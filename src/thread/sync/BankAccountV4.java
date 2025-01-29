package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int initialBalance) {
        this.balance = initialBalance;
    }

    // synchronized : 동시에 여러개의 스레드에서 호출을 막는다
    // (volatile을 사용하지 않아도 이를 해결해주기도 한다)
    // 단점1 - interrupt와 timeout의 부재
    // 단점2 - 공정성 : BLOCKED 상태의 여러 스레드 중 누가 LOCK을 획득할지 알 수는 없다 => 하나의 스레드가 오래 기다리게 됨
    @Override
    public boolean withdraw(int amount) {
        log("[거래 시작] : " + getClass().getSimpleName());

        // ==임계 영역 시작==
        lock.lock(); // ReentrantLock 사용하여 lock()
        try {
            log("[검증 시작]");
            log("잔고 : " + balance);
            log("인출액 : " + amount);

            // 잔고가 출금액 보다 많으면, 진행X
            if (balance < amount) {
                log("[검증 실패]");
                return false;
            }

            // 잔고가 출금액 보다 적으면, 진행O
            log("[검증 완료]");

            log("[출금 진행]");
            sleep(1000);
            balance = balance - amount;
        } finally {
            lock.unlock(); // ReentrantLock 사용하여 unlock()
            // ==임계 영역 종료==
        }

        log("[거래 종료]");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock();
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }

}
