package com.atguigu.juc;

@FunctionalInterface
interface Foo {

	public int add(int x, int y);
	
	default int sub(int x , int y) {
		return x -y;
	}
	default int sub2(int x , int y) {
		return x -y;
	}

	public static int mul(int x , int y) {
		return x*y;
	}
	public static int div(int x , int y) {
		return x/y;
	}
}

/**
 * 
 * @Description: Lambda Express-----> 函数式编程 
 * 拷贝小括号(形参列表)，写死右箭头 ->，落地大括号 {方法实现}
 *
 */
public class LambdaDemo {
	public static void main(String[] args) {
		Foo foo = (int x, int y)->{
			return x + y;
		};
		int add = foo.add(10, 2);
		System.out.println(add);
		//调用默认的实现方法
		int sub = foo.sub(10, 2);
		System.out.println(sub);
		int sub2 = foo.sub2(10, 2);
		System.out.println(sub2);
		//调用静态实现方法
		int mul = Foo.mul(10, 2);
		System.out.println(mul);
		int div = Foo.div(10, 2);
		System.out.println(div);
	}
}

/*
 * 1 拷贝小括号(形参列表)，写死右箭头 ->，落地大括号 {方法实现} 
 * 2 有且只有一个public方法@FunctionalInterface注解增强定义 
 * 3 default方法默认实现 
 * 4 静态方法实现
 * 
 **/
