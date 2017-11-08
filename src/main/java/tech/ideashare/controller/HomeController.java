package tech.ideashare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class HomeController {

    private    final Logger logger = Logger.getLogger(HomeController.class.getName());




    @GetMapping("/")
    public String showHome(){
        logger.info("come in home");
        return "index";
    }



}
