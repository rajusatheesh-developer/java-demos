package com.demos.java.profiling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo1
{
    public static void main(String[] args) {

        int coreCount=Runtime.getRuntime().availableProcessors();
        System.out.println("CPU Core Count : "+coreCount);
        ExecutorService service= Executors.newFixedThreadPool(coreCount);

        service.execute(new CpuIntensiveRunnable());

    }

    static class CpuIntensiveRunnable implements Runnable
    {

        @Override
        public void run() {
            System.out.println("Thread :"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
