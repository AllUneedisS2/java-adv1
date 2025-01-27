package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("work stop, thread.interrupt()");
        thread.interrupt();
        log("work thread Interrupted 1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            log("work start");

            while (!Thread.interrupted()) { // interrupt state changed
                log("working");
            }
            log("work thread Interrupted 2 = " + Thread.currentThread().isInterrupted());


            try {
                log("resource arrangement");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log("interruption occurrence while resource arrangement");
                log("work thread Interrupted 3 = " + Thread.currentThread().isInterrupted());
            }
            log("work end");
        }
    }
}

