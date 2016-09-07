package com.commonlibrary.http;

import okhttp3.OkHttpClient;

/**
 * Created by huangzefeng on 6/9/16.
 */
public class OkHttpManager {

    private static OkHttpManager instance;

    public static OkHttpManager getInstance(){
        if(instance == null)
            instance = new OkHttpManager();

        return instance;
    }

    private OkHttpClient mClient;

    public OkHttpClient getOkHttpClient(){
        if(mClient == null)
            mClient = new OkHttpClient();

        return mClient;
    }

}
