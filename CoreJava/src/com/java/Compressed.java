package com.java;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Compressed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="xxxxyyyyzzzz";
		String output=compressed(str);
      System.out.println("output:"+output);
	}

	private static String compressed(String str) {
		// TODO Auto-generated method stub
		   Map<Character,Long> countMap=str.chars()
				   .mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		    StringBuilder compressed=new StringBuilder();

		  countMap.forEach((key,value)->compressed.append(key).append(value));
		   
		   return compressed.toString();
	}

}
