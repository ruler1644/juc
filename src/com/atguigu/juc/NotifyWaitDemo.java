package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData//资源类
{
	private int number = 0;//初始值为零的一个变量
	
	//创建锁
	Lock lock = new ReentrantLock();
	Condition cd = lock.newCondition();
	
	public void increment() throws InterruptedException 
	{	
		//上锁
		lock.lock();
		try {
			//1.判断
			while(number != 0) {
				//等待
				cd.await();
			}
			//2.干活
			number++;
			System.out.println(Thread.currentThread().getName()+" number="+number);
			//3.通知
			cd.signalAll();
		} finally {
			//解锁
			lock.unlock();
		}
	}
	
	public void decrement() throws InterruptedException 
	{
		//上锁
		lock.lock();
		try {
			//1.判断
			while(number != 1) {
				//等待
				cd.await();
			}
			//2.干活
			number--;
			System.out.println(Thread.currentThread().getName()+" number="+number);
			//3.通知
			cd.signalAll();
		} finally {
			//解锁
			lock.unlock();
		}
	}
//	public synchronized void increment() throws InterruptedException 
//	{	
//		//1.判断
//		while(number != 0) {
//			//等待
//			this.wait();
//		}
//		//2.干活
//		number++;
//		System.out.println(Thread.currentThread().getName()+" number="+number);
//		//3.通知
//		this.notifyAll();
//	}
//	
//	public synchronized void decrement() throws InterruptedException 
//	{
//		//1.判断
//		while(number != 1) {
//			//等待
//			this.wait();
//		}
//		//2.干活
//		number--;
//		System.out.println(Thread.currentThread().getName()+" number="+number);
//		//3.通知
//		this.notifyAll();
//	}
}

/**
 * 
 * @Description:
 * 现在有两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮。 
 *
 *  * 笔记：Java里面如何进行工程级别的多线程编写
 * 1	多线程编程模板（套路）-----上
 * 		1.1	线程操作资源类	
 * 		1.2	高内聚	低耦合
 * 2	多线程编程模板（套路）-----下
 * 		2.1	判断
 * 		2.2	干活
 * 		2.3	通知
 
 */
public class NotifyWaitDemo
{
	public static void main(String[] args)
	{
		//创建资源对象
		ShareData sd = new ShareData();
		
		//AA加
		new Thread(()->{
			//来10轮
			for (int i = 0; i < 10; i++) {
				try {
					//加
					sd.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "AA").start();
		//BB减
		new Thread(()->{
			//来10轮
			for (int i = 0; i < 10; i++) {
				try {
					//减
					sd.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();
		//CC加
		new Thread(()->{
			//来10轮
			for (int i = 0; i < 10; i++) {
				try {
					//加
					sd.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
		//DD减
		new Thread(()->{
			//来10轮
			for (int i = 0; i < 10; i++) {
				try {
					//减
					sd.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "DD").start();
	}
}





/*
 * 3	防止虚假唤醒用while
 *  
 * */





