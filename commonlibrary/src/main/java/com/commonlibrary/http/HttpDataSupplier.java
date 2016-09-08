package com.commonlibrary.http;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by huangzefeng on 8/9/16.
 *
 * http内容的提供商,具体可以使用其他框架来实现相应的supplier.然后在{@link HttpDataManager}设置
 */
public interface HttpDataSupplier {

    public byte[] getData(String url, Map<String,String> params,Map<String,String> headers,int timeout);

    public byte[] postData(String url, Map<String,String> params, Map<String,String> headers, int timeout);

    public byte[] uploadFile(String url, File file, Map<String,String> params, Map<String,String> headers);

    public byte[] uploadFiles(String url, List<File> files, Map<String,String> params, Map<String,String> headers);
}
