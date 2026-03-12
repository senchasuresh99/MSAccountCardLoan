package com.java;

import java.util.Arrays;

public class RemoveZero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int[] arr = {1, 0, 3, 0, 5, 4, 0, 9, 6};
        int[] result=    Arrays.stream(arr).filter(num -> num !=0)
            .toArray();
        System.out.println(Arrays.toString(result));
        
        int[] numbers = {3, 5, 7, 2, 8, -1, 4, 10, 12};
        int max=Arrays.stream(numbers)
        		.max()
        		.getAsInt();
        System.out.println(max);
	}

}
