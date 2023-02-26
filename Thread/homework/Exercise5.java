package homework;

/**
 * ClassName: Exercise5
 * Description:
 *      案例：编写龟兔赛跑多线程程序，设赛跑长度为30米
 *      兔子的速度是10米每秒，兔子每跑完10米休眠的时间10秒
 *      乌龟的速度是1米每秒，乌龟每跑完10米的休眠时间是1秒
 *      要求：要等兔子和乌龟的线程结束，主线程（裁判）才能公布最后的结果。
 *
 * @Author Trigger
 * @Create 2023/2/26 16:43
 * @Version 1.0
 */
public class Exercise5 {
    public static void main(String[] args) {
        Racer r = new Racer("兔子", 100, 10000);
        Racer t = new Racer("乌龟", 1000, 1000);

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

        System.out.println("兔子用时为：" + r.getTime());
        System.out.println("乌龟用时为：" + t.getTime());
    }
}

class Racer extends Thread{
    private long meterTime;
    private long restTime;
    private long time;

    public Racer(String name, long meterTime, long restTime) {
        super(name);
        this.meterTime = meterTime;
        this.restTime = restTime;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 30; i++) {
            try {
                Thread.sleep(meterTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(i == 10 || i == 20){
                    System.out.println(getName()+"正在休息....");
                    Thread.sleep(restTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + "到达终点......");
        long end = System.currentTimeMillis();
        time = end - start;
    }

    public long getTime(){
        return time;
    }
}
