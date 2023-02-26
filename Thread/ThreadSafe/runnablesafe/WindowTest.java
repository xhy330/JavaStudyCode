package ThreadSafe.runnablesafe;
// 使用实现Runnable接口的方式，实现卖票
// 使用同步代码块解决上述卖票中的线程安全问题


import javax.lang.model.element.VariableElement;

class SaleTicket implements Runnable{
    int ticket = 100;
    Object obj = new Object();

    @Override
    public void run() {

        // 如果在while外面加同步代码块,那就不现实, 还是等
        // 该线程已经把语句都执行完了
        while (true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//          synchronized (obj){ // obj是唯一的
            synchronized (this){  // this也是唯一的,指的是SaleTicket对象
                if(ticket > 0){
                    System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}


public class WindowTest {

    public static void main(String[] args) {

        SaleTicket s = new SaleTicket();

        Thread t1 = new Thread(s);
        Thread t2 = new Thread(s);
        Thread t3 = new Thread(s);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }


}
