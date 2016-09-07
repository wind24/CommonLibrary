package com.commonlibrary.http;

import android.util.Log;

import com.commonlibrary.base.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huangzefeng on 6/9/16.
 */
public class HttpDataUtils {

    private final static String TAG = "HttpDataUtils";

    public static byte[] getByteData(String url, HashMap<String, String> params, HashMap<String, String> headers){
        InputStream stream = getData(url,params,headers);
        if(stream!=null){
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                int ret = 0;
                while (((ret = stream.read(buffer)))!=0){
                    baos.write(buffer,0,ret);
                }

                byte[] data = baos.toByteArray();
                baos.flush();
                baos.close();
                stream.close();
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream getData(String url, HashMap<String, String> params, HashMap<String, String> headers) {
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
                return response.body().byteStream();
        } catch (IOException e) {
            Log.e(TAG, "getData request error", e);
        }

        Log.d(TAG, "getData response error");
        return null;
    }

}
