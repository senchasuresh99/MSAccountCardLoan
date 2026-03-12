package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Interview {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String empData = "n1-a1-s1,n2-a2-s2,n3-a3-s3,n10-a10-s10";
		List<String> names = Arrays.stream(empData.split(","))
			    .map(entry -> entry.split("-")[0])
			    .collect(Collectors.toList());
			System.out.println(names);
	}

}
