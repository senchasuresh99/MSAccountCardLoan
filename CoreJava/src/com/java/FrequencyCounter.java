package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> input = Arrays.asList("AA", "BB", "AA", "CC");
        Map<String,Long>coiuntofEachString=  input.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	   System.out.println(coiuntofEachString);
	}

}
