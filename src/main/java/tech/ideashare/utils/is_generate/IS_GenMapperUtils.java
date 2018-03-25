package tech.ideashare.utils.is_generate;

import tech.ideashare.model.generate_model.*;
import tech.ideashare.utils.IS_NameUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author lixiang
 * @CreateTime 17/03/2018
 **/
public class IS_GenMapperUtils {

    /**
     * 生成方法头
     *
     * @return
     */
    public static String generateMethodHead(MethodHeadModel headModel) {

        return "";
    }

    /**
     * 对一个table对象生与一个对应的数据库实体类
     *
     * @param table
     * @return
     */
    public static String generateModel(MysqlTable table) {

        StringBuilder sb = new StringBuilder();
        List<String> fieldStrList = table.getFieldList().stream().map(field2BeanStr).collect(Collectors.toList());
        for (String s : fieldStrList) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }

    /**
     * 传入一个TABLE对象，生成里面所有字段对应xml的queryCondition
     * 如：
     * <sql id="queryCondition" >
     * <where >
     * <if test="id != null" >
     * and id = #{id}
     * </if>
     * </where>
     * </sql>
     *
     * @param table
     * @return
     */
    public static String generateQueryCondition(MysqlTable table) {

        StringBuilder sb = new StringBuilder("<sql id=\"queryCondition\" >").append("<where>\n");
        for (MysqlField field : table.getFieldList()) {
            sb.append("<if test=\"").append(IS_NameUtils.underScope2Camel(field.getName())).append("\" != null\" >\n")
                    .append("and ")
                    .append(field.getName())
                    .append("=#{")
                    .append(IS_NameUtils.underScope2Camel(field.getName()))
                    .append("}\n").append("</if>\n");
        }
        sb.append("</where>\n</sql>\n");
        return sb.toString();
    }

    /**
     * 传入一个TABLE对象，生成里面所有字段对应xml的ResultMap
     * 如：
     * <resultMap id="BaseResultMap" type="com.wdk.shop.model.PriceTagPrintItem" >
     * <id column="id" property="id" jdbcType="BIGINT" />
     * <result column="task_id" property="taskId" jdbcType="BIGINT" />
     * <result column="sku_code" property="skuCode" jdbcType="VARCHAR" />
     * </resultMap>
     *
     * @param table  要传入的表
     * @param config 项目配置，需要其中的包名，实体名
     * @return
     */
    public static String generateResultMap(MysqlTable table, IS_ProjectConfig config) {
        StringBuilder sb = new StringBuilder("<resultMap id=\"BaseResultMap\" type=\"" + config.getPackageName() + ".model." + config.getModelName() + "\" >\n");
        //id这一列单独处理
        sb.append("<id column=\"id\" property=\"id\" jdbcType=\"BIGINT\" />\n");
        for (MysqlField field : table.getFieldList()) {
            if (field.getName().equals("id")) continue;
            sb.append("<result column=\"")
                    .append(field.getName())
                    .append("\" property=\"")
                    .append(IS_NameUtils.underScope2Camel(field.getName()))
                    .append("\" jdbcType=\"")
                    .append(MysqlJdbcConfig.getResultMap(field.getType()))
                    .append("\" />\n");
        }
        sb.append("</resultMap>\n");
        return sb.toString();
    }

    /**
     * 生成BaseColumnList
     * <sql id="Base_Column_List">
     * id,type,name
     * </sql>
     *
     * @param table 对应的数据库的表
     * @return
     */
    public static String generateBaseColumnList(MysqlTable table) {
        StringBuilder sb = new StringBuilder("<sql id=\"Base_Column_List\">\n");
        List<MysqlField> fieldList = table.getFieldList();
        for (int i = 0; i < fieldList.size(); i++) {
            sb.append(fieldList.get(i).getName());
            if (i != fieldList.size() - 1) {
                //如果不是最后一个，那么就加上 ','
                sb.append(",");
            }
        }
        sb.append("\n</sql>");

        return sb.toString();
    }

    /**
     * 生成insert语句
     * <insert id="insert" parameterType="com.wdk.shop.model.StockoutOrderMsg">
     * insert into warn_stockout_order_msg
     * <trim prefix="(" suffix=")" suffixOverrides="," >
     * <if test="id != null" >
     * id,
     * </if>
     * <if test="msgId != null">
     * msg_id,
     * </if>
     * </trim>
     * <trim prefix="values (" suffix=")" suffixOverrides="," >
     * <if test="id != null" >
     * #{id,jdbcType=BIGINT},
     * </if>
     * <if test="msgId != null">
     * #{msgId,jdbcType=VARCHAR},
     * </if>
     * </trim>
     * </insert>
     *
     * @param table 对应的数据库的表
     * @param config 对应项目的配置，主要取包名和实体名
     * @return
     */
    public static String generateInsertSql(MysqlTable table,IS_ProjectConfig config) {
        if(config==null){
            config =new IS_ProjectConfig();
        }
        StringBuilder sb = new StringBuilder("<insert id=\"insert"+config.getModelName()+"\" parameterType=\""+config.getPackageName()+".model."+config.getModelName()+"\">\n");
        sb.append("insert into ").append(table.getTableName()).append("\n");
        // 生成上面的table属性名
        StringBuilder tableBuilder = new StringBuilder("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        // 生成下面插入的value JAVABean
        StringBuilder valueBuilder = new StringBuilder("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        for (MysqlField field : table.getFieldList()) {
            //生成上面的
            tableBuilder.append("<if test=\"").append(IS_NameUtils.underScope2Camel(field.getName())).append(" != null\" >\n").append(field.getName()).append(",\n").append("</if>\n");

            //生成下面的
            valueBuilder.append("<if test=\"").append(IS_NameUtils.underScope2Camel(field.getName())).append(" != null\">\n").append("#{").append(IS_NameUtils.underScope2Camel(field.getName())).append(",jdbcType=").append(MysqlJdbcConfig.getResultMap(field.getType())).append("},\n").append("</if>\n");

        }
        tableBuilder.append("</trim>\n");
        valueBuilder.append("</trim>");
        sb.append(tableBuilder).append(valueBuilder);
        sb.append("\n</sql>");

        return sb.toString();
    }


    /**
     * 传入一个数据库的field对象，根据据Field里面的 name 和 type 返回一个 JAVA 的 String
     * 返回值如 private String name;
     */
    static Function<MysqlField, String> field2BeanStr = (MysqlField field) -> "private " + MysqlJdbcConfig.getJavaBean(field.getType()) + " " + field.getName() + ";";
}
