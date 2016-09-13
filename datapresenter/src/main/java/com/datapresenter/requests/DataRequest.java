package com.datapresenter.requests;

/**
 * Created by huangzefeng on 13/9/16.
 */
public interface DataRequest<T> {

    public byte[] generateRequest();

    public T generateResponse(byte[] data);

}
