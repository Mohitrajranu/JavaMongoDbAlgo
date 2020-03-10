package com.mkyong.core;

public class ChildImplA implements ParentImplB,ParentImplA{
	
	public static void main(String[] args) {
		ParentImplA abc = new ChildImplA();
		abc.methodC();
		abc.m1(2,2);
	}

	
	public String methodB() {
		System.out.println("Inside Child");
		return "";
	}

	public void methodA() {
		// TODO Auto-generated method stub
		//ParentImplB.super.methodA();
		System.out.println("Inside Child");
	}


	@Override
	public String methodC() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void m1(float f, int i) {
		System.out.println("float int method");
		
	}
	
}
