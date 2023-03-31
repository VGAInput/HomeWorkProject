package main.java;

import java.util.List;

public interface EmployeeDAO {

    void insertEmployeeIntoTable(Employee employee);
    Employee selectEmployeeById(int id);
    List<Employee> getAllEmployees();
    void updateEmployee(int id,String first_name,String last_name,String gender,int age,int city_id);
    void dropEmployee(int id);

}
