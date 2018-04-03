package tech.ideashare.model.generate_model;

/**
 * @Author lixiang
 * @CreateTime 25/03/2018
 **/
public class IS_ProjectConfig {

    //实体名 命名首字母大写
    private String modelName="MybatisGenerate";

    //包名
    private String packageName="red.lixiang.utils";

    public String getModelName() {
        return modelName;
    }

    public IS_ProjectConfig setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public IS_ProjectConfig setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }
}
