package com.commonlibrary.http;

/**
 * Created by huangzefeng on 8/9/16.
 *
 * 外接给外面的http请求工具类,可以指定supplier,默认使用okhttp的supplier
 *
 * {@link HttpDataSupplier}
 */
public class HttpDataManager {

    private static HttpDataManager instance;

    public static HttpDataManager getInstance(){
        if(instance == null){
            instance = new HttpDataManager();
        }

        return instance;
    }

    private HttpDataSupplier supplier;

    public HttpDataSupplier getSupplier(){
        if(supplier == null){
            supplier = new OkHttpDataSupplier();
        }

        return supplier;
    }

    public void setSupplier(HttpDataSupplier supplier){
        this.supplier = supplier;
    }

}
