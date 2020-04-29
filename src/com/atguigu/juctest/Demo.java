package com.atguigu.juctest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
	public static void main(String[] args) {
		ShareD sd = new ShareD();
		new Thread(()->{
		    for (int i = 1; i <= 5; i++) {
		        try {
		            sd.print5(i);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		},"线程A").start();
		new Thread(()->{
		    for (int i = 1; i <= 5; i++) {
		        try {
		            sd.print10(i);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		},"线程B").start();
		new Thread(()->{
		    for (int i = 1; i <= 5; i++) {
		        try {
		            sd.print15(i);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		},"线程C").start();
	}
}
class ShareD{
    private int num = 1;
    private Lock lock = new ReentrantLock();
    
    //一把锁，配三把钥匙Condition 
    private Condition cd1 = lock.newCondition();
    private Condition cd2 = lock.newCondition();
    private Condition cd3 = lock.newCondition();
    public void print5(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            while (num != 1) {
                cd1.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()
                    +"第"+loopNum+"轮打印"+i);
            }
            num = 2;
            cd2.signal();
        } finally {
            //lock.unlock();
        }
    }
    public void print10(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            while (num != 2) {
                cd2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()
                    +"第"+loopNum+"轮打印"+i);
            }
            num = 3;
            cd3.signal();
        } finally {
            lock.unlock();
        }
    }
	public void print15(int loopNum) throws InterruptedException {
        lock.lock();
        try {
            while (num != 3) {
                cd3.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()
                    +"第"+loopNum+"轮打印"+i);
            }
            num = 1;
            cd1.signal();
        } finally {
            lock.unlock();
        }
    }
}