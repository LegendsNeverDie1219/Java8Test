package com.ds.java2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 11:52
 */
public class ConstructorRefTest {
    //Employee的空参构造器：new Employee()
    // Supplier中的 T get()
    @Test
    public void test1() {
        Supplier<Employee> supplier = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(supplier.get());


        System.out.println("***********************");

        Supplier<Employee> supplier1 = () -> new Employee();
        System.out.println(supplier1.get());

        System.out.println("***********************");

        Supplier<Employee> supplier2 = Employee::new;
        System.out.println(supplier2.get());
    }
    // Employee 中有参构造器 new Employee(Integer id)
    // Function 中的 R apply(T t)
    @Test
    public void test2() {
        Function<Integer,Employee> function = id -> new Employee(id);
        Employee employee = function.apply(1001);
        System.out.println(employee);

        System.out.println("***********************");
        Function<Integer,Employee> function1 = Employee::new;

        Employee em = function1.apply(1002);
        System.out.println(em);
    }
    @Test
    public void test4() {
        Function<Integer,String[]> function = length -> new String[length];
        String[] arr = function.apply(5);
        System.out.println(Arrays.toString(arr));

        System.out.println("*********************************");
        Function<Integer,String[]> function1 = String[]::new;
        String[] arr11 = function1.apply(10);
        System.out.println(Arrays.toString(arr11
        ));

    }
}
