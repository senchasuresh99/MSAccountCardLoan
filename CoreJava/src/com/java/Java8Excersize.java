package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Java8Excersize {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Kolkata", "Bangalore");
         List<String> result=cities.stream()
        		 .filter(c->c.length()>6)
        		 .collect(Collectors.toList());
         System.out.println("list o Strings whose lentgh greater then  6"+result);

         Optional<String> name = Optional.ofNullable(null);
         System.out.println(name.orElse("Guest"));

	}

}
