package tech.ideashare.utils;

/**
 * @Author lixiang
 * @CreateTime 24/03/2018
 **/
public class IS_StringUtils {

    public static String first2BigLetter(String word){
        return word.substring(0,1).toUpperCase().concat(word.substring(1));
    }
}
