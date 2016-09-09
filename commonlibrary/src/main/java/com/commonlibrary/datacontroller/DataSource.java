package com.commonlibrary.datacontroller;

import java.util.concurrent.Callable;

/**
 * Created by huangzefeng on 9/9/16.
 */
public interface DataSource<T> {

    public void subscribe(Callable<T> action);

    public T getResult();

}
