package communication;

/**
 * ClassName: PrintNumber
 * Description: 案例: 使用两个线程打印1-100 交替打印
 *
 * @Author Trigger
 * @Create 2023/2/26 15:00
 * @Version 1.0
 */
public class PrintNumber {

    public static void main(String[] args) {
        Communication c = new Communication();
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);

        t1.start();
        t2.start();
    }
}

class Communication implements Runnable{
    int num = 1;

    @Override
    public void run() {

        while (true){
            // 继承方式实现多线程, 而且只有一个对象, 使用this即可
            synchronized (this) {

                // 唤醒操作完成wait的线程
                notify();

                // 需要同步的内容
                if(num <= 100){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + num++);
                }else{
                    break;
                }

                // 每当一个线程执行完打印的操作以后就要主动释放锁, 等待唤醒
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
