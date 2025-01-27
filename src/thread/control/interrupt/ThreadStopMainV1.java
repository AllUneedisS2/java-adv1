package thread.control.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV1 {
    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("work stop, runFlag=false");
        task.runFlag = false;

    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true;

        @Override
        public void run() {
            log("work start");
            while (runFlag) {
                log("working");
                sleep(3000);
            }
            log("resource arrangement");
            log("work end");
        }
    }
}
