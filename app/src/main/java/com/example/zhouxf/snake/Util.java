package com.example.zhouxf.snake;

public class Util {


    public static void println(Object o) {
        System.out.println(o);
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object[] objects) {
        if (objects == null) {
            println("null");
        }
        print("[");
        for (int i = 0; i < objects.length; i++) {
            Object o = objects[i];
            if (i == objects.length - 1) {
                print(o);
            } else {
                print(o + ", ");
            }
        }
        println("]");
    }

    public static void println(int[] data) {
        if (data == null) {
            println("null");
        }
        print("[");
        for (int i = 0; i < data.length; i++) {
            Object o = data[i];
            if (i == data.length - 1) {
                print(o);
            } else {
                print(o + ", ");
            }
        }
        println("]");
    }

}
