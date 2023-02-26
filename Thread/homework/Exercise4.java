package homework;

/**
 * ClassName: Exercise4
 * Description:
 *      声明一个PrintEvenThread线程类，继承Thread类，重写run方法，实现打印[1,100]之间的偶数，要求每隔1毫秒打印1个偶数。
 *      声明一个PrintOddThread线程类，继承Thread类，重写run方法，实现打印[1,100]之间的奇数。
 *      在main线程中：
 *      1. 创建两个线程对象，并启动两个线程
 *      2. 当打印奇数的线程结束了，让偶数的线程也停下来，就算偶数线程没有全部打印完[1,100]之间的偶数。
 *
 * @Author Trigger
 * @Create 2023/2/26 16:30
 * @Version 1.0
 */

class PrintEvenThread extends Thread{

    private boolean flag = true;

    @Override
    public void run() {
        for (int i = 1; i <= 100 && flag; i++) {
            if(i % 2 == 0){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(getName()+"---->"+i);
            }
        }
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class PrintOddThread extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 1){
                System.out.println(getName()+"---->"+i);
            }
        }
    }

}

public class Exercise4 {
    public static void main(String[] args) {
        PrintEvenThread p1 = new PrintEvenThread();
        PrintOddThread p2 = new PrintOddThread();

        p1.setName("偶数");
        p2.setName("奇数");

        p1.start();
        p2.start();

        try {
            p2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p1.setFlag(false);

    }
}
