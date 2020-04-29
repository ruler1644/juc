package com.atguigu.juctest;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinTask<Long> task=new MyForkJoin(0L,10000000000L);
		Long sum = pool.invoke(task);
		System.out.println(sum);
	}

}
class MyForkJoin extends RecursiveTask<Long>{
	
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long thresHold=1000l;
	
	public MyForkJoin(long start,long end) {
		this.start=start;
		this.end=end;
	}
	@Override
	protected Long compute() {
		long length = end - start;
		
		if(length<=thresHold) {
			
			//计算
			long sum=0L;
			for (long i =start;i<=end;i++) {
				sum+=i;
			}
			return sum;
			
		}else{
			
			//拆分
			long middle=(start+end)/2;
			MyForkJoin left=new MyForkJoin(start,middle);
			left.fork();
			
			MyForkJoin right=new MyForkJoin(middle+1,end);
			right.fork();
			
			//合并
			return left.join()+right.join();
		}
	}
}
