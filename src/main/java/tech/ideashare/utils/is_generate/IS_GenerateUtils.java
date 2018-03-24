package tech.ideashare.utils.is_generate;

import com.alibaba.fastjson.JSON;
import tech.ideashare.model.generate_model.MethodHeadModel;
import tech.ideashare.model.generate_model.MysqlField;
import tech.ideashare.model.generate_model.MysqlJdbcConfig;
import tech.ideashare.model.generate_model.MysqlTable;

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
     * and id = #{id,jdbcType=BIGINT}
     * </if>
     * </where>
     * </sql>
     * @param table
     * @return
     */
    public static String generateQueryCondition(MysqlTable table) {

        StringBuilder sb = new StringBuilder();
        List<String> fieldStrList = table.getFieldList().stream().map(field2BeanStr).collect(Collectors.toList());
        for (String s : fieldStrList) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }


    /**
     * 传入一个数据库的field对象，根据据Field里面的 name 和 type 返回一个 JAVA 的 String
     * 返回值如 private String name;
     */
    static Function<MysqlField, String> field2BeanStr = (MysqlField field) -> "private " + MysqlJdbcConfig.getJavaBean(field.getType()) + " " + field.getName() + ";";
}
