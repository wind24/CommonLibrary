package com.commonlibrary.datarequests.managers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huangzefeng on 9/9/16.
 */
public class ExecuteManager {

  private static ExecutorService singleExecutor;

  public static ExecutorService singleExe() {
    if (singleExecutor == null) {
      singleExecutor = Executors.newSingleThreadExecutor();
    }
    return singleExecutor;
  }
}
