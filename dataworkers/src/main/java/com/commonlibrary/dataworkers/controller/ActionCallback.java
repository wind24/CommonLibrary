package com.commonlibrary.dataworkers.controller;

/**
 * Created by huangzefeng on 20/9/16.
 */

public interface ActionCallback<T> {

    public void onResult(T result);

    public void onFailue(int code,String msg);

    public void onProgress(float percent);

}
