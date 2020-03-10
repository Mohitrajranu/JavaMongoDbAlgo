package com.mkyong.core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {

	private String name;
	private int res=0;
	Test(String name){
	this.name=name;
	}
	
	public static void printname(String str1,String str2){
		System.out.println("Hello this is constructor test " );
	}
	
	public void printname(){
		System.out.println("Hello this is constructor test " +this.name);
	}
	
	public String printname(String str){
		System.out.println("Hello this is constructor test " +str);
	
	return "";}
	
	public Integer printname(Integer str){
		System.out.println("Hello this is constructor Integer test " +this.name);
	
	return 0;}
	
	public int printname(int str){
		System.out.println("Hello this is constructor int test " +this.name);
	
	return 1;}
	
	public static void main(String[] args) {
		/*int a = 10;
		int b=7;
		int c=b++*2;
		System.out.println("Answer "+c);
		
        double num[] = {5.5, 10.1, 11, 12.8, 56.9, 2.5};
        double result;
        result = 0;
        for (int i = 0; i < 6; ++i) 
            result = result + num[i];
    System.out.print(result/6);*/
    /*Enum en = Enums.C;
	BigDecimal b = new BigDecimal("23.43");
	BigDecimal br = new BigDecimal("24");
	BigDecimal bres = b.add(new BigDecimal("450.23"));
	System.out.println("Add: "+bres);
	MathContext mc = new MathContext(2, RoundingMode.DOWN);
	BigDecimal bdecMath = b.add(new BigDecimal("450.23"), mc);
	System.out.println("Add using MathContext: "+bdecMath);*/
		/*
		Test tb = new Test("Mohit");
		//name = "Siva";
		tb.printname(tb.name);
		//int result=tb.printname(new Integer(123));
		//System.out.println("result"+result);
		String start_dt = "25-10-19 11:10:57";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yy"); 
		try {
			Map<String, Integer> dayMap = new HashMap<String, Integer>();
			dayMap.put("Sunday",1);
			dayMap.put("Monday",2);
			dayMap.put("Tuesday",3);
			dayMap.put("Wednesday",4);
			dayMap.put("Thursday",5);
			dayMap.put("Friday",6);
			dayMap.put("Saturday",7);
			
			Date date = (Date)formatter.parse(start_dt);
			
			Date d=new Date();  
	        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
	       
	        StringBuilder selday=new StringBuilder(simpleDateformat.format(d));
	        System.out.println(selday.toString());
	        
	        StringBuilder newday=new StringBuilder(simpleDateformat.format(date));
	        System.out.println(newday.toString());
	        
	        Calendar curr = Calendar.getInstance();
	        curr.setTime(date);
	        //int dayOfWeek = curr.get(Calendar.DAY_OF_WEEK);
	        
	        int scheduleDay = dayMap.get(selday.toString());
	        System.out.println(scheduleDay);
	        int currentDay = dayMap.get(newday.toString());
	        System.out.println(currentDay);
	        if(scheduleDay == currentDay){
	        	long ltime=date.getTime();
		        Date today8=new Date(ltime);
		        System.out.println(formatter.format(today8));
	        }else if(scheduleDay > currentDay){
	        	long ltime=date.getTime()+(scheduleDay - currentDay)*24*60*60*1000;
		        Date today8=new Date(ltime);
		        System.out.println("Schedule Day is greater"+formatter.format(today8));
	        }else if(scheduleDay < currentDay){
	        	Calendar date1 = Calendar.getInstance();
	        	date1.setTime(date);
	        	while (date1.get(Calendar.DAY_OF_WEEK) != scheduleDay) {
	        	    date1.add(Calendar.DATE, 1);
	        	}
		        Date today8=date1.getTime();
		        System.out.println("Schedule Day is Smaller"+formatter.format(today8));	
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	double a = 295.04;
    int  b1 = 300;
    byte c = (byte) a;
    byte d = (byte) b1;
    System.out.println(c + " "  + d);
	 int array_variable [] = new int[10];
	    for (int i = 0; i < 10; ++i) {
             array_variable[i] = i/2;
             array_variable[i]++;
             System.out.print(array_variable[i] + " ");
             i++;
         }
	
	 int a = 1;
     int b = 2;
     int c;
     int d;
     c = ++b;
     d = a++;
     c++;
     b++;
     ++a;
     System.out.println(a + " " + b + " " + c+ " "+d);
     int var1 = 4;
     int var2 = ~var1;
     System.out.print(var1 + " " + var2); 
     
     */
     /*int a = 1;
     int b = 2;
     int c = 3;
     a |= 4;
     System.out.println(a);
     b >>= 1;
     System.out.println(b);
     c <<= 1;
     System.out.println(c);
     a ^= c;
     System.out.println(a);
     System.out.println(~a);
     System.out.println(a + " " + b + " " + c);*/
		 
    /* int sum = 0;
     for (int i = 0, j = 0; i < 5 & j < 5; ++i, j=i+1)
         sum += i;
  System.out.println(sum);*/
	}
}
enum Enums
{
    A, B, C;
 
    private Enums()
    {
        System.out.println(10);
    }
}
 
