package lock;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: LockTest
 * Description: Lock来测试线程安全
 *
 * @Author Trigger
 * @Create 2023/2/26 14:49
 * @Version 1.0
 */
public class LockTest {
    public static void main(String[] args) {

        Window2 w1 = new Window2();
        Window2 w2 = new Window2();
        Window2 w3 = new Window2();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();

    }

}

class Window2 extends Thread{

    static int ticket = 100;

    //1. 创建Lock的实例, 需要保证多个线程共用同一个Lock实例, 需要考虑将此对象
    //声明为 static final
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            //2. 执行lock方法, 锁定对共享资源的调用
            try {
                lock.lock();
                if(ticket > 0){

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
            } finally {
                //3. unlock对共享资源进行释放, 使用finally能够保证肯定执行
                lock.unlock();
            }
        }
    }
}
