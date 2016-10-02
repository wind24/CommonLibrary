package com.commonlibrary.datarequests;

/**
 * Created by huangzefeng on 2016/10/2.
 */

public interface OnRequestCallback<T> {

  public void onResult(T result);

  public void onError(int code, String msg);
}
