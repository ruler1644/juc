package com.atguigu.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyQueue
{
	
	
	private Object obj;

	//创建锁
	ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public void readObj()
	{
		//上读锁
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"读到的内容是："+obj);
		} finally {
			//解读锁
			rwl.readLock().unlock();
		}
	}
	
	public void writeObj(Object obj)
	{
		//上写锁
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"写入内容");
			this.obj = obj;
		} finally {
			//解写锁
			rwl.writeLock().unlock();
		}
	}
	
}

/**
 * 
 * @Description: 一个线程写入,100个线程读取
 * 
 */
public class ReadWriteLockDemo
{
	public static void main(String[] args) throws InterruptedException
	{
		MyQueue mq = new MyQueue();
		
		//一个线程写
		new Thread(()->{
			mq.writeObj("该下课了……");
		}, "AA").start();
		
		//100个线程读
		for (int i = 1; i <= 100 ; i++) {
			new Thread(()->{
				mq.readObj();
			}, String.valueOf(i)).start();
		}
	}
}
