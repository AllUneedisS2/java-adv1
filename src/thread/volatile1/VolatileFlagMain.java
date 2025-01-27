package thread.volatile1;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {
        log("main run");
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        t.start();

        log("runFlag = " + task.runFlag);
        sleep(1000);
        log("runFlag to false");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);

        log("main exit");
    }

    static class MyTask implements Runnable {

        // volatile이 아니라면 보통 캐시 메모리에 접근하기 때문에 runFlag 값을 바꾸어도 변경되지 않는다
        // cpu 설정상 언젠가는 메인 메모리에 접근하거나, 보통 context switch가 일어나면서 바뀌기는 하지만.. 이건 랜덤의 영역이다
        // boolean runFlag = true;
        // volatile : 캐시 메모리에 접근하지말고 메인 메모리에 항상 접근해서 값을 가져오라는 뜻
        volatile boolean runFlag = true;

        @Override
        public void run() {

            log("task start");
            while (runFlag) {
                // runFlag가 false로 변하면 탈출
            }
            log("task end");



        }
    }


}
