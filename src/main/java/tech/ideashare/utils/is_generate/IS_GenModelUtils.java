package tech.ideashare.utils.is_generate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import com.alibaba.fastjson.JSON;

import tech.ideashare.utils.IS_RandomUtils;

/**
 * @Author lixiang
 * @CreateTime 2018/04/03
 **/
public class IS_GenModelUtils {

    /**
     * 如果要使用这个方法，需要先提供一个字典文件(非必须)，在生成实体类时，会先从字典
     * 里面随机读取一个值做为实体类的值,默认从resource目录下读取,字典为    
     * json 格式数据，样文件可在本工程下 resource中找到
     * 如果没有配置文件，会根据类型自动生成随机值（第一期只做了全随机的）
     */
    public  Object  generate_model(Class<?> clazz) throws IllegalAccessException,InstantiationException{

        Object obj  = clazz.newInstance();
        //从配置文件中初始化字典
        
        //如果没有配置文件，则在类型匹配时会根据类型自动生成一个随机的东东
        
        //获取到一个类里面所有的变量
        Field[] allFields = clazz.getDeclaredFields();
        for(Field field : allFields){
            String typeString = field.getType().getName();
            System.out.println(typeString);
            switch (typeString) {
                case "java.lang.String":
                    String strValue  = IS_RandomUtils.nextString(10);
                    field.setAccessible(true);
                    field.set(obj, strValue);
                    break;
                case "java.lang.Integer":
                    Integer intValue  = IS_RandomUtils.nextInt();
                    field.setAccessible(true);
                    field.set(obj, intValue);
                    break;
                case "java.util.Date":
                    Date dateValue  = new Date();
                    field.setAccessible(true);
                    field.set(obj, dateValue);
                    break;
                case "java.lang.Boolean":
                    Boolean boolValue  = IS_RandomUtils.nextBoolean();
                    field.setAccessible(true);
                    field.set(obj, boolValue);
                    break;
                case "java.math.BigDecimal":
                    DecimalFormat df = new DecimalFormat("#.00");
                    BigDecimal decimalValue  = new BigDecimal(df.format(IS_RandomUtils.nextDouble(0,1000)));
                    field.setAccessible(true);
                    field.set(obj, decimalValue);
                    break;
                case "java.lang.Long":
                    Long longValue  =IS_RandomUtils.nextLong();
                    field.setAccessible(true);
                    field.set(obj, longValue);
                    break;
                default:
                    break;
            }            
        }
        System.out.println(JSON.toJSONString(obj));
    
        return obj;
    }
}
