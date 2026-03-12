package com.java;

import java.util.Arrays;

public class StringExample {

	public static boolean isAnagram(String str1, String str2) {
		if(str1.length()!=str2.length()) {
			return false;
		}else {
			
		char[] a = str1.toCharArray();
		char[] b = str2.toCharArray();
		Arrays.sort(a);
		Arrays.sort(b);
		return Arrays.equals(a, b);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String str1 = "listen";
	        String str2 = "silent";

	        if (isAnagram(str1, str2)) {
	            System.out.println("The strings are anagrams.");
	        } else {
	            System.out.println("The strings are not anagrams.");
	        }

		int x = 5;
		int y = ++x * 2;
		System.out.println(y);

	}

}
