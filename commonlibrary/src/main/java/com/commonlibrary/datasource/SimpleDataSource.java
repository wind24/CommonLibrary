package com.commonlibrary.datasource;

import com.commonlibrary.presentes.DataRequest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created by huangzefeng on 9/9/16.
 */
public class SimpleDataSource<T> implements DataSource<T> {

    private FutureTask<T> futureTask;
    private ExecutorService executorService;

    public SimpleDataSource(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void subscribe(final DataRequest<T> request) {
        Callable<T> callable = new Callable<T>() {
            @Override
            public T call() throws Exception {
                return request.generateRequest();
            }
        };

        if (executorService != null)
            futureTask = (FutureTask<T>) executorService.submit(callable);
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
