package com.datapresenter.controllers;


import com.datapresenter.datasource.DataSource;

/**
 * Created by huangzefeng on 13/9/16.
 */
public interface Controller {

    public <T> DataSource<T> getDataSource();

    public <REQUEST> Controller executeRequest(REQUEST request);

    public <ACTION> void onSubscribe(ACTION action);

}
