package homework;

/**
 * ClassName: Exercise1
 * Description: 模拟新年倒计时，每隔1秒输出一个数字，依次输出10,9,8......1，最后输出：新年快乐！
 *
 * @Author Trigger
 * @Create 2023/2/26 16:10
 * @Version 1.0
 */
public class Exercise1 {
    public static void main(String[] args) {
        for (int i = 10; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == 0){
                System.out.println("Happy NewYear");
                break;
            }
            System.out.println(i);
        }
    }

}
