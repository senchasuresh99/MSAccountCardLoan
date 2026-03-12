package com.java;

import java.util.Arrays;

public class Test1 {
	public static void main(String[] args) {
		final String input = "John:25,Kate:19,George:30,Michael:18,Julia:22";

		Arrays.stream(input.split(",")).map(entry -> entry.split(":")).filter(parts -> Integer.parseInt(parts[1]) > 20)
				.forEach(parts -> System.out.println(parts[0] + " (" + parts[1] + ")"));
	}
}