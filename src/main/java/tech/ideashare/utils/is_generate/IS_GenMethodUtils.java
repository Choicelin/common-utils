package tech.ideashare.utils.is_generate;

import tech.ideashare.model.generate_model.IS_ProjectConfig;
import tech.ideashare.utils.IS_NameUtils;
import tech.ideashare.utils.IS_StringUtils;

/**
 * 生成方法的工具类
 * 用来生成Controller , Dao 层对应的方法
 * 中间的业务层不生成
 * @Author lixiang
 * @CreateTime 28/03/2018
 **/
public class IS_GenMethodUtils {

    /**
     * 生成 Dao 里面的方法
     * int insertModel(Model model);
     * List<Model> listModel(ModelQC model);
     * Integer countModel(ModelQC model);
     *
     * @param config 项目的基本配置
     * @return
     */
    public static String generateDao(IS_ProjectConfig config) {
        StringBuilder sb = new StringBuilder();

        //Dao里面的insert方法
        sb.append("int insert").append(config.getModelName()).append("(").append(config.getModelName()).append(" ").append(IS_StringUtils.first2LowLetter(config.getModelName())).append(");\n");

        //Dao里面的list方法
        sb.append("List<").append(config.getModelName()).append("> list").append(config.getModelName()).append("(").append(config.getModelName()).append("QC ").append(IS_StringUtils.first2LowLetter(config.getModelName())).append(");\n");

        //Dao里面的count方法
        sb.append("Integer count").append(config.getModelName()).append("(").append(config.getModelName()).append("QC ").append(IS_StringUtils.first2LowLetter(config.getModelName())).append(");\n");

        return sb.toString();
    }


    /**
     *
     * 生成Controller里面的方法(想弄成spring-boot 2.0的，但还没有实验过，所以这里先不写)
     * 获取List  Post
     * 通过id获取单个 Get
     *
     * @param config 项目的基本配置
     * @return
     */
    public static String generateController(IS_ProjectConfig config){
        StringBuilder sb  = new StringBuilder();
        sb.append("@PostMapping(\"/xxx/xxx\")");


        return sb.toString();
    }

}
