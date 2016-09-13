package com.wolsx.summarize.javabasic.proxy.static_proxy;

/**
 * Created by hy on 6/27/16.
 */
/**
 * 代理接口。处理给定名字的任务。
 */
public interface Subject {
    /**
     * 执行给定名字的任务。
     * @param taskName 任务名
     */
    public void dealTask(String taskName);
}