package com.qmtec.servicecore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test1 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            executorService.execute(new TaskThread(i));
        }

    }

    static class TaskThread implements Runnable{

        Integer taskId;

        public TaskThread(Integer taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            InheritableThreadLocalUtils.set(taskId);
            System.out.println("pre id :=" + taskId );
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("chilere get pre id := " + InheritableThreadLocalUtils.get());

                    int ch = taskId + 10;
                    InheritableThreadLocalUtils.set(ch);
                    System.out.println("childre id :=" + ch );

                    ExecutorService executor2 = Executors.newSingleThreadExecutor();
                    executor2.execute(new Runnable() {
                        @Override
                        public void run() {

                            System.out.println("chileres get id := " + InheritableThreadLocalUtils.get());
                        }
                    });


                }
            });
        }
    }
}
