package com.commonlibrary.presentes;

import java.util.Map;

/**
 * Created by huangzefeng on 9/9/16.
 */
public abstract class DataRequest<T> {

    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    public abstract T generateRequest();

}
