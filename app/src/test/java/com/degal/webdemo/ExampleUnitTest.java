package com.degal.webdemo;

import android.util.Log;

import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String TAG = "ExampleUnitTest";

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void add() {
        //数据交换 第一只给你方法
        int a = 5;
        int b = 6;
//         a = 5;
//         b = 6;
//        int t = a;
//        a = b;
//        b = t;
        //第二种算法
//        a = a + b;
//        b = a - b;
//        a = a - b;
        //第三种算法 性能最优,但是没有可读性 用于空间和性能要求高的地方 比如无人机 (异或)
        //异或 各种进制间相互转换 二级制快速算法 短除法 有符号的运算可以用么？
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        //transient 瞬态变量 不参与序列化

        System.out.print("dayin a==" + a + "   b==" + b);

    }

    @Test
    public void testSort() {
        int[] arr = new int[]{1, 2, 56, 542, 3, 5, 981, 3};
        sortBullort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
    }

    public void sortBullort(int[] arr) {
        for (int j = arr.length - 1; j > 0; j--) {
            boolean flag = true;
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    public void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {

        }
    }

    class Student {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}