package homework;

/**
 * ClassName: Exercise7
 * Description:
 *      请按要求编写多线程应用程序，模拟多个人通过一个山洞：
 *      1、这个山洞每次只能通过一个人，每个人通过山洞的时间为5秒；
 *      2、随机生成10个人，同时准备过此山洞
 *      3、定义一个变量用于记录通过隧道的人数
 *      4、显示每次通过山洞人的姓名，和通过顺序；
 * @Author Trigger
 * @Create 2023/2/26 17:41
 * @Version 1.0
 */
public class Exercise7 {

}

class Tunnel implements Runnable{

    @Override
    public void run() {
        cross();
    }

    public synchronized void cross(){

    }
}
