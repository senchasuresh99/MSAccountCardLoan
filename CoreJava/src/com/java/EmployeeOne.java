package com.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee2 {
    private String name;
    private String cityCode;

    public Employee2(String name, String cityCode) {
        this.name = name;
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public String getCityCode() {
        return cityCode;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', cityCode='" + cityCode + "'}";
    }
}

public class EmployeeOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Employee2> employees = Arrays.asList(
	            new Employee2("Alice", "PQR123"),
	            new Employee2("Bob", "XYZ456"),
	            new Employee2("Charlie", "PAB789"),
	            new Employee2("David", "MNO321")
	        );
    List<Employee2> filterd=employees.stream()
    		             .filter(e->e.getCityCode().startsWith("P"))
    		             .collect(Collectors.toList());
    filterd.forEach(System.out::println);
	}

}
	