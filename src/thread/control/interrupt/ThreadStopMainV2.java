package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV2 {
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("work stop, thread.interrupt()");
        thread.interrupt();
        log("work thread Interrupted 1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            log("work start");
            try {
                while (true) {
                    log("working");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                log("work thread Interrupted 2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message = " + e.getMessage());
                log("sate = " + Thread.currentThread().getState());
            }
            log("resource arrangement");
            log("work end");
        }
    }
}

