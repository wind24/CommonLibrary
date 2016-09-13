package com.datapresenter.controllers;

import com.datapresenter.datasource.DataSource;

/**
 * Created by huangzefeng on 13/9/16.
 */
public class AbstractController implements Controller {

    @Override
    public <T> DataSource<T> getDataSource() {
        return null;
    }

    @Override
    public <REQUEST> Controller executeRequest(REQUEST request) {
        DataSource dataSource = getDataSource();
        if(dataSource!=null){
            //进行请求构建操作
        }
        return this;
    }


    @Override
    public <ACTION> void onSubscribe(ACTION action) {
        //dataSource构建线程,获取请求结果并回调(实际执行占线程操作是在请求结果的时候触发,这个时候是在线程中操作).
    }
}
