package com.mrk.mrkplayer.threadpool;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.mrk.mrkplayer.threadpool.RealCall.AsyncCall;
import com.mrk.mrkplayer.util.Util;

public final class Dispatcher {
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;

    private ExecutorService executorService;
    private final Deque<AsyncCall> readyAsyncCalls = new ArrayDeque<>();
    private final Deque<AsyncCall> runningAsyncCalls = new ArrayDeque<>();
    private final Deque<RealCall> runningSyncCalls = new ArrayDeque<>();

    public Dispatcher() {
    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), Util.threadFactory("ThreadPool Dispatcher", false));
        }

        return executorService;
    }

//    public synchronized void setMaxRequests(int maxRequests) {
//        if (maxRequests < 1) {
//            throw new IllegalArgumentException("max < 1: " + maxRequests);
//        }
//
//        this.maxRequests = maxRequests;
//        promoteCalls();
//    }

//    public synchronized int getMaxRequests() {
//        return maxRequests;
//    }

//    public synchronized void setMaxRequestsPerHost(int maxRequestsPerHost) {
//        if (maxRequestsPerHost < 1) {
//            throw new IllegalArgumentException("max < 1: " + maxRequestsPerHost);
//        }
//
//        this.maxRequestsPerHost = maxRequestsPerHost;
//        promoteCalls();
//    }

//    public synchronized int getMaxRequestsPerHost() {
//        return maxRequestsPerHost;
//    }

    synchronized void enqueue(AsyncCall call) {
        if (runningAsyncCalls.size() < maxRequests) {
            runningAsyncCalls.add(call);
            executorService().execute(call);
        } else {
            readyAsyncCalls.add(call);
        }
    }

    private void promoteCalls() {
        if (runningAsyncCalls.size() >= maxRequests) return;
        if (readyAsyncCalls.isEmpty()) return;

        for (Iterator<AsyncCall> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
            AsyncCall call = i.next();

            if (maxRequestsPerHost < 6) {
                i.remove();
                runningAsyncCalls.add(call);
                executorService().execute(call);
            }

            if (runningAsyncCalls.size() >= maxRequests) return;
        }
    }

    synchronized void executed(RealCall call) {
        runningSyncCalls.add(call);
    }

    void finished(RealCall.AsyncCall call) {
        finished(runningAsyncCalls, call, true);
    }

    void finished(RealCall call) {
        finished(runningSyncCalls, call, false);
    }

    private <T> void finished(Deque<T> calls, T call, boolean promoteCalls) {
        int runningCallsCount;

        synchronized (this) {
            if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");
            if (promoteCalls) promoteCalls();
            runningCallsCount = runningCallsCount();
        }
    }

    public synchronized int runningCallsCount() {
        return runningAsyncCalls.size() + runningSyncCalls.size();
    }

}
