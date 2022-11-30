package com.ds.java1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 9:59
 */
public class LambdaTest {
    @Test
    public void test1() {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门广场");
            }
        };

        runnable1.run();

        System.out.println("****************************************");

        Runnable runnable2 = () -> System.out.println("我爱北京天安门广场2");

        runnable2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };
        int result = comparator.compare(12, 31);
        System.out.println(result);

        System.out.println("******************************");
        Comparator<Integer> comparator2 = (o1,o2) -> Integer.compare(o1,o2);

        int result2 = comparator2.compare(31, 12);
        System.out.println(result2);

        System.out.println("*****************************");
        Comparator<Integer> comparator3 = Integer::compare;

        int result3 = comparator3.compare(31, 12);
        System.out.println(result3);
    }


}
