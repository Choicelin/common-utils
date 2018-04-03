package tech.ideashare.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import tech.ideashare.model.TestModel;
import tech.ideashare.model.generate_model.IS_ProjectConfig;
import tech.ideashare.model.generate_model.MysqlJdbcConfig;
import tech.ideashare.model.generate_model.MysqlTable;
import tech.ideashare.utils.is_generate.IS_GenMapperUtils;
import tech.ideashare.utils.is_generate.IS_GenModelUtils;
import tech.ideashare.utils.is_generate.IS_MySqlUtils;

/**
 * @Author lixiang
 * @CreateTime 07/03/2018
 **/
public class Test {



    public static void main(String[] args) {
        // MysqlJdbcConfig config = new MysqlJdbcConfig();
        // config.setUrl("115.28.167.227")
        //         .setDatabaseName("lightbluedev")
        //         .setUserName("lixiang")
        //         .setPassword("admin123");
        // MysqlTable table = IS_MySqlUtils.getTableInfo(config,"users");
        // System.out.println(IS_GenMapperUtils.generateModel(table));
        // System.out.println(IS_GenMapperUtils.generateResultMap(table,new IS_ProjectConfig()));
        // System.out.println(IS_GenMapperUtils.generateQueryCondition(table));
        // System.out.println(IS_GenMapperUtils.generateBaseColumnList(table));
        // System.out.println(IS_GenMapperUtils.generateInsertSql(table,new IS_ProjectConfig()));
        // System.out.println(IS_GenMapperUtils.generateUpdateSql(table,new IS_ProjectConfig()));
        // System.out.println(IS_GenMapperUtils.generateSelectSql(table,new IS_ProjectConfig()));

        //测试类的生成和注入
        IS_GenModelUtils modelUtils = new IS_GenModelUtils();
        try{

            modelUtils.generate_model(TestModel.class);
        }catch(Exception e){
            e.printStackTrace();
        }
   
    }
}
