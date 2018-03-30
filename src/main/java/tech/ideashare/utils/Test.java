package tech.ideashare.utils;

import tech.ideashare.model.generate_model.IS_ProjectConfig;
import tech.ideashare.model.generate_model.MysqlJdbcConfig;
import tech.ideashare.model.generate_model.MysqlTable;
import tech.ideashare.utils.is_generate.IS_GenMapperUtils;
import tech.ideashare.utils.is_generate.IS_MySqlUtils;

/**
 * @Author lixiang
 * @CreateTime 07/03/2018
 **/
public class Test {



    public static void main(String[] args) {
        MysqlJdbcConfig config = new MysqlJdbcConfig();
        config.setUrl("10.211.55.4")
                .setDatabaseName("hema")
                .setUserName("lixiang")
                .setPassword("admin123");
        MysqlTable table = IS_MySqlUtils.getTableInfo(config,"stockout_order_msg");
        System.out.println(IS_GenMapperUtils.generateModel(table));
        System.out.println(IS_GenMapperUtils.generateQueryCondition(table));
        System.out.println(IS_GenMapperUtils.generateResultMap(table,new IS_ProjectConfig()));
        System.out.println(IS_GenMapperUtils.generateBaseColumnList(table));
        System.out.println(IS_GenMapperUtils.generateInsertSql(table,new IS_ProjectConfig()));
        System.out.println(IS_GenMapperUtils.generateUpdateSql(table,new IS_ProjectConfig()));
        System.out.println(IS_GenMapperUtils.generateSelectSql(table,new IS_ProjectConfig()));
        //测试vscode
    }
}
