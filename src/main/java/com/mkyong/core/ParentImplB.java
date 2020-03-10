package com.mkyong.core;

public interface ParentImplB {

	default void methodA(){
		System.out.println("Indide parent B");
	}
	
	public String methodB();
	public void m1(float f,int i);


}
