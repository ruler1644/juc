package com.atguigu.test;

public interface Foo {

	public int add(int x, int y);

	default int sub(int x, int y) {
		return x - y;
	}

	default int sub2(int x, int y) {
		return x - y;
	}

	public static int mul(int x, int y) {
		return x * y;
	}

	public static int div(int x, int y) {
		return x / y;
	}
}
