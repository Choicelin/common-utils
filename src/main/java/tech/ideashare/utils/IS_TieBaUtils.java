package tech.ideashare.utils;

import com.alibaba.fastjson.JSONObject;
import tech.ideashare.model.TieBaReply;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static tech.ideashare.config.URLConfig.*;

/**
 * Created by lixiang on 14/07/2017.
 */
public class IS_TieBaUtils {

    /**
     * 发表新贴
     */
    public static JSONObject postNewThread(String tiebaName,String postTitle,String postContent){
        //请求tbs
        String tbsString  = getTbs();

        //获取fid
        String fidString  = getFid(tiebaName);

        //发贴的参数
        LinkedHashMap<String,String> postMap = new LinkedHashMap<>();
        postMap.put("ie","utf-8");
        postMap.put("kw",tiebaName);
        postMap.put("fid",fidString);
        postMap.put("rich_text","1");
        postMap.put("tbs",tbsString);
        postMap.put("content",postContent);
        postMap.put("title",postTitle);
        postMap.put("__type__","thread");

        //开始发贴请求
        JSONObject postReturnJson = IS_HttpUtils.postAsJson(POST_URL,postMap);
        return postReturnJson;

    }

    /**
     * 发表回复
     */
    public static void postNewReply(TieBaReply tieBaReply){
        //请求tbs
        String tbsString  = getTbs();

        //获取fid
        String fidString  = getFid(tieBaReply.getTbName());
        //发贴的参数
        LinkedHashMap<String,String> postMap = new LinkedHashMap<>();
        postMap.put("ie","utf-8");
        postMap.put("kw",tieBaReply.getTbName());
        postMap.put("fid",fidString);
        postMap.put("rich_text","1");
        postMap.put("tbs",tbsString);
        postMap.put("content",tieBaReply.getReply());
        postMap.put("__type__","reply");
        postMap.put("tid",tieBaReply.getTid());

        //开始发贴请求
        JSONObject postReturnJson = IS_HttpUtils.postAsJson(REPLY_URL,postMap);
        int errCode = postReturnJson.getIntValue("err_code");
        if(0==errCode){
            //成功回贴
        }else if(40==errCode){
            //需要验证码
            String captcha_vcode_str = postReturnJson.getJSONObject("data").getJSONObject("vcode").getString("captcha_vcode_str");
            String imageUrl = VERIFY_IMAGE_URL+captcha_vcode_str;
            LinkedHashMap<String,String> map = new LinkedHashMap<>();
            map.put("tag","pc");
            map.put("t","0.15532535115546553");
            byte[] imageBytes = IS_HttpUtils.getAsBytes(imageUrl,map);


            HashMap<String ,String> codeMap = new HashMap<>();
            codeMap.put("1","00000000");
            codeMap.put("2","00010000");
            codeMap.put("3","00020000");
            codeMap.put("4","00000001");
            codeMap.put("5","00010001");
            codeMap.put("6","00020001");
            codeMap.put("7","00000002");
            codeMap.put("8","00010002");
            codeMap.put("9","00020002");

            ////整个窗体的设置
            Stage verifyStage = new Stage();
            AnchorPane anchorPane = new AnchorPane();
            Scene scene = new Scene(anchorPane,430,350);
            verifyStage.setTitle("验证码");
            verifyStage.setScene(scene);
            verifyStage.show();

            ImageView verifyImage = new ImageView();
            verifyImage.setLayoutX(10);
            verifyImage.setLayoutY(80);
            anchorPane.getChildren().add(verifyImage);

            TextField verifyCode = new TextField();
            verifyCode.setLayoutX(10);
            verifyCode.setLayoutY(10);
            anchorPane.getChildren().add(verifyCode);

            Button submitVerifyCode = new Button("提交");
            submitVerifyCode.setLayoutX(200);
            submitVerifyCode.setLayoutY(10);
            anchorPane.getChildren().add(submitVerifyCode);

            Image image = new Image(new ByteArrayInputStream(imageBytes));
            verifyImage.setImage(image);

            submitVerifyCode.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    StringBuilder code= new StringBuilder();
                    for (char c : verifyCode.getText().toCharArray()) {
                        String codeValue = codeMap.get(String.valueOf(c));
                        code.append(codeValue);
                    }
                    System.out.println("转换过的code是："+code);
                    LinkedHashMap<String , String > checkCodeMap = new LinkedHashMap<>();
                    checkCodeMap.put("captcha_vcode_str",captcha_vcode_str);
                    checkCodeMap.put("captcha_code_type","4");
                    checkCodeMap.put("captcha_input_str",code.toString());
                    checkCodeMap.put("fid",fidString);

                    JSONObject responseJson = IS_HttpUtils.postAsJson(CHECK_VERIFYCODE_URL,checkCodeMap);
                    int antiErrorCode = responseJson.getIntValue("anti_value_err_no");
                    if(0 == antiErrorCode){
                        //验证通过，可以再次发送回贴数据
                        postMap.put("captcha_vcode_str",captcha_vcode_str);
                        postMap.put("captcha_code_type","4");
                        postMap.put("vcode_md5",captcha_vcode_str);
                        postMap.put("captcha_input_str",code.toString());
                        JSONObject postReturnJson = IS_HttpUtils.postAsJson(REPLY_URL,postMap);
                    }

                }

            });

        }

    }

    public static String getTbs(){
        JSONObject tbsReturnObject  = IS_HttpUtils.getAsJson(TBS_URL,null);
        return tbsReturnObject.getString("tbs");
    }

    public static String getFid(String tbName){
        LinkedHashMap<String, String> fidMap = new LinkedHashMap<>();
        fidMap.put("ie","utf-8");
        fidMap.put("fname",tbName);
        JSONObject responseJson =  IS_HttpUtils.getAsJson(FID_URL,fidMap);
        return responseJson.getJSONObject("data").getString("fid");
    }

    public static JSONObject getPersonInfo(){
        JSONObject personReturnObject  = IS_HttpUtils.getAsJson(USER_INFO_URL,null);
        return personReturnObject;
    }

}
