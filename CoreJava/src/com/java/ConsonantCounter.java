package com.java;

import java.util.Arrays;
import java.util.List;

public class ConsonantCounter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  List<String> words = Arrays.asList("Hello", "World", "Java", "Programming");
   long countOwels=    words.stream().flatMapToInt(String::chars)
        .mapToObj(c->(char)c)
        .filter(c -> !"AEIOUaeiou".contains(c.toString()))
        .count();
  System.out.println(countOwels);
	}

}
