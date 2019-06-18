package com.miku.advs.core.api;

import com.miku.advs.core.util.ThreadPoolUtil;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;

/**
 * Created by hp on 2019/4/29.
 */
public class ApiThreadManager {

    private static final ApiThreadManager apiThreadManager = new ApiThreadManager();

    private ExecutorService executorService;

    private ApiThreadManager(){}

    public static ApiThreadManager getInstance(){
        return apiThreadManager;
    }

    public void execute(TimerTask task){
        executorService = executorService != null?executorService: ThreadPoolUtil.getCachedThreadPool();
        executorService.execute(task);
    }

}
