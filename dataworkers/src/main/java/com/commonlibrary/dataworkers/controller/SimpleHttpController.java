package com.commonlibrary.dataworkers.controller;

import com.commonlibrary.dataworkers.datasource.Datasource;
import com.commonlibrary.dataworkers.datasource.SimpleDataSource;
import com.commonlibrary.http.PostResponse;

import java.util.concurrent.Callable;

/**
 * Created by huangzefeng on 20/9/16.
 */

public class SimpleHttpController extends AbstractController<PostResponse> {

    @Override
    protected Datasource<PostResponse> generateDataSource() {
        Datasource<PostResponse> datasource = new SimpleDataSource<>();
        return datasource;
    }

    @Override
    public String controllerName() {
        return "SimpleHttp";
    }
}
