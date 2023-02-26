package homework;

/**
 * ClassName: Exercise6
 * Description: 在exercise5的基础上，只要兔子和乌龟中有人到达终点，就宣布比赛结束，没到达终点的选手也要停下来。
 *
 * @Author Trigger
 * @Create 2023/2/26 17:14
 * @Version 1.0
 */
public class Exercise6 {

    public static void main(String[] args) {
        Athletes r = new Athletes("兔子", 100, 10000);
        Athletes t = new Athletes("乌龟", 1000, 1000);

        r.start();
        t.start();

        try {
            r.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(r.getName()+r.getTime());
        System.out.println(t.getName()+t.getTime());

    }

}

class Athletes extends Thread{
    private int meterTime;
    private int restTime;
    private long time;
    private int distance;
    private static boolean flag = true;

    public Athletes(String name, int meterTime, int restTime) {
        super(name);
        this.meterTime = meterTime;
        this.restTime = restTime;
    }

    @Override
    public void run() {
        long s = System.currentTimeMillis();
        while (distance < 30 && flag){
            try {
                Thread.sleep(meterTime);
                distance++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(distance == 10 || distance == 20){
                    System.out.println(getName()+"正在休息......");
                    Thread.sleep(restTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(distance == 30){
            flag = false;
            System.out.println(getName()+"到达终点......");
        }

        long e = System.currentTimeMillis();
        time = e - s;
        System.out.println(getName()+"停止跑步......");
    }

    public long getTime() {
        return time;
    }

    public static boolean isFlag(){
        return flag;
    }

    public int getDistance() {
        return distance;
    }
}
