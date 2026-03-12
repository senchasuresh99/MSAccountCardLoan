package com.java;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
	int id;
	int salary;
	String name;
	String department;

	public Employee(int id, int salary, String name, String department) {
		this.id = id;
		this.salary = salary;
		this.name = name;
		this.department = department;
	}

	@Override
	public String toString() {
		return name + " (" + department + ", ₹" + salary + ")";
	}
}

public class Main {
	public static void main(String[] args) {
		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee(1, 2000, "n1", "IT"));
		empList.add(new Employee(2, 2001, "n2", "HR"));
		empList.add(new Employee(3, 200, "n3", "IT"));
		empList.add(new Employee(4, 20000, "n4", "IT"));

		Map<Boolean, List<Employee>> map = empList.stream()
				.collect(Collectors.groupingBy(emp -> emp.department.equals("IT")));

		System.out.println(map);
	}
}