package com.thanhtoan.first.idiot.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.thanhtoan.first.idiot.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    // define fied for entity
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    // @Transactional handle transaction -> dont need to commit and start
    // transaction
    public List<Employee> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        // create a Query
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
        // execute query and get result List
        List<Employee> employees = theQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee and return
        Employee theEmployee = currentSession.get(Employee.class, id);

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        // get the current hibernate sessio
        try (Session currentSession = entityManager.unwrap(Session.class)) {
            // save the employee and return
            currentSession.saveOrUpdate(theEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        // get the current hibernate sessio
        Session currentSession = entityManager.unwrap(Session.class);

        // delete the employee with primary key
        Query theQuery = currentSession.createQuery("delete from Employee where id =:employeeID");
        theQuery.setParameter("employeeID", id);
        theQuery.executeUpdate();
    }
}
