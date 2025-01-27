package thread.sync;

public interface BankAccount {

    boolean withdraw(int amount); // 인출
    int getBalance(); // 잔고 확인

}
