package tech.ideashare.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author lixiang
 * @CreateTime 16/03/2018
 **/
@RestController
public class ModelController {



    /**
     * 传入的Model名称必须要首字母大写
     */
    @GetMapping("/methodGen/{modelName}")
    public Mono<String> getUser(@PathVariable String modelName) {

        //todo:生成Controller 里面的方法代码

        String controllerListMethodName = "list"+modelName;
        String controllerGetMethodName = "get"+modelName;
        String requestModelName = modelName+"Request" ;
        String manage = modelName.toLowerCase()+"manager";
        String dao = modelName.toLowerCase()+"Dao";
        StringBuilder controllerSb = new StringBuilder();
        //list 的方法头
        controllerSb.append("public BaseResponse ")
                .append(controllerListMethodName).append("(").append(requestModelName).append(" ").append(requestModelName.toLowerCase()).append("){\n");
        // list 的方法体
        String controllerListReturn  = "List<"+modelName+"VO> "+" voList = "+manage+"."+controllerListMethodName+"();";

        controllerSb.append(controllerListReturn).append("\n");

        controllerSb.append("return  assemblePageResponse(voList,100,").append(requestModelName).append(".getPageIndex(),").append(requestModelName).append(".getPageSize());\n}\n");


        System.out.println(controllerSb.toString());
        //todo:生成Manager 里面的方法代码
        StringBuilder managerSb = new StringBuilder();

        managerSb.append("public List<").append(modelName).append("VO> ")
                .append(controllerListMethodName).append("(").append(requestModelName).append(" ").append(requestModelName.toLowerCase()).append("){\n");
        // list 的方法体
        String managerlistReturn  = "List<"+modelName+"VO> "+" voList = "+dao+"."+controllerListMethodName+"();";

        managerSb.append(managerlistReturn).append("\n");

        managerSb.append("return voList;\n");

        managerSb.append("}\n");

        System.out.println(managerSb.toString());


        //todo: 生成DAO 里面的方法代码
        StringBuilder daoSb = new StringBuilder();
        daoSb.append("public List<").append(modelName).append(">")
                .append(controllerListMethodName).append("(").append(modelName).append(" ").append(modelName.toLowerCase()).append(");\n");



        System.out.println(daoSb.toString());
        return Mono.just(JSON.toJSONString(controllerSb));
    }

}
