package com.commonlibrary.datarequests.requests;

import com.commonlibrary.datarequests.OnRequestCallback;
import com.commonlibrary.datarequests.OnSubscribe;
import com.commonlibrary.datarequests.Requestable;
import com.commonlibrary.datarequests.threadrunners.SingleThreadRunner;
import com.commonlibrary.datarequests.threadrunners.UiThreadRunner;
import com.commonlibrary.http.HttpDataManager;
import com.commonlibrary.http.PostResponse;
import java.util.HashMap;

/**
 * Created by huangzefeng on 2016/10/2.
 */

public class HttpDataRequest {

  public final static String METHOD_POST = "post";
  public final static String METHOD_GET = "get";

  private String url;
  private HashMap<String, String> params;
  private HashMap<String, String> header;
  private String method;

  public static Builder newBuilder(String url) {
    Builder builder = new Builder(url);
    return builder;
  }

  public void execute(final OnRequestCallback<PostResponse> callback) {
    Requestable.create(new OnSubscribe<PostResponse>() {
      @Override public void onCall(OnRequestCallback<PostResponse> callback) {
        PostResponse response = null;
        if (method.equals(METHOD_GET)) {
          response = HttpDataManager.getInstance().getSupplier().getData(url, params, header, 0);
        } else if (method.equals(METHOD_POST)) {
          response = HttpDataManager.getInstance().getSupplier().postData(url, params, header, 0);
        }

        if (response != null) {
          if (response.getCode() >= 200 && response.getCode() <= 300) {
            callback.onResult(response);
          } else {
            callback.onError(response.getCode(), response.getMessage());
          }
        } else {
          callback.onError(-1, "response is empty");
        }
      }
    }).setRequestRunner(new SingleThreadRunner()).setCallbackRunner(new UiThreadRunner()).subscribe(callback);
  }

  public static class Builder {

    private String url;
    private HashMap<String, String> params;
    private HashMap<String, String> header;
    private String method;

    public Builder(String url) {
      this.url = url;
    }

    public Builder addParam(String key, String value) {
      if (params == null) params = new HashMap<>();

      params.put(key, value);
      return this;
    }

    public Builder addParams(HashMap<String, String> params) {
      if (this.params == null) this.params = new HashMap<>();

      this.params.putAll(params);
      return this;
    }

    public Builder setParams(HashMap<String, String> params) {
      this.params = params;
      return this;
    }

    public Builder addHeader(String key, String value) {
      if (header == null) header = new HashMap<>();

      header.put(key, value);
      return this;
    }

    public Builder addHeaders(HashMap<String, String> header) {
      if (this.header == null) this.header = new HashMap<>();

      this.header.putAll(header);
      return this;
    }

    public Builder setHeaders(HashMap<String, String> header) {
      this.header = header;
      return this;
    }

    public Builder post() {
      method = METHOD_POST;
      return this;
    }

    public Builder get() {
      method = METHOD_GET;
      return this;
    }

    public HttpDataRequest build() {
      HttpDataRequest request = new HttpDataRequest();
      request.url = url;
      request.params = params;
      request.header = header;
      request.method = method;
      return request;
    }
  }
}
