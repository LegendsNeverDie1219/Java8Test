package com.ds.java3;

import com.ds.java2.Employee;
import com.ds.java2.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 14:50
 */
public class StreamAPITest1 {
    // 筛选与切片
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Employee> stream = employees.stream();
        // 过滤. Predicate断言式接口类型 的Lambda表达式
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        //stream.forEach(System.out::println);
        System.out.println();
        // 限制/截断  -- 使其元素不超过给定的数量.
        employees.stream().limit(3).forEach(System.out::println);

        // System.out.println();
        //  List<Employee> employees1 = Lists.newArrayList(new Employee(1001, "hds"), new Employee(1001, "zl"));
        //  List<Employee> employees1 = Lists.newArrayList();
        //  employees1.stream().limit(3).forEach(System.out::println);

        System.out.println();
        //   skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        employees.stream().skip(3).forEach(System.out::println);


        employees.add(new Employee(1010, "刘强东", 40, 8000));
        employees.add(new Employee(1010, "刘强东", 41, 8000));
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        employees.add(new Employee(1010, "刘强东", 40, 8000));
        System.out.println("------------------------------");
        employees.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase(Locale.ROOT)).forEach(System.out::println);

        System.out.println("-----------------------------");
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(name -> name.length() > 3).forEach(System.out::println);

        System.out.println("-----------------------------");
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(stream -> {
            stream.forEach(System.out::println);
        });

        System.out.println("+++++++++++++++++++++++++++++++++++");
        // 接收一个函数作为入参, 将流中的每一个元素 都转换为另一个小的流. 然后把这些小的流 连接成一个大的流.
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);

        characterStream.forEach(System.out::println);

    }

    public static Stream<Character> fromStringToStream(String str) {
        char[] chars = str.toCharArray();
        //Arrays.stream(chars);
        //List<char[]> chars1 = Arrays.asList(chars);
        List<Character> list = new ArrayList<>();
        for (char c : chars) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);

        List<Employee> employees = EmployeeData.getEmployees();
        //   抛异常，原因:Employee没有实现Comparable接口
        // employees.stream().sorted().forEach(System.out::println);
        employees.add(new Employee(1003, "刘强东", 33, 4000.82));
        employees.add(new Employee(1003, "刘强东", 33, 2000.82));
        // 定制排序         年龄升序, 年龄相同的时候按照薪资降序.
        employees.stream().sorted((o1,o2) -> {
           int  ageResult =  Integer.compare(o1.getAge(),o2.getAge());
           if (ageResult == 0) {
               return  -Double.compare(o1.getSalary(), o2.getSalary());
           }
           return ageResult;
        }).forEach(System.out::println);
    }
}
