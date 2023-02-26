package thread;

public class EvenNumberTest {

    public static void main(String[] args) {

        PrintNumber t1 = new PrintNumber();
        t1.start();

        for (int i = 1; i <= 100; i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
    }
}

class PrintNumber extends Thread{

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++){
            if(i%2 == 0){
                System.out.println(Thread.currentThread().getName()+"："+i);
            }
        }
    }
}
