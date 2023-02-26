package ThreadSafe.runnablesafe;

/**
 * ClassName: WindowTest3
 * Description: 使用同步方法来解决 继承Thread类的安全问题
 *
 * @Author Trigger
 * @Create 2023/2/26 12:43
 * @Version 1.0
 */
public class WindowTest3 {
    public static void main(String[] args) {
        Window1 w1 = new Window1();
        Window1 w2 = new Window1();
        Window1 w3 = new Window1();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }

}


class Window1 extends Thread{
    static int ticket = 100;
    static boolean isFlag = true;

    @Override
    public void run() {
        while (isFlag){
            show();
        }
    }

    /**
     * @description: 只要是非静态的,同步监视器就是this, 不行, 故需要改
     *               加上static, 默认的同步监视器是当前类本身
     * @author: Trigger
     * @date: 2023/2/26 12:47  
     */
    public static synchronized void show(){

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