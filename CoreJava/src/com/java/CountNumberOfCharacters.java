package com.java;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountNumberOfCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       String str="this is java intervew with Natwest , java coding &";
     Map<Character, Long>  numberOfCharcter=str.chars()
    		 .filter(Character::isLetterOrDigit)
               .mapToObj(c->(char)c)
               .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
     System.out.println(numberOfCharcter);
	}

}
