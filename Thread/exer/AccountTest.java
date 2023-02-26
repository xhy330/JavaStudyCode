package exer;

/**
 * ClassName: AccountTest
 * Description: 两个账户同时往一个账户存钱的线程安全问题
 *
 * @Author Trigger
 * @Create 2023/2/26 13:03
 * @Version 1.0
 */
public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account();

        Customer c1 = new Customer(acct, "甲");
        Customer c2 = new Customer(acct, "乙");

        c1.start();
        c2.start();
    }

}

class Account{
    double balance;

    /**
     * @description: 这个是线程主要调用的方法,应该设置同步锁
     *               又非静态方法调用的是this, 而且本案例中只有一个
     *               Account对象, 故满足唯一性
     * @author: Trigger
     * @date: 2023/2/26 13:21
     * @param: amt
     */
    public synchronized void deposit(double amt){
        if(amt > 0){
            balance += amt;
            System.out.println(Thread.currentThread().getName() + "向账户汇款, 目前余额为: " + balance);
        }
    }
}

class Customer extends Thread{
    Account acct;

    public Customer(Account acct) {
        this.acct = acct;
    }

    public Customer(Account acct, String name) {
        super(name);
        this.acct = acct;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            acct.deposit(1000);
        }
    }
}