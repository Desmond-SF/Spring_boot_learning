package com.thanhtoan.first.idiot.repository;

import java.util.List;

import com.thanhtoan.first.idiot.entity.Employee;

public interface EmployeeDAO {
    public List<Employee> findAll();
    public Employee findById(int id);
    public void save(Employee theEmployee);
    public void deleteById(int id);
}