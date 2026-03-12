package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CubeFilterExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7);
		List<Integer> cubesofList=numbers.stream()
		.map(n->n*n*n)
		 .filter(n-> n > 50)
		 .collect(Collectors.toList());
		
		System.out.println(cubesofList);


	}

}
