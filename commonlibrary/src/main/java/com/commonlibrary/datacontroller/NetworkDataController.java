package com.commonlibrary.datacontroller;

import com.commonlibrary.presentes.DataRequest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by huangzefeng on 9/9/16.
 */
public class NetworkDataController<T> extends SimpleDataController<T> {

    public static <T> NetworkDataSource<T> createDataSource(final DataRequest<T> request) {
        NetworkDataController.NetworkDataSource<T> source = new NetworkDataController.NetworkDataSource<>();
        source.subscribe(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return request.generateRequest();
            }
        });

        return source;
    }

    public static class NetworkDataSource<T> implements DataSource<T> {

        private FutureTask<T> futureTask;

        @Override
        public void subscribe(Callable<T> action) {
            ExecutorService threadPool = Executors.newSingleThreadExecutor();
            futureTask = (FutureTask<T>) threadPool.submit(action);
        }

        @Override
        public T getResult() {
            if (futureTask != null) {
                try {
                    return futureTask.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}
