package com.java;

import java.util.Arrays;
import java.util.Comparator;

public class SecondHighest {
    public static void main(String[] args) {
        int[] arr = {10, 4, 8, 17, 60, 67, 63, 65};

        // Initialize highest and secondHighest
        int highest = Integer.MIN_VALUE;
        int secondHighest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > highest) {
                secondHighest = highest;
                highest = num;
            } else if (num > secondHighest && num < highest) {
                secondHighest = num;
            }
        }

        System.out.println("Second highest number is: " + secondHighest);
        
        int[] arr1 = {10, 4, 8, 17, 60, 67, 63, 65};
        System.out.println(Arrays.stream(arr1).boxed().sorted(Comparator.reverseOrder())
        		.skip(1).findFirst());
    }
}
