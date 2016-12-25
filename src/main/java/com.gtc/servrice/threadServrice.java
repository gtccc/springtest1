package com.gtc.servrice;

import com.gtc.dao.UserDao;
import com.gtc.dao.UserDaoImp;
import com.gtc.entity.User;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/25.
 */
@Service
public class threadServrice implements ApplicationListener<ContextRefreshedEvent> {
    private int num = 1;//设置打印的初始值
    private int index = 0;
    private int sum = 100;//设置插入条数
    int x = 1; //设置初始值，标记为1是线程一

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            threadServrice print = new threadServrice();
            Print1 print11 = new Print1(print);
            Print2 print22 = new Print2(print);
            Print3 print33 = new Print3(print);
            print11.setName("thread1");
            print22.setName("thread2");
            print33.setName("thread3");
            print11.start();
            print22.start();
            print33.start();
        }
    }

    public synchronized void add1() { //线程一的打印方法
        if (x != 1) {//当线程一被唤醒 但是x！=1，则将线程一wait();
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {//当x为1时，从num开始打印
            for (int i = num + index; i <= num + sum; i++) {
                addUser(i, Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + ": " + i);
                index += 1;
                x = 2;//改变标记值
                this.notifyAll();//将其他线程唤醒
                break;//跳出for 循环
            }
        }
    }

    //同理下面两个方法：
    public synchronized void add2() {
        if (x != 2) {
            System.out.println("线程二输出完毕，轮到线程三输出");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = num + index; i <= num + sum; i++) {
                addUser(i, Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + ": " + i);
                index += 1;
                x = 3;
                this.notifyAll();
                break;
            }
        }
    }

    public synchronized void add3() {
        if (x != 3) {
            System.out.println("线程三输出完毕，轮到线程一输出");
            try {
                wait();
            } catch (InterruptedException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            for (int i = num + index; i <= num + sum; i++) {
                addUser(i, Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + ": " + i);
                index += 1;
                x = 1;//线程三打印完毕 将标记改为1
                this.notifyAll();
                break;
            }
        }
    }

    private void addUser(int num, String name) {
        UserDao userDao = new UserDaoImp();
        int rd = Math.random() > 0.5 ? 1 : 0;
        String sex = (rd == 0 ? "boy" : "girl");
        client cc = new client();
        User user = new User(num, name, sex);
        JSONObject json = JSONObject.fromObject(user);
        try {
            cc.sendPost("user", json.toString());
            Thread.currentThread().sleep(5000);
        } catch (Exception e) {
            try {
                e.printStackTrace();
                Thread.currentThread().sleep(10000);
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }


    private class Print1 extends Thread {//线程一
        private threadServrice print;//申明中间类

        public Print1(threadServrice print) {//创建中间类参数的线程一构造方法
            super();
            this.print = print;
        }

        @Override
        public void run() {
// TODO Auto-generated method stub
            while (true) {//让线程一被启动的时候一直运行下面的代码
                print.add1();//调用中间类中的add1方法
            }
        }
    }

    class Print2 extends Thread {
        private threadServrice print;

        public Print2(threadServrice print) {
            super();
            this.print = print;
        }

        @Override
        public void run() {
// TODO Auto-generated method stub
            while (true) {
                print.add2();
            }
        }
    }

    class Print3 extends Thread {
        private threadServrice print;

        public Print3(threadServrice print) {
            super();
            this.print = print;
        }

        @Override
        public void run() {
            while (true) {
                print.add3();
            }
        }
    }
}

