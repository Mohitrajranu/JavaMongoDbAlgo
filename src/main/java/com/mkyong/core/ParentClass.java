package com.mkyong.core;

public class ParentClass {
    final String x = "Parents`s Instance Variable";
    ParentClass(){
    	System.out.println("Inside Parent Constructor");
    }
    {
    	System.out.println("Inside Parent staticInit block");
    	
    }
    
	Object fun() 
    { 
        System.out.println("Base fun()"); 
        return new Object(); 
    } 
	public void display(int i)
    {
        System.out.println("Inside A");
    }
	public void printname(){
		System.out.println("Hello this is constructor parent test " );
	}
	
	public Object display(String obj)
	{
		System.out.println("Base.display() " + obj);
		return "0";
	}
	public Object printname(Integer str){
		System.out.println("Hello this is constructor Integer test " +str);
	
	return 0;
	}
	public Integer printname(int str){
		System.out.println("Hello this is constructor int test " +str);
	
	return 2;}
	static void m1() 
    { 
        System.out.println("From parent "
                           + "static m1()"); 
    } 
  
}
