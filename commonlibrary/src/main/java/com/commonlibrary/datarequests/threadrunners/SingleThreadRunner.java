package com.commonlibrary.datarequests.threadrunners;

import com.commonlibrary.datarequests.managers.ExecuteManager;

/**
 * Created by huangzefeng on 2016/10/2.
 */
public class SingleThreadRunner implements RequestRunner {

  @Override public void submit(Runnable runnable) {
    ExecuteManager.singleExe().submit(runnable);
  }
}
