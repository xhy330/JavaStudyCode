package ThreadSafe.runnablesafe;

class Window extends Thread{
    static int ticket = 100;
    @Override
    public void run() {
        while (true){

            // 此时使用this显然不靠谱,this指的是三个窗口的对象, 一个线程一个灯
            // Object obj = new Object(); 也不行,只有被是他static修饰以后才行
            synchronized (Window.class) {
                if(ticket > 0){

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"售票，票号为："+ticket);
                    ticket--;
                }else{
                    break;
                }
            }

        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}
