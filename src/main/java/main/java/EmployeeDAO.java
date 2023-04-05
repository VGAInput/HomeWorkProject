package main.java;

import java.util.List;

public interface EmployeeDAO {

    void insertEmployeeIntoTable(Employee employee);
    Employee selectEmployeeById(int id);
    List<Employee> getAllEmployees();
    void updateEmployee(Employee employee);
    void dropEmployee(Employee employee);

}
