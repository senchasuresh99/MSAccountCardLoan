package com.java;

import java.util.Scanner;

public class ReverseNmuber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    int reverse=0;
    Scanner sc=new Scanner(System.in);
    System.out.println("Please give the number :");
    int i=sc.nextInt();
    while(i!=0) {
    	reverse=reverse*10 + i%10;
    	i=i/10;
    }
    System.out.println("Number after reverse :"+reverse);
	}

}
