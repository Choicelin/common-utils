package tech.ideashare.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Created by lixiang on 15/07/2017.
 */
public class IS_FileUtils {

    private static Logger logger = Logger.getLogger(IS_FileUtils.class.toString());


    /**
     * 把文件从resources目录下转移到外部的conf目录下
     * @param fileName
     */
    public static void convertIstoFile(String fileName){
        InputStream is = IS_FileUtils.class.getClassLoader().getResourceAsStream(fileName);
        String pathNow = IS_XMLUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String filePath = new File(pathNow).getParent() + "/conf/"+fileName;
        File file = new File(filePath);
        //先判断外部是否存在配置文件，如果已经存在，则不进行导入

        if (file.exists()) {
            logger.info("配置文件已存在，不需要导入");
        }else{
            try {
                FileUtils.writeByteArrayToFile(file, IOUtils.toByteArray(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
