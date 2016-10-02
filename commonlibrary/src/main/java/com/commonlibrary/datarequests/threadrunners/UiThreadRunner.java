package com.commonlibrary.datarequests.threadrunners;

import com.commonlibrary.utils.ThreadUtil;

/**
 * Created by huangzefeng on 2016/10/2.
 */

public class UiThreadRunner implements RequestRunner {
  @Override public void submit(Runnable runnable) {
    ThreadUtil.runInUIThread(runnable);
  }
}
