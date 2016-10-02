package com.commonlibrary.dataworkers.datasource;

/**
 * Created by huangzefeng on 20/9/16.
 */

public interface DatasourceSubscriber<T> {

    public void onResult(Datasource<T> datasource);

}
