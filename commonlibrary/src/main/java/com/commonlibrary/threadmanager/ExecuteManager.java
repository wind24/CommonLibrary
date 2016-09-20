package com.commonlibrary.threadmanager;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangzefeng on 9/9/16.
 */
public class ExecuteManager {

    private static HashMap<String,ExecutorService> executorServices;

    public static synchronized ExecutorService getCacheExeService(String key){
        if(executorServices == null){
            executorServices = new HashMap<>();
        }

        ExecutorService service = null;
        if(!executorServices.containsKey(key)) {
            service = Executors.newCachedThreadPool();
            executorServices.put(key,service);
        }

        service = executorServices.get(key);
        return service;
    }

}
