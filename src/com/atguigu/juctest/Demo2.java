package com.atguigu.juctest;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo2 {
	public static void main(String[] args) {
		NumberThread nt=new NumberThread();
		
		for(int i=1; i<=10;i++) {
			new Thread(nt).start();
		}
	}
}

class NumberThread implements Runnable{
	//private volatile int number=0;
	private AtomicInteger number=new AtomicInteger();
	
	public int getNumber() {
		return number.getAndIncrement();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(getNumber());
	}
	
	
}