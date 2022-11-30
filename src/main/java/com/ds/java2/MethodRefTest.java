package com.ds.java2;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用;
 * 1. 使用情景:  当要传递给lambda体的 部分, 已经有现成的 实现好的方法的时候, 就可以使用方法引用.
 *
 * 2. 方法引用:  本质也是lambda表达式  --> 函数式接口的 匿名实现类  的实例.
 *
 * 3.使用格式:
 *  对象::实例方法
 *  类::静态方法
 *  类::实例方法
 *
 *  4.方法引用的要求:
 *   方法引用的 形参列表  和返回值类型   ==  函数式接口中抽象方法的 形参列表, 返回值类型
 *
 * @author Administrator
 * @date 2022/11/30 11:17
 */
public class MethodRefTest {
    // 情况一 对象::实例方法
    // Consumer 中的 void  accept(T t)
    // PrintStream 中的 void println(T t)
    @Test
    public void test1() {
        Consumer<String> consumer = str -> System.out.println(str);
        consumer.accept("北京");

        System.out.println("***********************");

        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;
        consumer1.accept("beijing");
    }
    // Supplier 中的 T get()
    // Employee 中的 String getName()
    @Test
    public void test2() {
        Employee emp = new Employee(1001, "tom", 23,1000);
        Supplier<String> supplier = () -> emp.getName();
        System.out.println(supplier.get());

        System.out.println("*************************");
        Supplier<String> supplier1 = emp:: getName;
        System.out.println(supplier1.get());
    }
    // 类::静态方法.
    // Compartor中的  int compare(T t1, T t2)
    // Integer 中的  int  compare(int x, int y)
    @Test
    public void test3() {
        Comparator<Integer> comparator = (o1,o2) -> Integer.compare(o1,o2);
        System.out.println(comparator.compare(12,21));

        System.out.println("*****************************");

        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(12,21));
    }
    // Function 中 R apply (T t)
    // Math 中的 long round(double a)
    @Test
    public void test4() {
        Function<Double,Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(function.apply(12.34));

        System.out.println("**************************");

        Function<Double,Long> function1 = d -> Math.round(d);
        System.out.println(function1.apply(12.34));

        System.out.println("***************************");

        Function<Double,Long> function2 = Math::round;
        System.out.println(function2.apply(12.34));
    }
    // 类::实例方法
    // Compareator 中的int compare(T o1, T o2);
    // String      中的int compareTo(String o2)  调用者如果当作形参列表的 第一个参数,如果符合也是可以的.
    @Test
    public void test5() {
        Comparator<String> comparator = (s1,s2) -> s1.compareTo(s2);
        System.out.println(comparator.compare("abc","abd"));

        System.out.println("******************");

        Comparator<String> comparator1 = String::compareTo;
        System.out.println(comparator1.compare("abc","abf"));
    }
    // Function 中的 R apply(T t)
    // Employee 中的 String getName() 调用者如果当作形参列表的 第一个参数,如果符合也是可以的.
    @Test
    public void test7() {
        Employee employee = new Employee(1001, "jerry", 23, 1000);


        Function<Employee,String> function = e -> e.getName();
        System.out.println(function.apply(employee));

        System.out.println("******************************");

        Function<Employee,String> function2 = Employee::getName;
        System.out.println(function2.apply(employee));




    }

}
