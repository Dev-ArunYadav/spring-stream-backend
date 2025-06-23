package com.stream.app.spring_stream_backend.user;



public class TestCode {
    public static void testMethod1(String a) {
        System.out.println("Test Method 1: " + a);
    }
    public static void testMethod1(Object a) {
        System.out.println("Test Method 2: " + a );
    }
    public static void main(String[] args) {
        TestCode.testMethod1("Hello");
    }
}
