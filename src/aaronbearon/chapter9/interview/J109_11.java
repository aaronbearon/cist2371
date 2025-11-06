package aaronbearon.chapter9.interview;

import java.util.Scanner;

public class J109_11 {
    public static void main(String[] args) {
        Person me = new Person();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        me.setName(input.nextLine());
        System.out.print("Enter the last four digits of your phone number: ");
        me.setPhone(input.nextLine());
        System.out.println("Your name is " + me.getName());
        System.out.println("The last four digits of your phone number are " + me.getPhone());
    }
}

class Person {
    private String name;

    private String phone;

    // Implicit default constructor

    void setName(String n) {
        name = n;
    }

    void setPhone(String p) {
        phone = p;
    }

    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }
}
