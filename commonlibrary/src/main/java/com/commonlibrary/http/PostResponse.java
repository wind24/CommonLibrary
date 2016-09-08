package com.commonlibrary.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangzefeng on 8/9/16.
 */
public class PostResponse {

    private int code;
    private String message;
    private byte[] data;
    private Map<String, String> responseHeaders;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void putResponseHeader(String key, String value) {
        if (responseHeaders == null)
            responseHeaders = new HashMap<>();

        responseHeaders.put(key, value);
    }

    public String getHeader(String key){
        if(responseHeaders!=null){
            return responseHeaders.get(key);
        }

        return null;
    }

    public boolean isSuccess(){
        return code >= 200 && code < 300;
    }

}
