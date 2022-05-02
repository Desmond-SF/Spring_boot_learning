package com.thanhtoan.first.idiot.service;

import java.util.List;

import com.thanhtoan.first.idiot.entity.Employee;
import com.thanhtoan.first.idiot.repository.EmployeeDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDAOJPAImpl") EmployeeDAO theEmployeeDAO) {
        employeeDAO = theEmployeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Employee theEmployee) {
        if (theEmployee.getPassWord().length() != 64) {
            theEmployee.setPassWord(passwordEncoder().encode(theEmployee.getPassWord()));
        }
        employeeDAO.save(theEmployee);
    }

    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDAO.findByEmail(email);
    }
}
