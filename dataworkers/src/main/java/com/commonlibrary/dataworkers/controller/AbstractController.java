package com.commonlibrary.dataworkers.controller;

import com.commonlibrary.dataworkers.datasource.Datasource;
import com.commonlibrary.dataworkers.request.DataRequest;
import com.commonlibrary.threadmanager.ExecuteManager;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Created by huangzefeng on 20/9/16.
 */

public abstract class AbstractController<T> implements Controller<T> {

    private DataRequest<T> request;

    @Override
    public Controller setRequest(final DataRequest<T> request) {
        this.request = request;
        return this;
    }

    protected abstract Datasource<T> generateDataSource();

    @Override
    public void subscribe(final ActionCallback<T> callback) {
        ExecutorService service = ExecuteManager.getCacheExeService(controllerName());
        if (service != null) {
            Datasource<T> datasource = generateDataSource();
            datasource.subscribe(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    return request.generateResponse();
                }
            },service);
        }
    }
}
