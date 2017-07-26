package tech.ideashare.model.wrapper;

import java.util.List;

/**
 * Created by lixiang on 11/07/2017.
 */
public abstract class BaseWrapper<T> {


    public abstract List<T> getList() ;

    public abstract void setList(List<T> list);
}
