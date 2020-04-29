package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket // 实例变量+实例方法Field +method
{
	private int number = 30;

//	// 1.同步方法
//	public synchronized void sale() {
//		// 2.同步代码块
//		synchronized (this) {
//		}
//		if (number > 0) {
//			System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
//		}
//	}
	//创建锁
	Lock lock = new ReentrantLock();
	public void sale() {
		//上锁
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
			}
		} finally {
			//解锁
			lock.unlock();
		}
		
	}

}


/**
 * 
 * @Description:卖票程序复习线程知识 ,	三个售票员			卖出			30张票
 * 
 * 笔记：Java里面如何进行工程级别的多线程编写
 * 1	多线程编程模板（套路）-----上
 * 		1.1	线程		操作(资源类里的实例方法)资源类	
 * 		1.2	高内聚	低耦合
 */
public class SaleTicket 
{
	public static void main(String[] args)//main所有程序的入口
	{
		//创建资源对象
		Ticket tk = new Ticket();
		
//		//AA卖票
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 0; i < 40; i++) {
//					//卖票
//					tk.sale();
//				}
//			}
//		}, "AA").start();
//		//BB卖票
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 0; i < 40; i++) {
//					//卖票
//					tk.sale();
//				}
//			}
//		}, "BB").start();
//		//CC卖票
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				for (int i = 0; i < 40; i++) {
//					//卖票
//					tk.sale();
//				}
//			}
//		}, "CC").start();
		
		//AA卖票
		new Thread(()->{for (int i = 0; i < 40; i++) {tk.sale();}}, "AA").start();
		//BB卖票
		new Thread(()->{for (int i = 0; i < 40; i++) {tk.sale();}}, "BB").start();
		//CC卖票
		new Thread(()->{for (int i = 0; i < 40; i++) {tk.sale();}}, "CC").start();
		
	}
}