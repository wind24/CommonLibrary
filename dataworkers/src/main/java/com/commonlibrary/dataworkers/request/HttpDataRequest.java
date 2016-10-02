package com.commonlibrary.dataworkers.request;

import android.util.Log;

import com.commonlibrary.http.HttpDataManager;
import com.commonlibrary.http.PostResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangzefeng on 9/9/16.
 */
public class HttpDataRequest implements DataRequest<PostResponse> {

    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    public static Builder newBuilder(){
        return new Builder();
    }

    @Override
    public PostResponse generateResponse() {
        long start = System.currentTimeMillis();
        PostResponse response = HttpDataManager.getInstance().getSupplier().postData(url, null, null,30000);
        Log.d("Test","generate Time:"+(System.currentTimeMillis() - start));
        return response;
    }

    public static class Builder {
        private String url;
        private Map<String, String> params;
        private Map<String, String> headers;

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setParams(Map<String, String> params) {
            this.params = params;
            return this;
        }

        public Builder setHeaders(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder putParam(String key, String value) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put(key, value);
            return this;
        }

        public Builder putHeader(String key, String value) {
            if (headers == null) {
                headers = new HashMap<>();
            }

            headers.put(key, value);
            return this;
        }

        public HttpDataRequest build(){
            HttpDataRequest request = new HttpDataRequest();
            request.url = url;
            request.params = params;
            request.headers = headers;
            return request;
        }

    }
}
