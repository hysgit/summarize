package com.wolsx.summarize.concurrent.four;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hy on 2/16/17.
 */
public class ThreadR {

    private static String getFirstResult(String question, List<String> engines) {
        AtomicReference<String> result = new AtomicReference<>();
        for (String base : engines) {
            String url = base + question;
            new Thread(() -> {
                //result.compareAndSet(null, WS.url(url).get());
            }).start();
        }
        while (result.get() == null) ; // wait for some result to appear
        return result.get();
    }

    private static String getFirstResultExecutors(String question, List<String> engines) {
        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(4));

        for (String base : engines) {
            String url = base + question;
            service.submit(() -> {
                //return WS.url(url).get();
                return "";
            });
        }
        try {
            return service.take().get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    private static String getFirstResultForkJoinPool(String question, List<String> engines) {
        // get element as soon as it is available
        Optional<String> result = engines.stream().parallel().map((base) -> {
            String url = base + question;
            //return WS.url(url).get();
            return "";
        }).findAny();

        return result.get();
    }


}
