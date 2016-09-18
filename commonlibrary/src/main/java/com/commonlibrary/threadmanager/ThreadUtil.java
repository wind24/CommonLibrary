package com.commonlibrary.threadmanager;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by huangzefeng on 16/9/18.
 */
public class ThreadUtil {

  private static Handler handler;

  public static void runInUIThread(Runnable runnable){
    if(handler == null) {
      handler = new Handler(Looper.getMainLooper());
    }

    handler.post(runnable);
  }

}
