package homework;

/**
 * ClassName: Exercise2
 * Description: 在子线程中输出1-100之间的偶数，主线程输出1-100之间的奇数。  效果如下：
 *
 * @Author Trigger
 * @Create 2023/2/26 16:17
 * @Version 1.0
 */
public class Exercise2 {
    public static void main(String[] args) {
        new Thread("子线程"){
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if(i % 2 == 0){
                        System.out.println(getName()+"---->"+i);
                    }
                }
            }
        }.start();

        for (int i = 1; i <= 100; i++) {
            System.out.println("main主线程---->" + i);
        }
    }

}
