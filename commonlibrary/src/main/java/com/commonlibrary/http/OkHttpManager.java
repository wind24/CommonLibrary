package com.commonlibrary.http;

import java.util.concurrent.TimeUnit;
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
        if(mClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30000, TimeUnit.SECONDS);
            builder.readTimeout(30000, TimeUnit.SECONDS);
            mClient = new OkHttpClient();
        }

        return mClient;
    }

}
