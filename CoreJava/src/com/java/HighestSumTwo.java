package com.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class HighestSumTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   List<Integer> numbers = Arrays.asList(3, 5, 9, 1, 10, 2);
		int sum=   numbers.stream().sorted(Comparator.reverseOrder())
		   .limit(2)
		   .mapToInt(Integer::intValue)
		   .sum();
		   
		   System.out.println(sum);

	}

}
