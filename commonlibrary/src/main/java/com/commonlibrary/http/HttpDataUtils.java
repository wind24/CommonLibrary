package com.commonlibrary.http;

import android.util.Log;

import com.commonlibrary.base.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huangzefeng on 6/9/16.
 */
public class HttpDataUtils {

    private final static String TAG = "HttpDataUtils";

    public static byte[] getByteData(String url, HashMap<String, String> params, HashMap<String, String> headers,int timeout){
        Response response = getData(url,params,headers,timeout);
        if(response!=null && response.isSuccessful()){
            try {
                return response.body().bytes();
            } catch (IOException e) {
                Log.e(TAG, "getData request error", e);
            }
        }
        return null;
    }

    public static InputStream getStreamData(String url, HashMap<String, String> params, HashMap<String, String> headers,int timeout) {
        Response response = getData(url,params,headers,timeout);
        if(response!=null && response.isSuccessful()){
            return response.body().byteStream();
        }
        return null;
    }

  /**
   * get the data from net
   *
   * @param url
   * @param params
   * @param headers
   * @param timeout
   * @return
   */
    public static Response getData(String url, HashMap<String, String> params, HashMap<String, String> headers,int timeout) {
        Log.d(TAG, "getData:" + url);

        if (StringUtils.isEmpty(url))
            throw new NullPointerException("The request url must not be null.");

        StringBuffer buffer = new StringBuffer(url);
        if (params != null && params.size() > 0) {
            if (buffer.lastIndexOf("?") == -1) {
                buffer.append("?");
            }

            try {
                for (String name : params.keySet()) {
                    buffer.append(URLEncoder.encode(name, "utf-8"));
                    buffer.append("=");
                    buffer.append(URLEncoder.encode(params.get(name), "utf-8"));
                    buffer.append("&");
                }
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "getData generate url error", e);
            }
        }

        Log.d(TAG, "getData generated url:" + buffer.toString());

        Request.Builder builder = new Request.Builder().url(buffer.toString());
        if (headers != null && headers.size() > 0) {
            for (String name : headers.keySet()) {
                builder = builder.header(name, headers.get(name));
            }
        }
        Request request = builder.build();
        try {
            Response response = OkHttpManager.getInstance().getOkHttpClient().newCall(request).execute();
            Log.d(TAG, "getData response code=" + response.code() + ",content-length=" + response.body().contentLength());
            if (response.isSuccessful())
                return response;
        } catch (IOException e) {
            Log.e(TAG, "getData request error", e);
        }

        Log.d(TAG, "getData response error");
        return null;
    }

}
