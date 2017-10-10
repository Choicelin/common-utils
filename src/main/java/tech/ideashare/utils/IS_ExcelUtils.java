package tech.ideashare.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tech.ideashare.model.BBG_Order;
import tech.ideashare.model.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IS_ExcelUtils {

    /**
     * 将一个EXCEL里面的内容转成对应类的ArrayList
     * 此方法只针对于  xls 有效
     *
     * @param fileInputStream
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> excelToListForXLS(InputStream fileInputStream, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        HashMap<String, String> indexMap = new HashMap<>();

        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            int rows = hssfSheet.getPhysicalNumberOfRows();

            //规定第1(引索是0)行是字段名行
            HSSFRow firstRow = hssfSheet.getRow(0);


            //遍历是从第2(引索是1)行开始
            for (int i = 1; i < rows; i++) {
                HSSFRow hssfRow = hssfSheet.getRow(i);
                if (hssfRow == null) {
                    continue;
                }
                for (int j = 0; j < hssfRow.getLastCellNum(); j++) {


                    //获取该单元格的列名
                    String  columnCellName = firstRow.getCell(j).getStringCellValue();

                    HSSFCell cell = hssfRow.getCell(j);
                    String value = "";
                    if (cell != null) {
                        switch (cell.getCellTypeEnum()) {
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;

                            case NUMERIC:
                                value = "" + cell.getNumericCellValue();
                                break;

                            case STRING:
                                value = cell.getStringCellValue();
                                break;

                            case BLANK:
                                value = "<BLANK>";
                                break;

                            case BOOLEAN:
                                value = "" + cell.getBooleanCellValue();
                                break;

                            case ERROR:
                                value = "" + cell.getErrorCellValue();
                                break;

                            default:
                                value = "" + cell.getCellTypeEnum();
                        }
                    }
                    indexMap.put(columnCellName,value);
                }
                //一行循环之后，将值注入到新生成的实例中，然后放到list中
                try {

                    T t = clazz.newInstance();

                    //获取到传入Class的全部变量名
                    Field[] fields = clazz.getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        String fieldName  = field.getName();
                        if(field.getType()==Integer.class){
                            if(indexMap.get(fieldName)!=null){
                                double d = Double.valueOf(indexMap.get(fieldName));
                                field.set(t, (int)d);
                            }

                        }else {
                            field.set(t, indexMap.get(fieldName));
                        }
                    }
                    list.add(t);

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }


    public static List<BBG_Order> getBbgOrders() {
        InputStream fileInputStream = IS_ExcelUtils.class.getClassLoader().getResourceAsStream("members.xls");
        List<BBG_Order> list = new ArrayList<>();

        try {
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
            int rows = hssfSheet.getPhysicalNumberOfRows();

            for (int i = 0; i < rows; i++) {

                HSSFRow hssfRow = hssfSheet.getRow(i);
                if (hssfRow == null) {
                    continue;
                }
                //开始遍历每一行的数据

                for (int j = 1; j < hssfRow.getLastCellNum(); j++) {
                    BBG_Order order = new BBG_Order();
                    order.setMemberId(Integer.valueOf(hssfRow.getCell(0).getStringCellValue()));
                    HSSFCell cell = hssfRow.getCell(j);
                    String value = "";
                    boolean flag = true;
                    if (cell != null) {
                        switch (cell.getCellTypeEnum()) {
                            case FORMULA:
                                value = "FORMULA value=" + cell.getCellFormula();
                                break;

                            case NUMERIC:
                                value = "NUMERIC value=" + cell.getNumericCellValue();
                                order.setBuyPrice(cell.getNumericCellValue());
                                break;

                            case STRING:
                                value = "STRING value=" + cell.getStringCellValue();
                                break;

                            case BLANK:
                                value = "<BLANK>";
                                flag = false;
                                break;

                            case BOOLEAN:
                                value = "BOOLEAN value-" + cell.getBooleanCellValue();
                                break;

                            case ERROR:
                                value = "ERROR value=" + cell.getErrorCellValue();
                                break;

                            default:
                                value = "UNKNOWN value of type " + cell.getCellTypeEnum();
                                flag = false;
                        }
                    }
                    if (flag) {
                        list.add(order);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        InputStream fileInputStream = IS_ExcelUtils.class.getClassLoader().getResourceAsStream("jinhuobiao.xls");
        excelToListForXLS(fileInputStream, Product.class);
    }
}
