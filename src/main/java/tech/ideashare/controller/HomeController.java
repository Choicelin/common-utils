package tech.ideashare.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class HomeController implements EnvironmentAware {

    private    final Logger logger = Logger.getLogger(HomeController.class.getName());


    private Environment environment;


    @GetMapping("/")
    public String showHome(){
        logger.info("come in home");
        System.out.println(environment.getActiveProfiles());
        return "index";
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}
