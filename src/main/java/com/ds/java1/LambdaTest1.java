package com.ds.java1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 10:07
 */
public class LambdaTest1 {
    // 无参数,无返回值
    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门广场");
            }
        };
        runnable.run();

        System.out.println("***************************");

        Runnable runnable1 = () -> System.out.println("我爱北京");

        runnable1.run();
    }

    // 有一个参数, 但是没有返回值
    @Test
    public void test2() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer.accept("谎言和誓言的区别是什么?");

        System.out.println("********************************");

        Consumer<String> consumer1 = (String s) -> {
            System.out.println(s);
        };

        consumer1.accept("一个是听得人当真了,一个是说的人当真了");
    }

    // 形参列表的数据类型可以省略, 因为可以由编译器 推断得出. 即类型推断.
    @Test
    public void test3() {
        Consumer<String> consumer = (s) -> {
            System.out.println(s);
        };

        consumer.accept("一个是听得人当真了,一个是说的人当真了");
    }

    // 如果只需要一个入参, 则小括号也可以省略.
    @Test
    public void test5() {
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };

        consumer.accept("一个是听得人当真了,一个是说的人当真了");
    }

    //  Lambda需要两个或者以上的入参,则() 不可以省略,  Lambda体中有多条执行语句,{}也不可以省略.
    @Test
    public void test6() {
        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        int compare = comparator.compare(12, 5);
        System.out.println(compare);
    }
    // Lambda体 只有一条语句的时候,, {} return 都可以省略.
    @Test
    public void test7() {
        Comparator<Integer> comparator = (o1, o2) -> o1.compareTo(o2);
        System.out.println(comparator.compare(13,31));
    }
}
