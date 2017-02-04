package com.commonlibrary.dataworkers.datasource;

import com.commonlibrary.dataworkers.request.DataRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created by huangzefeng on 20/9/16.
 */

public class SimpleDataSource<T> implements Datasource<T> {

    private FutureTask<T> futureTask;


    @Override
    public void bindRequest(DataRequest<T> request) {
        //生成请求数据和futureTask
    }

    @Override
    public void subscribe(DatasourceSubscriber<T> subscriber, ExecutorService service) {
        //提交请求并回调
    }

    @Override
    public void cancel() {
        if (futureTask != null && !futureTask.isCancelled()) {
            futureTask.cancel(true);
        }
    }

    @Override
    public boolean isCancel() {
        return futureTask != null && !futureTask.isCancelled();
    }


    @Override
    public boolean isDone() {
        if (futureTask != null) {
            return futureTask.isDone();
        }
        return false;
    }
}
