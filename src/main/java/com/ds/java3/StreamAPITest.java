package com.ds.java3;

import com.ds.java2.Employee;
import com.ds.java2.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 14:37
 */
public class StreamAPITest {
    // 通过Collection接口的 default 方法 stream()/ parrelStream()
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

        Stream<Employee> stream = employees.stream();
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    // 通过Arrays的静态方法stream()
    @Test
    public void test2() {
        int[] arr = new int[]{1,2,3,4,5,6};
        IntStream stream = Arrays.stream(arr);
        Employee e1 = new Employee(1001, "tom");
        Employee e2 = new Employee(1002, "jerry");
        Employee[] arr1 = new Employee[]{e1,e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }
    // 通过 Stream.of()方法
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }
    // 通过Stream.iterate()/generate() 创建无限流.
    @Test
    public void test4() {
        // 迭代
        Stream.iterate(0, t ->  t + 2).limit(10).forEach(System.out::println);
        System.out.println("____________________________________________");
        // 随机生成
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
