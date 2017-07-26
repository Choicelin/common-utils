package tech.ideashare;

import tech.ideashare.model.wrapper.TieBaPostWrapper;
import tech.ideashare.utils.IS_XMLUtils;

/**
 * Created by lixiang on 09/07/2017.
 */
public class test {

    public static void main(String[] args) {
       TieBaPostWrapper baPostWrapper = (TieBaPostWrapper) IS_XMLUtils.loadFromFile("conf/posts.xml",TieBaPostWrapper.class);
        System.out.println("a");
    }


}
