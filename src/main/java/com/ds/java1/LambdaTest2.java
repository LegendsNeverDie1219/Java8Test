package com.ds.java1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * TODO
 *
 * @author Administrator
 * @date 2022/11/30 10:42
 */
public class LambdaTest2 {

    // 消费型接口 : Consumer<T>  void accept(T t)
    // 供给型接口: Supplier<T>  T void get()
    // 函数型接口: Function<T,R> R apply(T t)
    // 断定型接口 Predicate<T>  boolean test(T t)
    @Test
    public void test1() {
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了,去天上人间买了一瓶矿泉水 ,价格为: " + aDouble);
            }
        });

        System.out.println("************************");
    }

    public void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("北京", "南京", "天津", "东京", "西京", "普京");
        List<String> filterString = filterString(list, s -> s.contains("京"));
        System.out.println(filterString);
    }

    public List<String> filterString(List<String> inputList, Predicate<String> predicate) {
        List<String> filterList = new ArrayList<>();
        for (String inputStr : inputList) {
            if(predicate.test(inputStr)) {
                filterList.add(inputStr);
            }
        }
        return filterList;
    }
}
