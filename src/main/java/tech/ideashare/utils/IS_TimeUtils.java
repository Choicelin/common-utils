package tech.ideashare.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IS_TimeUtils {

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        System.out.println(dateFormat.format(new Date()));
    }
}
