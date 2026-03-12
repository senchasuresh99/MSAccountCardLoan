package com.java;

import java.util.Arrays;
import java.util.Comparator;

public class SecondLargestFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   int[] numbers = {5, 9, 11, 2, 8, 21, 1};
		   int secondLargest = Arrays.stream(numbers)
				   .boxed()
				   .distinct()
				    .sorted(Comparator.reverseOrder())
				    .skip(1)
				    .findFirst()
				    .get();
		   System.out.println(secondLargest);

		   
}

}
