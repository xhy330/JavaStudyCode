package threadsafemore;

/**
 * ClassName: BankTest
 * Description:
 *      实现线程安全的懒汉式
 * @Author Trigger
 * @Create 2023/2/26 13:27
 * @Version 1.0
 */
public class BankTest {

    static Bank b1 = null;
    static Bank b2 = null;

    public static void main(String[] args){
        Thread t1 = new Thread(){
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b1 == b2);
    }
}

class Bank{
    private Bank(){};

    private static Bank instance = null;

    /**
     * @description: 实现线程安全的方式1
     *      静态的方法, 同步锁为Bank.class
     * @author: Trigger
     * @date: 2023/2/26 13:45
     * @return: threadsafemore.Bank
     */
//    public static synchronized Bank getInstance(){
//        if (instance == null){
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            instance = new Bank();
//        }
//        return instance;
//    }

    /**
     * @description: 使用的是同步代码块
     *      注意同步监视器是自己设置的共享类, 不能是this, 最好是该类本身
     *      类加载器只把它加载一次
     * @author: Trigger
     * @date: 2023/2/26 13:49  
     * @return: threadsafemore.Bank
     */
//    public static Bank getInstance(){
//        synchronized (Bank.class) {
//            if (instance == null){
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                instance = new Bank();
//            }
//        }
//        return instance;
//    }

    /**
     * @description: 改进方式2
     *      在创建完成对象后, 后续线程如果还行访问, 那就直接拿走该对象即可
     * @author: Trigger
     * @date: 2023/2/26 13:52
     * @return: threadsafemore.Bank
     */
    public static Bank getInstance(){
        synchronized (Bank.class) {
            if (instance == null){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                instance = new Bank();
            }
        }
        return instance;
    }
}
