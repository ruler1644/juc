package com.atguigu.test;

public class FooImpl implements Foo, Fo {

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public int sub(int x, int y) {
		return x - y;
	}

	@Override
	public int sub2(int x, int y) {
		return x - y;
	}

}
