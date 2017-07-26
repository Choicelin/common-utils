package tech.ideashare.utils;


import tech.ideashare.model.wrapper.BaseWrapper;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lixiang on 11/07/2017.
 */
public class IS_XMLUtils {

    public static Logger logger = Logger.getLogger(IS_XMLUtils.class.toString());
    static {
        logger.setLevel(Level.WARNING);
    }

    public static void saveToFile(BaseWrapper wrapper, String fileName, Class clazz){
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            String pathNow = IS_XMLUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String filePath = new File(pathNow).getParent() + "/conf/"+fileName;
            File file = new File(filePath);
            m.marshal(wrapper, file);
        } catch (PropertyException e) {
            logger.warning("propertyException");
            e.printStackTrace();
        } catch (JAXBException e) {
            logger.warning("JAXBException");
            e.printStackTrace();
        }
    }

    public static BaseWrapper loadFromFile(String fileName,Class clazz){
        try {

            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller um = context.createUnmarshaller();
            String pathNow = IS_XMLUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String filePath = new File(pathNow).getParent() + "/conf/"+fileName;
            File file = new File(filePath);
            return (BaseWrapper) um.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static BaseWrapper loadFromFile(File file,Class clazz){
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller um = context.createUnmarshaller();
            return (BaseWrapper) um.unmarshal(file);
        } catch (Exception e) { // catches ANY exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向xml文件中添加一条数据
     * @param object  要添加的数据，实体类
     * @param fileName 要添加的文件名
     * @param clazz   要添加的实体类的wrapper类
     */
    public static void addToFile(Object object,String fileName,Class clazz){

        BaseWrapper wrapper = loadFromFile(fileName,clazz);
        List list = wrapper.getList();
        list.add(object);
        saveToFile(wrapper,fileName,clazz);

    }

    /**
     * 从XML文件中根据指定的主键(@identify)删除一条记录
     * @param identify 指定的主键
     * @param identifyNo 指定的要删除的值
     * @param fileName 要删除的文件名
     * @param clazz 要删除文件存储的实体类的wrapper类
     */
    public static  void deleteFromFile(String identify ,String identifyNo, String fileName,Class clazz){
        //先取到list
        BaseWrapper wrapper = loadFromFile(fileName,clazz);
        List list = wrapper.getList();
        try {
            Object object = list.get(0).getClass().newInstance();
            StringBuffer methodName = new StringBuffer("get");
            methodName.append(identify.substring(0,1).toUpperCase())
                    .append(identify.substring(1));

            for (Object obj : list) {
               String value = (String) object.getClass().getMethod(methodName.toString()).invoke(obj);
               if(value.equals(identifyNo)){
                   //匹配成功，从list中将其删掉
                   list.remove(obj);
                   saveToFile(wrapper,fileName,clazz);
                   return;
               }
            }

        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析xml并转化为Json值
     * @param content json字符串
     * @return Json值
     */
//    public static JSONObject toJSONObject(String content){
//
//        if (null == content || "".equals(content)) {
//            return null;
//        }
//
//        try (InputStream in = new ByteArrayInputStream(content.getBytes("UTF-8"))){
//            return (JSONObject) inputStream2Map(in, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }

    /**
     * 解析xml并转化为json值
     * @param in 输入流
     * @return Json值
     */
//    public static JSONObject toJSONObject(InputStream in) {
//
//        if (null == in) {
//            return null;
//        }
//
//        try {
//            return (JSONObject)inputStream2Map(in, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//
//    }

    /**
     *
     * @param in xml输入流
     * @param m 参数集
     * @return 整理完成的参数集
     * @throws IOException xml io转化异常
     */
//    public static Map inputStream2Map(InputStream in, Map m) throws IOException {
//        if (null == m){
//            m = new JSONObject();
//        }
//        SAXBuilder builder = new SAXBuilder();
//        try {
//            Document doc = builder.build(in);
//            Element root = doc.getRootElement();
//            List list = root.getChildren();
//            Iterator it = list.iterator();
//            while (it.hasNext()) {
//                Element e = (Element) it.next();
//                String k = e.getName();
//                String v = "";
//                List children = e.getChildren();
//                if (children.isEmpty()) {
//                    v = e.getTextNormalize();
//                } else {
//                    v = getChildrenText(children);
//                }
//                m.put(k, v);
//            }
//        } catch (JDOMException e) {
//            e.printStackTrace();
//        }
//        return m;
//    }



    /**
     * 获取子结点的xml
     *
     * @param children 集合
     * @return String 子结点的xml
     */
//    public static String getChildrenText(List children) {
//        StringBuffer sb = new StringBuffer();
//        if (!children.isEmpty()) {
//            Iterator it = children.iterator();
//            while (it.hasNext()) {
//                Element e = (Element) it.next();
//                String name = e.getName();
//                String value = e.getTextNormalize();
//                List list = e.getChildren();
//                sb.append("<" + name + ">");
//                if (!list.isEmpty()) {
//                    sb.append(getChildrenText(list));
//                }
//                sb.append(value);
//                sb.append("</" + name + ">");
//            }
//        }
//
//        return sb.toString();
//    }

    /**
     * 将请求参数转换为xml格式的string
     * @param parameters 请求参数
     * @return xml
     */
    public static String getMap2Xml(Map<String, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (String key : parameters.keySet()){
            if ("attach".equalsIgnoreCase(key) || "body".equalsIgnoreCase(key) || "attach".equalsIgnoreCase(key) || "sign".equalsIgnoreCase(key)) {
                sb.append("<" + key + ">" + "<![CDATA[" + parameters.get(key) + "]]></" + key + ">");
            } else {
                sb.append("<" + key + ">" +  parameters.get(key) + "</" + key + ">");
            }

        }

        sb.append("</xml>");
        return sb.toString();
    }

}
