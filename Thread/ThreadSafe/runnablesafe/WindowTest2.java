package ThreadSafe.runnablesafe;

/**
 * ClassName: WindowTest2
 * Description: 使用同步方法解决实现runnable接口的线程安全问题
 *
 * @Author Trigger
 * @Create 2023/2/25 22:27
 * @Version 1.0
 */


class SaleTicket1 implements Runnable{

    int ticket = 100;

    boolean isFlag = true;

    @Override
    public void run() {
        while (isFlag){
            show();
        }

    }

    // 如果同步方法是非静态的,同步监视器是this, 要考虑的则就是this是否唯一
    public synchronized void show(){
        if(ticket > 0){

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
            ticket--;
        }else{
            isFlag = false;
        }
    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        SaleTicket1 st = new SaleTicket1();
        Thread t1 = new Thread(st, "窗口1");
        Thread t2 = new Thread(st, "窗口2");
        Thread t3 = new Thread(st, "窗口3");

        t1.start();
        t2.start();
        t3.start();

    }

}
