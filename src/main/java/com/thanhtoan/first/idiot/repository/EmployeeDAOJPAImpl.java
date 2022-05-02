package com.thanhtoan.first.idiot.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.thanhtoan.first.idiot.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // Create a query and get the result list
        Query theQuery = entityManager.createQuery("from Employee");
        // return 
        List<Employee> employees = theQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee findByEmail(String email) {
        List<Employee> employees = findAll();
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        // Employee employee = entityManager.find(Employee.class, email);
        return null;
    }

    @Override
    public void save(Employee theEmployee) {
        Employee tmpEmployee = entityManager.merge(theEmployee);
        theEmployee.setId(tmpEmployee.getId());
        
    }

    @Override
    public void deleteById(int id) {
        Query theQuery = entityManager.createQuery("delete from Employee where id =:employeeId");
        theQuery.setParameter("employeeId", id);
        theQuery.executeUpdate();

    }
    
}
