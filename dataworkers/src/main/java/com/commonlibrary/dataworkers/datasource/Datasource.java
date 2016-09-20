package com.commonlibrary.dataworkers.datasource;

import com.commonlibrary.dataworkers.request.DataRequest;

import java.util.concurrent.ExecutorService;

/**
 * Created by huangzefeng on 19/9/16.
 */
public interface Datasource<T> {

    public void bindRequest(DataRequest<T> request);

    public void subscribe(DatasourceSubscriber<T> subscriber,ExecutorService service);

    public void cancel();

    public boolean isCancel();

    public boolean isDone();
}
