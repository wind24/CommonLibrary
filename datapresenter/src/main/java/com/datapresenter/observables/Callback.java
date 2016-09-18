package com.datapresenter.observables;

/**
 * Created by huangzefeng on 16/9/18.
 */
public interface Callback<V> {

  public void onResult(V result);

  public void onFailue(int code,String msg);

  public void onProgress(float percent);

}
