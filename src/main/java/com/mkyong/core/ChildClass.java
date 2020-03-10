package com.mkyong.core;

public  final class ChildClass extends ParentClass{
	String x = "Child`s Instance Variable";
	
	ChildClass(){
		System.out.println("Inside Child Constructor");
	}
	{
    	System.out.println("Inside child staticInit block");
    	
    }
	public void display(Integer i)
    {
        System.out.println("Inside B");
    }
	public Integer printname(int str){
		System.out.println("Hello this is constructor int test child" +str);
	
	return 1;}
	public static void foo() { 
        System.out.println("Test.foo() called "); 
    } 
    public  void foo(int a) {  
        System.out.println("Test.foo(int) called "); 
    } 
	ChildClass fun() 
    { 
        System.out.println("Derived fun()"); 
        return new ChildClass(); 
    } 
	public String display(String obj)
	{
		System.out.println("Derived.display() " + obj);
		return "Derived";
	}
	public void printname(){
		System.out.println("Hello this is constructor child test " );
	}
	static void m1() 
    { 
        System.out.println("From child "
                           + "static m1()"); 
    } 
  
	public static void main(String[] args) {
		ChildClass obj = new ChildClass();
		obj.display(5);
		obj.display("abc");
		obj.m1();
		
		System.out.println(obj.x);// Output - Parent's Instance Variable
	    // Accessing child's variable from parent's reference by type casting
	    System.out.println(((ChildClass) obj).x);// Output - "Child's Instance Variable"
		/*obj.printname(2);
		
		obj.printname();
		
		obj.display("abc");
		
		obj.fun();
		//obj.foo();
	//	obj.foo(3);
		obj.m1();
		 
*/	}

}
