package homework;

import java.util.Scanner;

/**
 * ClassName: Exercise3
 * Description: 自定义线程类ChatThread：问是否结束（输入Y/y结束），
 * 如果输入的不是y，继续问是否结束，直到输入y才结束。  打印[1,10]，每
 * 隔10毫秒打印一个数字，现在当主线程打印完5之后，就让自定义线程类加塞，
 * 直到自定义线程类结束，主线程再继续。
 *
 * @Author Trigger
 * @Create 2023/2/26 16:22
 * @Version 1.0
 */

public class Exercise3 {
    public static void main(String[] args){
        for (int i = 1; i < 11; i++) {
            System.out.println("main: "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5){
                ChatThread t = new ChatThread();
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ChatThread extends Thread{
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("问是否结束（输入Y/y结束）");
            String s = sc.next();
            if(s.equals("Y") || s.equals("y")){
                break;
            }
        }
        sc.close();
    }
}
