package tech.ideashare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

/**
 * @Author lixiang
 * @CreateTime 19/03/2018
 **/
@RestController
public class MyBaitsController {

    @GetMapping("mybatis")
    public String generateXml() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://10.211.55.4/lightblue?user=lixiang&password=admin123");

        String columnName;
        String columnType;
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet resultSet = metaData.getColumns(null,"%", "cart","%");

        while(resultSet.next()) {
            columnName = resultSet.getString("COLUMN_NAME");
            columnType = resultSet.getString("TYPE_NAME");
            int datasize = resultSet.getInt("COLUMN_SIZE");
            int digits = resultSet.getInt("DECIMAL_DIGITS");
            int nullable = resultSet.getInt("NULLABLE");
            System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable);
        }
        System.out.println(resultSet);
        return null;
    }
}
