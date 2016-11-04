package com.test;

class Vehicle
{
    public void drive()
    {
        System.out.println("Vehicle drive");
    }

    public static void main(String[] args) {
	// write your code here
    }
}

class Car extends Vehicle
{
    @Override
    public void drive()
    {
        System.out.println("Car drive");
    }
}

public class VehicleTest
{

    public  static void main(String[] args)
    {
        Vehicle v;
        Car c;
        v = new Vehicle();
        c = new Car();

        v.drive();
        c.drive();

        v = c;

        v.drive();
    }
}
