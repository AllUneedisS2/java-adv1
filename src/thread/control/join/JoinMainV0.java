package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV0 {

    public static void main(String[] args) {
        log("START");

        Thread thread1 = new Thread(new Job(), "tread-1");
        Thread thread2 = new Thread(new Job(), "tread-2");

        thread1.start();
        thread2.start();

        log("END");
    }

    static class Job implements Runnable {

        @Override
        public void run() {
            log("Job start");
            sleep(2000); // Sate : TIMED_WAITING
            log("Job end");
        }
    }

}
