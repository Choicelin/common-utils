package tech.ideashare.utils.is_sms;



import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiang on 18/06/2017.
 */

public class IS_SMSUtils {

    private static String phoneNumber = "15898191969";

    public static void main(String[] args)throws Exception
    {



    }

    public static Boolean sendSms(IS_SMSConfig config, String mobilePhone,String smsText) {

        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(config.getUrl());
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("Uid", config.getUid()));
            nvps.add(new BasicNameValuePair("Key", config.getKey()));
            nvps.add(new BasicNameValuePair("smsMob",mobilePhone));
            nvps.add(new BasicNameValuePair("smsText",smsText));
            System.out.println(JSON.toJSONString(nvps));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");
            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpPost.setEntity(entity);
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpPost);
            String returnString = EntityUtils.toString(response.getEntity());
            System.out.println(returnString);
            httpPost.releaseConnection();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }


        return true;
    }


}
