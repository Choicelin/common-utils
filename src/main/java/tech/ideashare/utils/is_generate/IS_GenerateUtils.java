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
public class IS_GenerateUtils {

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
     * @param table 要传入的表
     * @param config 项目配置，需要其中的包名，实体名
     * @return
     */
    public static String generateResultMap(MysqlTable table, IS_ProjectConfig config) {
        StringBuilder sb = new StringBuilder("<resultMap id=\"BaseResultMap\" type=\""+config.getPackageName()+"."+config.getModelName()+"\" >\n");
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
     * 传入一个数据库的field对象，根据据Field里面的 name 和 type 返回一个 JAVA 的 String
     * 返回值如 private String name;
     */
    static Function<MysqlField, String> field2BeanStr = (MysqlField field) -> "private " + MysqlJdbcConfig.getJavaBean(field.getType()) + " " + field.getName() + ";";
}
