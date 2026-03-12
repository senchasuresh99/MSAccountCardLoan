package com.java;

import java.util.Scanner;

public class Factorial {
	public static void main(String args[]) {
    try (Scanner sc = new Scanner(System.in)) {
		System.out.println("Enter number:");
		int num=sc.nextInt();
   long factorial=1;
   
   for(int i=1;i<=num;i++) {
		   factorial *=i;
   }
   System.out.println("factorial of"  +num+  "is:"+factorial);
	}
	}
}
