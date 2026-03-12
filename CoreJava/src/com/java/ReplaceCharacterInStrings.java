package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReplaceCharacterInStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 List<String> input = Arrays.asList("apple", "elephant", "tree", "desk");
		List<String> result= input.stream()
		    .map(str->str.replace('e', 'x'))
		    .collect(Collectors.toList());
		System.out.println(result);

	}

}
