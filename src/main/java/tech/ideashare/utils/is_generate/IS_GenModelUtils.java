package tech.ideashare.utils.is_generate;

import java.lang.reflect.Field;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * @Author lixiang
 * @CreateTime 2018/04/03
 **/
public class IS_GenModelUtils {

    /**
     * 如果要使用这个方法，需要先提供一个字典文件(非必须)，在生成实体类时，会先从字典
     * 里面随机读取一个值做为实体类的值,默认从resource目录下读取,字典为    
     * json 格式数据，样文件可在本工程下 resource中找到
     * 如果
     */
    public  Object  generate_model(Class<?> clazz){

        //从配置文件中初始化字典
        
        //如果没有配置文件，则在类型匹配时会根据类型自动生成一个随机的东东
        
        //获取到一个类里面所有的变量
        Field[] allFields = clazz.getDeclaredFields();
        for(Field field : allFields){
            String typeString = field.getType().getName();
            switch (typeString) {
                case "java.lang.String":
                    System.out.println("OK");
                    break;
            
                default:
                    break;
            }
            System.out.println(field.getName());
            System.out.println(field.getType().getTypeName());
            
        }
    
        return null;
    }
}
