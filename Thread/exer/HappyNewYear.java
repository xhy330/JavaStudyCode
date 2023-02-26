package exer;

/**
 * ClassName: HappyNewYear
 * Description: 模拟新年倒计时
 *
 * @Author Trigger
 * @Create 2023/2/26 12:56
 * @Version 1.0
 */
public class HappyNewYear {
    public static void main(String[] args) {
        for (int i = 10; i >= 0; i--) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i == 0){
                System.out.println("Happy NewYear!");
                break;
            }

            System.out.println(i);

        }
    }

}
