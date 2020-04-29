package com.atguigu.juctest;

public class TestCompareAndSwap {

	public static void main(String[] args) {
		final CompareAndSwap cas=new CompareAndSwap();
		for(int i=0;i<10;i++) {
			new Thread(()->{
				int explair = cas.get();
				int num = (int)(Math.random()*101);
				System.out.println(num);
				boolean flag=cas.compareAndSet(explair,num);
				System.out.println(flag);
			}).start();
		}
	}

}
class CompareAndSwap{
	private int value;
	
	//获取内存值
	public synchronized int get() {
		return value;
	}
	
	//比较
	public synchronized int compareAndSwap(int explair,int newValue) {
		int oldValue = value;
		if(oldValue==explair) {
			this.value=newValue;
		}
		//System.out.println("--------"+oldValue);
		return oldValue;
	}
	
	//设置
	public synchronized boolean compareAndSet(int explair,int newValue){
		return explair==compareAndSwap(explair,newValue);
	}
}