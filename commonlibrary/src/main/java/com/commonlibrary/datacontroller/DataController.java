package com.commonlibrary.datacontroller;

/**
 * Created by huangzefeng on 9/9/16.
 */
public interface DataController<T> {

    public T get(DataSource<T> source);

}
