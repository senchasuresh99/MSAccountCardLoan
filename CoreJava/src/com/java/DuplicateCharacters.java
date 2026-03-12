package com.java;

import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str="Sreenivasulu swarna";
		str.chars().mapToObj(c->(char)c)
		.filter(Character::isLetterOrDigit)
		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
		.entrySet()
		.stream()
		.filter(entry->entry.getValue()>1)
		.forEach(System.out::println);

	}

}
