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


        return null;
    }
}
