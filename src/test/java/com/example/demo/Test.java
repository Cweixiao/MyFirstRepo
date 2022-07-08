package com.example.demo;

public class Test {

    /**
     * 测试 int[]初始化后的默认值
     */
    @org.junit.Test
    public void func(){
        int[] a = new int[5];
        for(int i:a){
            System.out.println(i);
        }
    }
}
