package com.concurrency.multithreading;

abstract class Animal {
	abstract String eat();
}

class Animal1 extends Animal {

	@Override
	String eat() {
		return "Food1";
	}
	
}


class Animal2 extends Animal1{
	@Override
	String eat() {
		return super.eat() + ",Food2";
	}
}

public class Animal3 extends Animal1 {
	@Override
	String eat() {
		return super.eat() + ",Food3";
	}
}
