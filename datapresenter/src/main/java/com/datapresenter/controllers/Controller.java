package com.datapresenter.controllers;

import com.commonlibrary.presentes.DataRequest;

/**
 * Created by huangzefeng on 13/9/16.
 */
public interface Controller {

    public void executeRequest(DataRequest request);

    public <ACTION> void onSubscribe(ACTION action);

}
