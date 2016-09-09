package com.commonlibrary.datacontroller;

/**
 * Created by huangzefeng on 9/9/16.
 */
public abstract class SimpleDataController<T> implements DataController<T> {

    @Override
    public T get(DataSource<T> source) {
        return source.getResult();
    }
}
