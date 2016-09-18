package com.datapresenter.observables;

import com.datapresenter.datarequests.DataRequest;

/**
 * Created by huangzefeng on 16/9/18.
 */
public interface TaskController<V> {

  public TaskController callwith(DataRequest<V> request);

  public void subscribe(Callback<V> callback);

}
