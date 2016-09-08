package com.commonlibrary.http;

import android.util.Log;

import com.commonlibrary.base.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by huangzefeng on 8/9/16.
 */
public class OkHttpDataSupplier implements HttpDataSupplier {

    private static final String TAG = "OkHttpDataSupplier";

    @Override
    public PostResponse getData(String url, Map<String, String> params, Map<String, String> headers, int timeout) {
        Log.d(TAG, "getData:" + url);
        PostResponse pr = new PostResponse();
        if (StringUtils.isEmpty(url)) {
            pr.setCode(-1);
            pr.setMessage("The request url must not be null.");
        }

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
                pr.setCode(-2);
                pr.setMessage("getData generate url error");
            }
        }

        Log.d(TAG, "getData generated url:" + buffer.toString());

        Request.Builder builder = new Request.Builder().url(buffer.toString());
        if (headers != null && headers.size() > 0) {
            //headerBuffer for log
            StringBuffer headerBuffer = new StringBuffer("[");
            for (String name : headers.keySet()) {
                String value = headers.get(name);
                builder = builder.header(name, value);

                headerBuffer.append(name);
                headerBuffer.append("=");
                headerBuffer.append(value);
                headerBuffer.append(",");
            }
            headerBuffer.append("]");
            Log.d(TAG, "postData headers:" + headerBuffer);
        }
        Request request = builder.build();
        try {
            Response response = OkHttpManager.getInstance().getOkHttpClient().newCall(request).execute();
            Log.d(TAG, "getData response code=" + response.code());
            pr = generateResponse(response);
        } catch (IOException e) {
            Log.e(TAG, "getData request error", e);
            pr.setCode(-3);
            pr.setMessage("getData request error");
        }

        return pr;
    }


    @Override
    public PostResponse postData(String url, Map<String, String> params, Map<String, String> headers, int timeout) {
        Log.d(TAG, "postData:" + url);

        PostResponse pr = new PostResponse();
        if (StringUtils.isEmpty(url)) {
            pr.setCode(-1);
            pr.setMessage("The request url must not be null.");
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            //headerBuffer for log
            StringBuffer headerBuffer = new StringBuffer("[");
            for (String name : headers.keySet()) {
                String value = headers.get(name);
                builder = builder.header(name, value);

                headerBuffer.append(name);
                headerBuffer.append("=");
                headerBuffer.append(value);
                headerBuffer.append(",");
            }
            headerBuffer.append("]");
            Log.d(TAG, "postData headers:" + headerBuffer);
        }
        if (params != null) {
            //paramsBuffer for log params
            StringBuffer paramsBuffer = new StringBuffer("[");
            FormBody.Builder form = new FormBody.Builder();
            for (String key : params.keySet()) {
                String value = params.get(key);
                form = form.add(key, value);

                paramsBuffer.append(key);
                paramsBuffer.append("=");
                paramsBuffer.append(value);
                paramsBuffer.append(",");
            }
            paramsBuffer.append("]");
            Log.d(TAG, "postData params:" + paramsBuffer);
            builder = builder.post(form.build());
        }
        Request request = builder.build();
        try {
            Response response = OkHttpManager.getInstance().getOkHttpClient().newCall(request).execute();
            Log.d(TAG, "postData response code=" + response.code());
            pr = generateResponse(response);
        } catch (IOException e) {
            Log.e(TAG, "postData request error", e);
            pr.setCode(-3);
            pr.setMessage("getData request error");
        }
        return pr;
    }

    @Override
    public PostResponse postByteData(String url, byte[] data, Map<String, String> headers, int timeout) {

        Log.d(TAG, "postData:" + url);

        PostResponse pr = new PostResponse();
        if (StringUtils.isEmpty(url)) {
            pr.setCode(-1);
            pr.setMessage("The request url must not be null.");
        }
        RequestBody body = new ProtoBufRequestBody(data);

        Request.Builder builder = new Request.Builder().url(url);
        if (headers != null && headers.size() > 0) {
            //headerBuffer for log
            StringBuffer headerBuffer = new StringBuffer("[");
            for (String name : headers.keySet()) {
                String value = headers.get(name);
                builder = builder.header(name, value);

                headerBuffer.append(name);
                headerBuffer.append("=");
                headerBuffer.append(value);
                headerBuffer.append(",");
            }
            headerBuffer.append("]");
            Log.d(TAG, "postData headers:" + headerBuffer);
        }
        Request request = builder.post(body).build();
        try {
            Response response = OkHttpManager.getInstance().getOkHttpClient().newCall(request).execute();
            Log.d(TAG, "postData response code=" + response.code());
            pr = generateResponse(response);
        } catch (IOException e) {
            Log.e(TAG, "postData request error", e);
            pr.setCode(-3);
            pr.setMessage("getData request error");
        }
        return pr;
    }

    /**
     * @param url
     * @param fileParam 文件的参数名
     * @param file
     * @param params
     * @param headers
     * @return
     */
    @Override
    public PostResponse uploadFile(String url, String fileParam, File file, Map<String, String> params, Map<String, String> headers) {
        Log.d(TAG, "uploadFile url=" + url + ",file=" + file);
        PostResponse pr = new PostResponse();
        if (StringUtils.isEmpty(url)) {
            pr.setCode(-1);
            pr.setMessage("The request url must not be null.");
        }
        if (file == null || !file.exists()) {
            pr.setCode(-2);
            pr.setMessage("The uploaded file is not exists or null.");
        }
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"" + fileParam + "\"; filename=\"" + file.getAbsolutePath() + "\""), fileBody);

        if (params != null && params.size() > 0) {
            StringBuffer paramsBuffer = new StringBuffer("[");
            for (String key : params.keySet()) {
                String value = params.get(key);
                bodyBuilder = bodyBuilder.addFormDataPart(key, value);
                paramsBuffer.append(key);
                paramsBuffer.append("=");
                paramsBuffer.append(value);
                paramsBuffer.append(",");
            }

            paramsBuffer.append("]");
            Log.d(TAG, "uploadFile params:" + paramsBuffer);
        }

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(bodyBuilder.build());
        if (headers != null && headers.size() > 0) {
            //headerBuffer for log
            StringBuffer headerBuffer = new StringBuffer("[");
            for (String name : headers.keySet()) {
                String value = headers.get(name);
                builder = builder.header(name, value);

                headerBuffer.append(name);
                headerBuffer.append("=");
                headerBuffer.append(value);
                headerBuffer.append(",");
            }
            headerBuffer.append("]");
            Log.d(TAG, "uploadFile headers:" + headerBuffer);
        }

        Call call = OkHttpManager.getInstance().getOkHttpClient().newCall(builder.build());
        try {
            Response response = call.execute();
            Log.d(TAG, "uploadFile response code=" + response.code() + ",message=" + response.message());
            pr = generateResponse(response);
        } catch (IOException e) {
            Log.e(TAG, "uploadFile failue ioexception", e);
            pr.setCode(-3);
            pr.setMessage("uploadFile failue ioexception:" + e.getMessage());
        }

        return pr;
    }

    private PostResponse generateResponse(Response response) throws IOException {
        PostResponse pr = new PostResponse();
        pr.setCode(response.code());
        pr.setMessage(response.message());
        if (response.isSuccessful()) {
            pr.setData(response.body().bytes());
        }
        for (String key : response.headers().names()) {
            pr.putResponseHeader(key, response.header(key));
        }

        return pr;
    }
}
