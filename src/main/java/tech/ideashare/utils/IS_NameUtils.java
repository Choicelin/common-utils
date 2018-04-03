package tech.ideashare.utils;

import tech.ideashare.utils.IS_StringUtils;

/**
 * @Author lixiang
 * @CreateTime 24/03/2018
 **/
public class IS_NameUtils {
    public static String underScope2Camel(String underScope){

        String[] ss = underScope.split("_");
        StringBuilder sb = new StringBuilder(ss[0]);
        for (int i = 0; i < ss.length; i++) {
            if (i==0) {
                continue;
            }
            sb.append(IS_StringUtils.first2BigLetter(ss[i]));
        }
        return sb.toString();
    }
    public static String camel2underScope(){
        return "";
    }
}
