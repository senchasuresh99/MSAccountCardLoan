package com.java;

import java.util.Arrays;
import java.util.List;

public class SquireEvenNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      List<Integer> listOFintegers=Arrays.asList(5,10,4,13,6,7,18,23);
     int squiresOfeven= listOFintegers.stream().sorted().filter(n->n%2==0)
    		 .mapToInt(Integer::intValue)
    		 .sum();
	System.out.println(squiresOfeven);
	}

}
