package com.mkyong.core;

public class Vehicle extends Car{

	public void go(Car c,Vehicle v){
		new Vehicle().go1(v, c);
	}
public void go1(Vehicle v,Car c){
		
	}
	
}
class Car{}