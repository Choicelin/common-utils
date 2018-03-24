package tech.ideashare.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ideashare.model.generate_model.MysqlField;
import tech.ideashare.model.generate_model.MysqlTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lixiang
 * @CreateTime 19/03/2018
 **/
@RestController
public class MyBaitsController {

    @GetMapping("mybatis")
    public String generateXml() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {


        return null;
    }
}
