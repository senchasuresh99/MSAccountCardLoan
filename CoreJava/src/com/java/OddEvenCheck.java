package com.java;

import java.util.Scanner;

public class OddEvenCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();

		if (num % 2 == 0) {
         System.out.println(num + "is even");
		}else {
			System.out.println(num +"is odd");
		}
	}

}
