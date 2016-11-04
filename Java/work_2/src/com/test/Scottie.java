package com.test;

/**
 * Created by daizhen on 11/4/2016.
 */

class Mammal
{
    Mammal()
    {
        System.out.println("Four");
    }

    public void ears()
    {
        System.out.println("Two");
    }
}

class Dog extends Mammal
{
    Dog()
    {
        super.ears();
        System.out.println("Three");
    }
}
public class Scottie extends Dog
{
    public static void main(String[] args)
    {
        System.out.println("One");
        Scottie h = new Scottie();
    }
}
