package thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {

    private int balance;

    public BankAccountV2(int initialBalance) {
        this.balance = initialBalance;
    }

    // synchronized : 동시에 여러개의 스레드에서 호출을 막는다
    // (volatile을 사용하지 않아도 이를 해결해주기도 한다)
    @Override
    public synchronized boolean withdraw(int amount) {
        log("[거래 시작] : " + getClass().getSimpleName());

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

        log("[거래 종료]");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balance;
    }

}
