package thread.control;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        // Runnable 클래스에서 체크 예외를 던지지 않기 때문에 상속받은 자식도 던질 수 없음
        @Override
        public void run() /*throws Exception*/ {
            /*throw new Exception();*/
        }

    }

}
