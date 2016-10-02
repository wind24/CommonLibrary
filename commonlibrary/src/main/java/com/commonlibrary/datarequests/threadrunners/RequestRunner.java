package com.commonlibrary.datarequests.threadrunners;

import java.util.concurrent.Callable;

/**
 * Created by huangzefeng on 2016/10/2.
 */

public interface RequestRunner {

  public void submit(Runnable runnable);

}
