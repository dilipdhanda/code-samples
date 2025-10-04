package com.c1.Comparable_Comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class EmployeeComparator implements Comparator<Employee>{ // do not public,
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getSalary().compareTo(e2.getSalary());
    }
}

public class Comparator_Employee { // only one class can be public

    public static void main(String a[]){

        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(10, "Raghu", 25000));
        employees.add(new Employee(120, "Krish", 45000));
        employees.add(new Employee(210, "John", 14000));
        employees.add(new Employee(150, "Kishore", 24000));

        Employee maxSal = Collections.max(employees, new EmployeeComparator());

        System.out.println("Employee with max salary: "+maxSal);
    }
}

class Employee {

    private int id;
    private String name;
    private Integer salary;

    public Employee(int id, String name, Integer sal){
        this.id = id;
        this.name = name;
        this.salary = sal;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSalary() {
        return salary;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public String toString(){
        return id+"  "+name+"   "+salary;
    }
}