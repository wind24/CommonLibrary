package com.commonlibrary.datarequests;

/**
 * Created by huangzefeng on 2016/10/2.
 */

public interface OnSubscribe<T> {

  public void onCall(OnRequestCallback<T> callback);

}
