package tech.ideashare.model.generate_model;

import java.util.List;

/**
 * @Author lixiang
 * @CreateTime 17/03/2018
 **/
public class MethodHeadModel {

    // public private
    private String scope = "public";

    //返回值类型 void , String 等等
    private String returnType= "String";

    //方法名
    private String methodName = "helloLiXiang";

    //参数列表
    private List<String> params;


    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}
