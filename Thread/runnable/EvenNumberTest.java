package runnable;

class EvenNumberPrint implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
    }
}


public class EvenNumberTest {
    public static void main(String[] args) {
//        EvenNumberPrint p = new EvenNumberPrint();
//        Thread t1 = new Thread(p);
//        t1.start();
//
//        // main方法对应的主线程执行的操作
//        for (int i = 1; i <= 100; i++) {
//            if(i%2 == 0){
//                System.out.println(Thread.currentThread().getName()+"："+i);
//            }
//        }
//
//        Thread t2 = new Thread(p);
//        t2.start();

        // 方法3：使用Runnable接口的方式：提供了runnable接口匿名实现类的匿名对象
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if(i%2 == 0){
                    System.out.println(Thread.currentThread().getName()+"："+i);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if(i%2 == 1){
                        System.out.println(Thread.currentThread().getName()+"："+i);
                    }
                }
            }
        }).start();
    }

}
