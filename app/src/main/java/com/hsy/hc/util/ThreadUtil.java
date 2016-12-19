package com.hsy.hc.util;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaojiaxing_name on 16/9/8.
 */
public class ThreadUtil {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(5);
}
