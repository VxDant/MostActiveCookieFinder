package com.findmostactivecookie.implementations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {



    public static void main(String[] args){

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 95000, 5),
                new Employee("Bob", "HR", 65000, 3),
                new Employee("Charlie", "Engineering", 105000, 8),
                new Employee("Diana", "Marketing", 75000, 4),
                new Employee("Evan", "HR", 70000, 6),
                new Employee("Fiona", "Engineering", 115000, 10)
        );

        Map<String, Double> highEarners = employees.stream()
                .filter(e -> e.getSalary() > 80000
                        && e.getYearsOfExperience() > 5)
                        .collect(Collectors.toMap(
                                Employee::getName,
                                Employee::getSalary
                        ));

        System.out.println("High Earners are: " + highEarners);

        List<String> highEarnersByName = employees.stream()
                .filter(e -> e.getSalary() > 80000).map(Employee::getName)
                .filter(e -> e.startsWith("C"))
                .collect(Collectors.toList());

        System.out.println(highEarnersByName);

        Map<String, List<Employee>> groupByDepartment = employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        Map<String, List<String>> groupNamesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.mapping(
                                Employee::getName,
                                Collectors.toList()
                        )
                ));

        Employee highestPaidEmployee = employees.stream()
                        .filter(
                                e -> e.getSalary() == Collections.max(employees
                                        .stream()
                                        .map(Employee::getSalary)
                                        .toList())
                        )
                .findFirst()
                .orElse(null);

        System.out.println("HighestPaidEmployee::" + highestPaidEmployee);

        Employee highestPaidEmployee1 = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)).orElse(null);

        String highestPaidEmployeeName = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary)).map(Employee::getName).orElse(null);

        System.out.println("Highest paid employee:" + highestPaidEmployee1);
        System.out.println("Highest paid employee:" + highestPaidEmployeeName);






        System.out.println(groupByDepartment);
        System.out.println(groupNamesByDepartment);
        System.out.println("last one:" + highestPaidEmployee1);

        double averageSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(averageSalary);


        List<String> employeeNamesByDescSalary = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed()).map(Employee::getName).toList();

        System.out.println(employeeNamesByDescSalary);

        Map<String, Double> AverageSalaryDeptWise = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.averagingDouble(
                                        Employee::getSalary
                                )
                        )
                );










    }
}
