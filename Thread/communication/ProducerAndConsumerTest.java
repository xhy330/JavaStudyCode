package communication;

import jdk.nashorn.internal.ir.WhileNode;

/**
 * ClassName: ProducerAndConsumerTest
 * Description: 终极案例
 *      生产者(Productor)将产品交给店员(Clerk)，而消费者(Customer)从店员处取走产品，
 *      店员一次只能持有固定数量的产品(比如:20），如果生产者试图生产更多的产品，店员会叫
 *      生产者停一下，如果店中有空位放产品了再通知生产者继续生产；如果店中没有产品了，店员
 *      会告诉消费者等一下，如果店中有产品了再通知消费者来取走产品。类似的场景，比如厨师和服务员等。
 *
 *      分析:
 *      1. 是否是多线程问题? 是, 生产者和消费者
 *      2. 是否有共享数据? 是 产品
 *      3. 是否有线程安全问题? 是
 *      4. 是否存在线程通信? 是
 *
 * @Author Trigger
 * @Create 2023/2/26 15:19
 * @Version 1.0
 */

// 店员
class Clerk{

    private int num;

    public synchronized void addProduct(){
        if(num < 20){
            num++;
            System.out.println(Thread.currentThread().getName()+"生产了第"+num+"个产品!");
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void minusProdct(){
        if(num > 0){
            System.out.println(Thread.currentThread().getName()+"消费了第"+num+"个产品!");
            num--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

// 生产者
class Producer extends Thread{

    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        System.out.println("===============生产者开始生产产品===============");

        while (true){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.addProduct();
        }
    }
}

// 消费者
class Consumer extends Thread{

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("===============消费者开始消费产品===============");
        while (true){

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.minusProdct();
        }
    }
}

public class ProducerAndConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);

        p1.setName("生产者1");
        c1.setName("消费者1");

        p1.start();
        c1.start();
    }
}


