package com.commonlibrary.dataworkers.controller;

import com.commonlibrary.dataworkers.request.DataRequest;

/**
 * Created by huangzefeng on 20/9/16.
 */

public interface Controller<T> {

    public String controllerName();

    public Controller setRequest(DataRequest<T> request);

    public void subscribe(ActionCallback<T> callback);
}
