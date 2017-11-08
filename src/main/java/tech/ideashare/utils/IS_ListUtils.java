package tech.ideashare.utils;

import javafx.collections.ObservableList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by lixiang on 14/07/2017.
 */
public class IS_ListUtils {


    /**
     * List根据某一个属性去重
     *        List<ProductAttribute> colorList
     *        =allAttr.stream()
     *          .filter(IS_ListUtils.distinctByKey(ProductAttribute::getColorCode))
     *          .collect(Collectors.toList());
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

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
