package com.commonlibrary.dataworkers.datasource;

import java.util.concurrent.Callable;

/**
 * Created by huangzefeng on 19/9/16.
 */
public interface Datasource<T> {

    public void subscribe(Callable<T> callable);

    public void cancel();

    public boolean isCancel();

    public T getResult();
}
