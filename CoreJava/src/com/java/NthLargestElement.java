package com.java;

import java.util.Arrays;
import java.util.Comparator;

public class NthLargestElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int arr[]= {12,4,3,1,5,657};
        int i=3;
        Arrays.stream(arr)
        .boxed()
        .sorted(Comparator.naturalOrder())
        .skip(i-1)
        .findFirst()
        .ifPresent(System.out::println);
	}

}
