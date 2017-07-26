package tech.ideashare.utils;



import java.io.IOException;

/**
 * Created by lixiang on 18/06/2017.
 */

public class IS_SMSUtils {

    private static String phoneNumber = "15898191969";

    public static void main(String[] args)throws Exception
    {



    }

//    public static Boolean sendSms(String mobilePhone,String smsText) throws IOException {
//        HttpClient client = new HttpClient();
//        PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
//        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
//        NameValuePair[] data ={ new NameValuePair("Uid", "lixiang9409"),new NameValuePair("Key", "2cfb8ee9e6fc7034f8f1"),new NameValuePair("smsMob",mobilePhone),new NameValuePair("smsText",smsText)};
//        post.setRequestBody(data);
//
//        client.executeMethod(post);
//        Header[] headers = post.getResponseHeaders();
//        int statusCode = post.getStatusCode();
//        System.out.println("statusCode:"+statusCode);
//        for(Header h : headers)
//        {
//            System.out.println(h.toString());
//        }
//        String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
//        System.out.println(result); //打印返回消息状态
//
//
//        post.releaseConnection();
//        return true;
//    }


}
