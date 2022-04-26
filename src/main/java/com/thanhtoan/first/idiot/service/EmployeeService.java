package com.thanhtoan.first.idiot.service;

import java.util.List;

import com.thanhtoan.first.idiot.entity.Employee;

public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findById(int id);
    public Employee findByEmail(String email);
    public void save(Employee theEmployee);
    public void deleteById(int id);
}
