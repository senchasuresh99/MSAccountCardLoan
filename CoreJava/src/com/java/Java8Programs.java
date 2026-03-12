package com.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Programs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// find the even numbers from list
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		numbers.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

		int sumOFnumber = numbers.stream().filter(n -> n % 2 == 0) // Filter for even numbers
				.mapToInt(Integer::intValue) // Convert to IntStream for sum() method
				.sum(); // ;
		System.out.println(sumOFnumber);

		// sorted order
		numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		// check two strings are
		String s1 = "listen", s2 = "silent";

		boolean isAnagram = Stream.of(s1.split("")).sorted().collect(Collectors.joining())
				.equals(Stream.of(s2.split("")).sorted().collect(Collectors.joining()));
		System.out.println(isAnagram); // Output: true

		// find second highest nubmer

		int secondHighest = numbers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElseThrow();
		System.out.println(secondHighest);
		
		String input = "456";

		Optional<Integer> number = Optional.of(input)
		    .filter(str -> str.chars().allMatch(Character::isDigit))
		    .map(Integer::parseInt);

		number.ifPresent(n -> System.out.println("Converted number: " + n));

	}

}
