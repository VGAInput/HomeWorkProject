package main.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertEmployeeIntoTable(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee selectEmployeeById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }


    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("FROM Employee").list();
        return employees;

    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }


    }

    @Override
    public void dropEmployee(Employee employee) {
        try (
                Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
