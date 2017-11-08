//package tech.ideashare.utils;
//
//import sun.misc.BASE64Encoder;
//
//import java.security.MessageDigest;
//
///**
// * Created by lixiang on 12/16/2016.
// */
//public class IS_MD5Utils {
//
//    public static String getMd5(String source){
//        String newStr="";
//        MessageDigest mdInst = null;
//        try {
//            mdInst = MessageDigest.getInstance("MD5");
//            BASE64Encoder base64en = new BASE64Encoder();
//            //加密后的字符串
//            newStr=base64en.encode(mdInst.digest(source.getBytes("utf-8")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return newStr;
//    }
//}
