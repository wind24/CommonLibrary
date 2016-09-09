package com.commonlibrary.datasource;

import com.commonlibrary.presentes.DataRequest;

/**
 * Created by huangzefeng on 9/9/16.
 */
public interface DataSource<T> {

    public void subscribe(DataRequest<T> request);

    public T getResult();

}
