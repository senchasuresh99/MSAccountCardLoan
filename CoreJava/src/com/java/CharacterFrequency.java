package com.java;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterFrequency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String input = "Manoj nethagani";
    Map<Character,Long>  countCharcters= input.chars()
    		.filter(Character::isLetterOrDigit).mapToObj(c->(char)c)
    		.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
	System.out.println(countCharcters);
	}

}
