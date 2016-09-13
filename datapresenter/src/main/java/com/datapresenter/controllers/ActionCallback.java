package com.datapresenter.controllers;

/**
 * Created by huangzefeng on 13/9/16.
 */
public interface ActionCallback<T> {

    public void onResult(T result);

    public void onProgress(float percent);

    public void onFailue(int code,String msg);

}
