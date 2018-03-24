package tech.ideashare.utils;

import com.alibaba.fastjson.JSON;
import tech.ideashare.model.generate_model.MysqlJdbcConfig;
import tech.ideashare.model.generate_model.MysqlTable;
import tech.ideashare.utils.is_generate.IS_GenerateUtils;
import tech.ideashare.utils.is_generate.IS_MySqlUtils;

/**
 * @Author lixiang
 * @CreateTime 07/03/2018
 **/
public class Test {



    public static void main(String[] args) {
        MysqlJdbcConfig config = new MysqlJdbcConfig();
        config.setUrl("10.211.55.4")
                .setDatabaseName("lightblue")
                .setUserName("lixiang")
                .setPassword("admin123");
        MysqlTable table = IS_MySqlUtils.getTableInfo(config,"test_table");
        System.out.println(JSON.toJSONString(table));
        System.out.println(IS_GenerateUtils.generateModel(table));
    }
}
