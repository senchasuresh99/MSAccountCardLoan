package com.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  int[] nums = {1, 2, 3, 2, 4, 5, 1};
        HashSet<Integer> set=new HashSet<>();
        HashSet<Integer> duplicates = new HashSet<>();
       for(int num:nums) {
    	   if(!set.add(num)) {
    		   duplicates.add(num);
    	   }
    	   
		}
        System.out.println("Duplicates...." +duplicates);
        int[] nums1 = {1, 2, 3, 2, 4, 5, 1};

        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicate = Arrays.stream(nums1)
            .boxed()
            .filter(n -> !seen.add(n)) // add returns false if n is already in the set
            .collect(Collectors.toSet());

        System.out.println("Duplicates: " + duplicate);

	}

}