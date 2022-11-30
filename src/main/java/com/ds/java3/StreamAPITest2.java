package com.ds.java3;

import com.ds.java2.Employee;
import com.ds.java2.EmployeeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 15:41
 */
public class StreamAPITest2 {
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
        // 年龄是否都大于 18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        // 薪资是否至少有一个大于 10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
        // 返回第一个元素
        Optional<Employee> firstOptional = employees.stream().findFirst();
        System.out.println(firstOptional.get());
        //  findAny——返回当前流中的任意元素
        Optional<Employee> employeeOptional = employees.parallelStream().findAny();
        System.out.println(employeeOptional);
    }

    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();
        // count——返回流中元素的总个数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);


        Optional<Double> max = employees.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println(max.get());

        //double sumSalary = employees.stream().mapToDouble(Employee::getAge).sum();
        Double sumSalary = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sumSalary);

        Double avgSalary = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avgSalary);

    }

    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        List<Employee> employees = EmployeeData.getEmployees();
        Optional<Double> moneySum =
                employees.stream().map(Employee::getSalary).reduce((salary1, salary2) -> salary1 + salary2);
        System.out.println(moneySum.get());
    }

    @Test
    public void test4() {
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employList = employees.stream()
                .filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employList.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");
        employees.add(new Employee(1003, "刘强东", 33, 4000.82));
        employees.add(new Employee(1003, "刘强东", 33, 2000.82));
        List<Employee> distinctEmployeeList =
                employees.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                                new TreeSet<>((o1, o2) -> o1.getName().compareTo(o2.getName()))),
                        ArrayList::new));
        distinctEmployeeList.forEach(System.out::println);

        System.out.println("--------------------------------------------------------");
        String longStr = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(longStr);

        System.out.println("--------------------------------------------------------");
        employees.add(new Employee(1002, "马云", 13, 9876.12));
        employees.add(new Employee(1002, "马云", 11, 9876.12));
        Map<String, List<Employee>> nameMap = employees.stream().collect(Collectors.groupingBy(Employee::getName));
        nameMap.forEach((name, employeeList) ->
                {
                    System.out.println("name is" + name);
                    employeeList.forEach(System.out::println);
                }
        );
    }
    Map<String,Integer> unsortMap = new HashMap<>();
    @BeforeEach
    public  void initData() {
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);
    }

    @Test
    public void test5() {
        System.out.println("original ...");
        // {a=6, b=5, c=20, d=1, e=7, f=9, g=50, y=8, z=10, m=2, n=99}
        System.out.println(unsortMap);

        System.out.println("---------------------------------------------------");
        // {a=6, b=5, c=20, d=1, e=7, f=9, g=50, m=2, n=99, y=8, z=10}
        LinkedHashMap<String, Integer> sortedMap =
                unsortMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue, (oldValue, newValue) -> newValue, LinkedHashMap::new));
        System.out.println(sortedMap);

        System.out.println("---------------------------------------------------");
        Integer integer = unsortMap.computeIfAbsent("aafsdf", key -> this.genValue(key));
        System.out.println(integer);
        System.out.println(unsortMap);

    }

    private Integer genValue(String key) {
        return key.length();
    }
}
