package com.java;

public class StringPalendrom {
    public static boolean isPalindrome(String input) {
    	String reversed = new StringBuilder(input).reverse().toString();
        return input.equals(reversed);

    }

    public static void main(String[] args) {
        System.out.println("Is 'radar' a palindrome? " + isPalindrome("radar"));     // true
        System.out.println("Is 'apple' a palindrome? " + isPalindrome("apple"));     // false
        System.out.println("Is 'RaceCar' a palindrome? " + isPalindrome("RaceCar")); // true
    }
}