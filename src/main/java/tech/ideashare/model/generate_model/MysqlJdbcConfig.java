package tech.ideashare.model.generate_model;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lixiang
 * @CreateTime 24/03/2018
 **/
public class MysqlJdbcConfig {
    private String url;
    private String databaseName;
    private String userName;
    private String password;

    //<数据库类型，JAVA类型>
    private static Map<String,String> property2BeanMap = new HashMap<>();
    //<数据库类型，resultMap里面的类型>
    private static Map<String,String> property2ResultMap = new HashMap<>();

    static{
        property2BeanMap.put("BIGINT","Integer");
        property2BeanMap.put("BIGINT UNSIGNED","Integer");
        property2BeanMap.put("TINYINT","Integer");
        property2BeanMap.put("VARCHAR","String");
        property2BeanMap.put("DATETIME","Date");
        property2BeanMap.put("TIMESTAMP","Date");
        property2BeanMap.put("DECIMAL","BigDecimal");
        property2BeanMap.put("INT","Integer");


        property2ResultMap.put("TINYINT","TINYINT");
        property2ResultMap.put("BIGINT","BIGINT");
        property2ResultMap.put("BIGINT UNSIGNED","BIGINT");
        property2ResultMap.put("VARCHAR","VARCHAR");
        property2ResultMap.put("DATETIME","TIMESTAMP");
        property2ResultMap.put("TIMESTAMP","TIMESTAMP");
        property2ResultMap.put("DECIMAL","DECIMAL");
        property2ResultMap.put("INT","INTEGER");
    }

    public static String getJavaBean(String property){
        return property2BeanMap.get(property);
    }
    public static String getResultMap(String property){
        return property2ResultMap.get(property);
    }

    public String getUrl() {
        return url;
    }

    public MysqlJdbcConfig setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public MysqlJdbcConfig setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public MysqlJdbcConfig setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MysqlJdbcConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public String buildUrl(){
        return "jdbc:mysql://"+url+"/"+databaseName+"?user="+userName+"&password="+password;
    }
}
