package com.findmostactivecookie.implementations;

public class Employee {

    private String name;
    private String department;
    private double salary;
    private int yearsOfExperience;

    public Employee(String name, String department, double salary, int yearsOfExperience) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + ", experience=" + yearsOfExperience + "}";
    }

}
