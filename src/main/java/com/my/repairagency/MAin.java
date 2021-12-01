package com.my.repairagency;



public class MAin {
    public static void main(String[] args) throws Exception {
        System.out.println(Test.valueOf("not_started"));
        Test test = Test.NOT_STARTED;
        System.out.println(test.toString());
    }
}
enum Test{
    NOT_STARTED
}


