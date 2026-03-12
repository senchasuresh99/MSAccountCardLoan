package com.java;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RepeatedStringsCounter {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("apple", "banana", "apple", "orange", "banana", "banana");

       Map<String,Long> repeatedCount= strings.stream()
            .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
            

        System.out.println("Number of repeated strings: " + repeatedCount);
    }
}