package com.wolsx.summarize.bishiti.hirect;

/**
 * Created by hy on 10/14/16.
 */
class Parent
{
    public void display()
    {
        System.out.println("Parent");
    }
}

class Children extends Parent
{
    @Override
    public void display()
    {
        System.out.println("Children");
    }
}

public class Main {
    public static void main(String[] args) {
        Parent person = new Children();
        person.display();
    }
}
