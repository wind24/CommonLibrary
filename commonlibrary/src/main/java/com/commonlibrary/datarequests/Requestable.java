package com.commonlibrary.datarequests;

import com.commonlibrary.datarequests.threadrunners.RequestRunner;

/**
 * Created by huangzefeng on 2016/10/2.
 */

public class Requestable<T> {

  public static <T> Requestable<T> create(OnSubscribe<T> subscribe) {
    Requestable<T> requestable = new Requestable<>(subscribe);
    return requestable;
  }

  private OnSubscribe<T> subscribe;
  private RequestRunner runner;
  private RequestRunner callbackRunner;
  private OnRequestCallback<T> finalCallback;

  protected Requestable(OnSubscribe<T> subscribe) {
    this.subscribe = subscribe;
  }

  public Requestable setRequestRunner(RequestRunner runner) {
    this.runner = runner;
    return this;
  }

  public Requestable setCallbackRunner(RequestRunner runner) {
    callbackRunner = runner;
    return this;
  }

  public void subscribe(final OnRequestCallback<T> callback) {
    finalCallback = callback;
    if (callbackRunner != null) {
      finalCallback = generateFinalCallback(callback);
    }
    if (callback != null && subscribe != null) {
      if (runner != null) {
        runner.submit(new Runnable() {
          @Override public void run() {
            subscribe.onCall(finalCallback);
          }
        });
      } else {
        subscribe.onCall(finalCallback);
      }
    }
  }

  private OnRequestCallback<T> generateFinalCallback(final OnRequestCallback<T> callback) {
    OnRequestCallback<T> finalCallback;
    finalCallback = new OnRequestCallback<T>() {
      @Override public void onResult(final T result) {
        callbackRunner.submit(new Runnable() {
          @Override public void run() {
            callback.onResult(result);
          }
        });
      }

      @Override public void onError(final int code, final String msg) {
        callbackRunner.submit(new Runnable() {
          @Override public void run() {
            callback.onError(code, msg);
          }
        });
      }
    };

    return finalCallback;
  }
}
