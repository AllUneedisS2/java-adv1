package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {

    public static void main(String[] args) throws InterruptedException {
        log("START");

        SumTask sumTask1 = new SumTask(1, 50);
        SumTask sumTask2 = new SumTask(51, 100);

        Thread thread1 = new Thread(sumTask1, "sumTask1");
        Thread thread2 = new Thread(sumTask2, "sumTask2");

        thread1.start();
        thread2.start();

        // thread가 종료될 때 까지 특정 시간만 대기
        thread1.join(500); // 0.5초간 thread1가 terminated 될 때 까지 main thread는 TIMED_WAITING
        thread2.join(500); // 0.5초간 thread2가 terminated 될 때 까지 main thread는 TIMED_WAITING

        log("sumTask1.result = " + sumTask1.result);
        log("sumTask2.result = " + sumTask2.result);

        int sumAll = sumTask1.result + sumTask2.result;
        log("sumAll = " + sumAll);

        log("END");
    }

    static class SumTask implements Runnable {

        int startValue;
        int endValue;
        int result = 0;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            log(Thread.currentThread().getName() + " start");
            sleep(2000);

            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                sum += i;
            }
            result = sum;
            log(Thread.currentThread().getName() + " result = " + result);

            log(Thread.currentThread().getName() + " end");
        }
    }

}
