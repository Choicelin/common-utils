package tech.ideashare.utils;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by lixiang on 14/07/2017.
 */
public class IS_ListUtils {


    /**
     * 从list中随机返回一个值（效率未验）
     * @param list
     * @param <T>
     * @return
     */
    public static <T> T getRandomValueFromList(List<T> list){

        return list.get(new Random().nextInt(list.size()));

    }

    /**
     * 向list中添加一条数据，并返回添加的Index值（只限于ArrayList）
     * @param list
     * @param t
     * @param <T>
     * @return
     */
    public static <T> int setReturnIndex(ArrayList<T> list, T t){
        list.add(t);
        return list.indexOf(t);
    }

}
