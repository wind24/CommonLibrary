package com.commonlibrary.http;

/**
 * Created by huangzefeng on 8/9/16.
 */
public interface UploadFileCallback {

    public void onResponse(String msg);

    public void onFailue(int code, String msg);

}
