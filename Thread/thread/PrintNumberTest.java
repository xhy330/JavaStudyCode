package thread;

class EvenNumberPrint extends Thread{

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName() +": "+i);
            }
        }
    }
}

class OddNumberPrint extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++){
            if(i%2 == 1){
                System.out.println(Thread.currentThread().getName() +": "+i);
            }
        }
    }
}

public class PrintNumberTest {
    public static void main(String[] args) {

      /*  方式1:创建对象然后自己调用
        EvenNumberPrint t1 = new EvenNumberPrint();
        OddNumberPrint t2 = new OddNumberPrint();

        t1.start();
        t2.start();*/

        // 方式2:创建Thread类的匿名子类的匿名对象
        new Thread(){
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++){
                    if(i%2 == 0){
                        System.out.println(Thread.currentThread().getName() +": "+i);
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++){
                    if(i%2 == 1){
                        System.out.println(Thread.currentThread().getName() +": "+i);
                    }
                }
            }
        }.start();




    }
}
