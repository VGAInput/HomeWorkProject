package main.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertEmployeeIntoTable(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (first_name, last_name, gender," +
                        " age, city_id) VALUES ((?), (?), (?), (?), (?))")) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee selectEmployeeById(int id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            int n = 0;

            if (resultSet.next()) {
                n = resultSet.getInt(1);
            }
            if (n > 0) {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt("age"));
                employee.setCityId(Integer.parseInt("city_id"));
                return employee;
            }
        } catch (SQLException e) {
            System.out.println("Employee not found.");
            return null;
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("id"));
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                int city_id = Integer.parseInt(resultSet.getString("city_id"));

                employeeList.add(new Employee(first_name, last_name, gender, age, city_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
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
