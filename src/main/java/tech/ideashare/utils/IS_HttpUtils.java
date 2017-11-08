package tech.ideashare.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiang on 10/07/2017.
 */
public class IS_HttpUtils {

    public static HashMap<String,String> configMap = new HashMap<>();

    public static final String USER_COOKIE = "user_cookie";


    // 私有构造器，防止类的实例化
    private IS_HttpUtils(){
        super();
    }

    // =====================================================================================================================================


    public static byte[] getAsBytes(String url , LinkedHashMap<String,String > params){
        //判断请求地址是否为空
        if (StringUtils.isBlank(url)){
            return null;
        }

        try{

            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> nvps = uriBuilder.getQueryParams();
            nvps = transformParams(params, nvps, Boolean.FALSE);
            uriBuilder.setParameters(nvps);
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            byte[] responseAsBytes = getResponseAsBytes(httpGet);
            return responseAsBytes;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    /**
     * GET提交（默认UTF-8编码格式）
     * @param url                                请求地址
     * @param params                        请求参数
     * @return
     */
    public static JSONObject getAsJson(String url, LinkedHashMap<String, String> params){
        return getAsJson(url, params, CharEncoding.UTF_8);
    }

    /**
     * GET提交
     * @param url                                请求地址
     * @param params                        请求参数
     * @param charsetName               编码格式
     * @return
     */
    public static JSONObject getAsJson(String url, LinkedHashMap<String, String> params, String charsetName){
        try {
            String jsonStr = getAsString(url, params, charsetName);
            return JSON.parseObject(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * GET提交
     * @param url                                         请求地址
     * @param params                                 请求参数
     * @param charsetName                        编码格式
     * @return
     */
    public static JSONObject getAsJson(String url, List<NameValuePair> params, String charsetName){
        try{
            String jsonStr = getAsString(url, params, charsetName);
            return JSON.parseObject(jsonStr);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * GET提交（默认UTF-8编码格式）
     * @param url                                请求地址
     * @param params                         请求参数
     * @return
     */
    public static String getAsString(String url, LinkedHashMap<String, String> params){
        return getAsString(url, params, CharEncoding.UTF_8);
    }

    /**
     * GET提交
     * @param url                                请求地址
     * @param params                        请求参数
     * @param charsetName               编码格式
     * @return
     */
    public static String getAsString(String url, LinkedHashMap<String, String> params, String charsetName){
        //判断请求地址是否为空
        if (StringUtils.isBlank(url)){
            return null;
        }

        try{

            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> nvps = uriBuilder.getQueryParams();
            nvps = transformParams(params, nvps, Boolean.FALSE);
            uriBuilder.setParameters(nvps);
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            String jsonStr = getResponseAsStr(httpGet, charsetName);
            return jsonStr;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * GET提交
     * @param url                                         请求地址
     * @param params                                 请求参数
     * @param charsetName                        编码格式
     * @return
     */
    public static String getAsString(String url, List<NameValuePair> params, String charsetName){
        if (StringUtils.isBlank(url)){
            return null;
        }

        try{

            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> nvps = uriBuilder.getQueryParams();
            nvps.removeAll(params);
            nvps.addAll(params);
            nvps = transformParams(null, nvps, Boolean.FALSE);
            uriBuilder.setParameters(nvps);
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            String jsonStr = getResponseAsStr(httpGet, charsetName);
            return jsonStr;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * POST提交（默认UTF-8编码格式）
     * @param url                   提交地址
     * @param params           提交的参数
     * @return
     */
    public static JSONObject postAsJson(String url, LinkedHashMap<String, String> params){
        return postAsJson(url, params, CharEncoding.UTF_8);
    }

    /**
     * POST提交
     * @param url                           提交地址
     * @param params                   提交的参数
     * @param charsetName          编码格式
     * @return
     */
    public static JSONObject postAsJson(String url, LinkedHashMap<String, String> params, String charsetName){
        try{
            String jsonStr = postAsString(url, params, charsetName);
            return JSON.parseObject(jsonStr);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * GET提交
     * @param url                                         请求地址
     * @param params                                 请求参数
     * @param charsetName                        编码格式
     * @return
     */
    public static JSONObject postAsJson(String url, List<NameValuePair> params, String charsetName){
        try{
            String jsonStr = postAsString(url, params, charsetName);
            return JSON.parseObject(jsonStr);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * POST提交（默认UTF-8编码格式）
     * @param url                           提交地址
     * @param params                   提交的参数
     * @return
     */
    public static String postAsString(String url, LinkedHashMap<String, String> params) {
        return postAsString(url, params, CharEncoding.UTF_8);
    }

    /**
     * POST提交
     * @param url                           提交地址
     * @param params                   提交的参数
     * @param charsetName          编码格式
     * @return
     */
    public static String postAsString(String url, LinkedHashMap<String, String> params, String charsetName)
    {
        if (StringUtils.isBlank(url))
        {
            return null;
        }

        try
        {

            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> nvps = uriBuilder.getQueryParams();
            nvps = transformParams(params, nvps, Boolean.TRUE);
            System.out.println(JSON.toJSONString(nvps));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, CharEncoding.UTF_8);

            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpPost.setEntity(entity);
            String jsonStr = getResponseAsStr(httpPost, charsetName);

            return jsonStr;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * POST提交
     * @param url                           提交地址
     * @param params                   提交的参数
     * @param charsetName          编码格式
     * @return
     */
    public static String postAsString(String url, List<NameValuePair> params, String charsetName)
    {
        if (StringUtils.isBlank(url))
        {
            return null;
        }

        try
        {

            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> nvps = uriBuilder.getQueryParams();
            nvps.removeAll(params);
            nvps.addAll(params);
            nvps = transformParams(null, nvps, Boolean.TRUE);
            UrlEncodedFormEntity  entity = new UrlEncodedFormEntity(nvps, CharEncoding.UTF_8);

            HttpPost httpPost = new HttpPost(uriBuilder.build());
            httpPost.setEntity(entity);
            String jsonStr = getResponseAsStr(httpPost, charsetName);

            return jsonStr;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Post 文件上传（默认UTF-8编码格式）
     * @param url                提交地址
     * @param params        提交参数集, 键/值对
     * @param field             接收上传文件的字段
     * @param file               上传的文件
     * @return
     * @throws Exception
     */
    public static JSONObject postMultipartAsJson(String url, LinkedHashMap<String, String> params, String field, File file) throws Exception
    {
        return postMultipartAsJson(url, params, field, file, CharEncoding.UTF_8);
    }

    /**
     * Post 文件上传
     * @param url                           提交地址
     * @param params                   提交参数集, 键/值对
     * @param field                        接收上传文件的字段
     * @param file                          上传的文件
     * @param charsetName          编码格式
     * @return
     * @throws Exception
     */
    public static JSONObject postMultipartAsJson(String url, LinkedHashMap<String, String> params, String field, File file, String charsetName) throws Exception
    {
        try
        {
            String jsonStr = postMultipartAsString(url, params, field, file, charsetName);
            return JSON.parseObject(jsonStr);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Post 文件上传（默认UTF-8编码格式）
     * @param url                           提交地址
     * @param params                   提交参数集, 键/值对
     * @param field                        接收上传文件的字段
     * @param file                          上传的文件
     * @return
     * @throws Exception
     */
    public static String postMultipartAsString(String url, LinkedHashMap<String, String> params, String field, File file) throws Exception
    {
        return postMultipartAsString(url, params, field, file, CharEncoding.UTF_8);
    }

    /**
     * Post 文件上传
     * @param url                           提交地址
     * @param params                   提交参数集, 键/值对
     * @param field                        接收上传文件的字段
     * @param file                          上传的文件
     * @param charsetName          编码格式
     * @return
     * @throws Exception
     */
    public static String postMultipartAsString(String url, LinkedHashMap<String, String> params, String field, File file, String charsetName) throws Exception
    {
        if (StringUtils.isBlank(url))
        {
            return null;
        }

        // 创建HttpClient实例
        URIBuilder uriBuilder = new URIBuilder(url);
        List<NameValuePair> nvps = uriBuilder.getQueryParams();
        nvps = transformParams(params, nvps, Boolean.TRUE);
        uriBuilder.setParameters(nvps);
        HttpPost hp = new HttpPost(uriBuilder.build());



        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        multipartEntityBuilder.setCharset(Consts.UTF_8);
        // 上传的文件
        if (StringUtils.isNotBlank(field) && null != file)
        {
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE).addPart("file", new FileBody(file, ContentType.create("multipart/form-data"), file.getName()));
        }
        HttpEntity entity= multipartEntityBuilder.build();

        hp.setEntity(multipartEntityBuilder.build());

        try
        {
            String jsonStr = getResponseAsStr(hp, charsetName);
            return jsonStr;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 释放请求连接
     *
     * @param request
     */
    public static void releaseConnection(HttpRequestBase request)
    {
        if (request != null)
        {
            request.releaseConnection();
        }

    }

    // =====================================================================================================================================

    /**
     * 将LinkedHashMap格式的参数转为合并到List<NameValuePair>的参数中
     * @param params
     * @param nvps
     */
    private static List<NameValuePair> transformParams(LinkedHashMap<String, String> params, List<NameValuePair> nvps, Boolean isPost){

        if(null!=params){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }


//        if(null == nvps){
//            nvps = Lists.newArrayList();
//        }
//
//        if (null != params && !params.isEmpty()){
//            // 实现浏览器的机制，post请求的QueryParams参数如果与表单参数冲突，将以表单参数替换
//            if(Boolean.TRUE.equals(isPost)){
//                LinkedHashMap<String, List<NameValuePair>> linked_nvps = Maps.newLinkedHashMap();
//                for(NameValuePair nvp : nvps){
//                    List<NameValuePair> sameName_nvps = linked_nvps.get(nvp.getName());
//                    if(null == sameName_nvps){
//                        sameName_nvps  = Lists.newArrayList();
//                        linked_nvps.put(nvp.getName(), sameName_nvps);
//                    }
//                    sameName_nvps.add(nvp);
//                }
//
//                for (Map.Entry<String, String> entry : params.entrySet()){
//                    if(null == entry.getValue() || "".equals(entry.getValue())){
//                        continue;
//                    }
//
//                    NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
//                    linked_nvps.put(entry.getKey(), Lists.newArrayList(nvp));
//                }
//
//                nvps = Lists.newArrayList();
//                for(List<NameValuePair> list : linked_nvps.values())     {
//                    nvps.addAll(list);
//                }
//            }else {
//                for (Map.Entry<String, String> entry : params.entrySet()){
//                    if(null == entry.getValue() || "".equals(entry.getValue())){
//                        continue;
//                    }
//
//                    NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue());
//                    nvps.add(nvp);
//                }
//            }
//        }

        return nvps;
    }

    /**
     * 获取HttpClient
     *
     * @return HttpClient HttpClient
     * @throws Exception
     */
    public static HttpClient getHttpClient() throws Exception {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
        };
        sslcontext.init(null, new TrustManager[] { tm }, null);
        HttpClient httpClient = HttpClientBuilder.create().setSslcontext(sslcontext).build();
        return httpClient;
    }

    /**
     * 执行request请求，返回String
     *
     * @param request       HttpRequestBase
     * @return String          响应内容
     */
    private static String getResponseAsStr(HttpRequestBase request, String charsetName){
        String responseText = null;
        StringBuffer slog = new StringBuffer();

        HttpResponse response = getResponse(request);

        try{
            if (StringUtils.isNotBlank(charsetName))
            {
                responseText = EntityUtils.toString(response.getEntity(), charsetName);
            } else
            {
                responseText = EntityUtils.toString(response.getEntity());
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            releaseConnection(request);// 释放连接
        }
        slog.append("#### 请求URL返回值: " + responseText);
        System.out.println(slog.toString()+"\n\n\n");
        return responseText;
    }

    private static byte[] getResponseAsBytes(HttpRequestBase request){
        byte[] responseBytes = null;
        StringBuffer slog = new StringBuffer();

        HttpResponse response = getResponse(request);

        try{
            responseBytes = EntityUtils.toByteArray(response.getEntity());
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            releaseConnection(request);// 释放连接
        }
        slog.append("#### 请求URL返回值为bytes " + responseBytes);
        System.out.println(slog.toString()+"\n\n\n");
        return responseBytes;
    }

    /**
     * 执行request请求，返回HttpResponse对象
     *
     * @param request
     *                HttpRequestBase
     * @return String 响应内容
     */
    private static HttpResponse getResponse(HttpRequestBase request){
        if (null == request){
            return null;
        }

        HttpResponse response = null;
        try
        {
            request.addHeader("cookie", configMap.get(USER_COOKIE));
            request.addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
            System.out.println("request uri: "+request.toString());
            response = executeRequest(request);


        } catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    /**
     * 执行request请求
     *
     * @param request
     * @return
     * @throws Exception
     */
    private static HttpResponse executeRequest(HttpRequestBase request) throws Exception{
        request.setConfig(RequestConfig.custom()
                .setSocketTimeout(100000)
                .setConnectTimeout(100000)
                .build());
        //对请求发出去的位置进行时间埋点，并且在Cat中进行捕获
        HttpResponse response = getHttpClient().execute(request);

        // 获取状态
        int statuscode = response.getStatusLine().getStatusCode();

        if (statuscode == HttpStatus.SC_OK){
            return response;
        }
        // 重定向处理
        else if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY)
                || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                || (statuscode == HttpStatus.SC_SEE_OTHER)
                || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {

            Header redirectLocation = response.getFirstHeader("Location");
            String newUrl = redirectLocation.getValue();
            if (StringUtils.isNotBlank(newUrl)){
                request.setURI(new URI(newUrl));
                executeRequest(request);
            }
        }

        System.out.println("#### 请求URL :" + request.getURI().toString()+"#### 请求返回状态异常 :" + response.getStatusLine().getStatusCode());
        return null;
    }

//    private static String showLog(List<NameValuePair> nvps){
//        LinkedHashMap<String, List<String>> params = Maps.newLinkedHashMap();
//
//        if(CollectionUtils.isNotEmpty(nvps)){
//            for (NameValuePair nvp : nvps){
//                List<String> param = params.get(nvp.getName());
//                if(null == param){
//                    param = Lists.newArrayList();
//                    params.put(nvp.getName(), param);
//                }
//                param.add(nvp.getValue());
//            }
//        }
//
//        return JSON.toJSONString(params);
//    }

}
