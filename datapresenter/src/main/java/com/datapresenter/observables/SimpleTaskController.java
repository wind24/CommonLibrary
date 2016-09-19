package com.datapresenter.observables;

import android.util.Log;
import com.commonlibrary.threadmanager.ThreadUtil;
import com.datapresenter.datarequests.DataRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created by huangzefeng on 16/9/18.
 */
public class SimpleTaskController<T> implements TaskController<T> {

  private final static String TAG = "SimpleTaskController";

  private ExecutorService executorService;
  private Callable<T> callable;

  public SimpleTaskController(ExecutorService executorService) {
    this.executorService = executorService;
  }

  @Override public TaskController callwith(final DataRequest<T> request) {
    callable = new Callable<T>() {
      @Override public T call() throws Exception {
        T result = request.generateRequest();
        return result;
      }
    };

    return this;
  }

  @Override public void subscribe(final Callback<T> callback) {
    FutureTask<T> future = new FutureTask<T>(callable) {
      @Override protected void done() {
        super.done();
        try {
          final T response = get();
          ThreadUtil.runInUIThread(new Runnable() {
            @Override public void run() {
              if (callback != null) {
                callback.onResult(response);
              }
            }
          });
        } catch (InterruptedException e) {
          e.printStackTrace();
          callbackFailue(-1, e.getMessage(), callback);
        } catch (ExecutionException e) {
          e.printStackTrace();
          callbackFailue(-2, e.getMessage(), callback);
        }
      }
    };
    if (executorService != null) {
      executorService.execute(future);
    }
  }

  private void callbackFailue(final int code, final String msg, final Callback<T> callback) {
    ThreadUtil.runInUIThread(new Runnable() {
      @Override public void run() {
        if (callback != null) {
          callback.onFailue(code, msg);
        }
      }
    });
  }
}
