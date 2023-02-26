package thread;

public class TestThread {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread("子线程1");
        mt1.start();

        MyThread mt2 = new MyThread("子线程2");
        mt2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("main线程" + "：正在执行" + i);
        }

    }
}
