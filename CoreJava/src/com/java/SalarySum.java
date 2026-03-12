package com.java;

import java.util.Arrays;
import java.util.List;

class Employee1 {
    private int id;
    private String name;
    private double salary;

    public Employee1(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() { return id; }
    public double getSalary() { return salary; }
}

public class SalarySum {
    public static void main(String[] args) {
        List<Employee1> employees = Arrays.asList(
            new Employee1(1, "Alice", 50000),
            new Employee1(2, "Bob", 60000),
            new Employee1(3, "Charlie", 55000),
            new Employee1(4, "Diana", 70000)
        );

        double totalOddIdSalary = employees.stream()
        		.filter(e->e.getId()%2!=0)
        		.mapToDouble(Employee1::getSalary)
        		.sum();

        System.out.println("Total salary of employees with odd IDs: " + totalOddIdSalary);
    }
}