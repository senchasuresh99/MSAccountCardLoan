package com.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Interview_09 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//sort list of string based on their length
		List<String> names = Arrays.asList("apple", "banana", "kiwi", "cherry");
		List<String> sorted=names.stream()
				           .sorted(Comparator.comparing(String::length))
				           .collect(Collectors.toList());
		
		System.out.println(sorted);
		
		//find repeated chacter of each string
		 names.forEach(word -> {
	            Map<Character, Long> repeated = word.chars()
	                .mapToObj(c -> (char) c)
	                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	                .entrySet().stream()
	                .filter(entry -> entry.getValue() > 1)
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	            System.out.println("Repeated characters in \"" + word + "\": " + repeated);
	        });




	}

}
