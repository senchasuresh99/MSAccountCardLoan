package com.java;

public class SwapNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 10;
		int b = 20;
		System.out.println("Before swapping: a	 = " + a + ", b = " + b);
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println("after swapping: a = " + a + ", b = " + b);
		// Swap values using temp
		int temp; // Third variable

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Swap values using temp
        temp = a;
        a = b;
        b = temp;

        System.out.println("After swapping: a = " + a + ", b = " + b);


	}

}
