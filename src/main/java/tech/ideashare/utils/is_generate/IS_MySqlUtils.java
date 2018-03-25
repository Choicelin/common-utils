package tech.ideashare.utils.is_generate;

import com.alibaba.fastjson.JSON;
import tech.ideashare.model.generate_model.MysqlField;
import tech.ideashare.model.generate_model.MysqlJdbcConfig;
import tech.ideashare.model.generate_model.MysqlTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lixiang
 * @CreateTime 24/03/2018
 **/
public class IS_MySqlUtils {

    public static MysqlTable getTableInfo(MysqlJdbcConfig config,String tableName){

        MysqlTable table = new MysqlTable();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection conn =DriverManager.getConnection(config.buildUrl());


            String columnName;
            String columnType;


            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getColumns(null,"%", tableName,"%");

            List<MysqlField> fieldList = new ArrayList<>();
            while(resultSet.next()) {
                MysqlField field = new MysqlField();
                columnName = resultSet.getString("COLUMN_NAME");
                columnType = resultSet.getString("TYPE_NAME");
                int datasize = resultSet.getInt("COLUMN_SIZE");
                int digits = resultSet.getInt("DECIMAL_DIGITS");
                int nullable = resultSet.getInt("NULLABLE");
                String remark = resultSet.getString("REMARKS");
                System.out.println(remark);
                field.setName(columnName);
                field.setType(columnType);
                fieldList.add(field);
            }
            table.setFieldList(fieldList).setTableName(tableName);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return table;
    }


}
