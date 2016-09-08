package com.commonlibrary.http;

import java.io.File;
import java.util.Map;

/**
 * Created by huangzefeng on 8/9/16.
 *
 * http内容的提供商,具体可以使用其他框架来实现相应的supplier.然后在{@link HttpDataManager}设置
 */
public interface HttpDataSupplier {

    public PostResponse getData(String url, Map<String,String> params,Map<String,String> headers,int timeout);

    public PostResponse postData(String url, Map<String,String> params, Map<String,String> headers, int timeout);

    public PostResponse postByteData(String url, byte[] data,Map<String,String> headers,int timeout);

    public PostResponse uploadFile(String url,String fileParam, File file, Map<String,String> params, Map<String,String> headers);

}
