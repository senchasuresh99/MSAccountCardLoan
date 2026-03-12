package com.java;

import java.util.Arrays;

public class AnagramCheck {
	public static boolean isAnagram(String str1, String str2) {
		if (str1.length()!=str2.length()) {
			return false;
		}else {
			char[] a1=str1.toCharArray();
			char[] b1=str2.toCharArray();
			Arrays.sort(a1);
			Arrays.sort(b1);
			return Arrays.equals(a1, b1);
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

	}

}
