package main.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "0451";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        List<Employee> employeeList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            //Добавление нового населённого пункта в базу данных.
            CityDAO cityDAO = new CityDAOImpl(connection);
            City newCity = new City("Tver'");

            // Создание списка работников

            employeeList.add(new Employee("Andrey", "Martynov", "MALE", 37, 12));
            employeeList.add(new Employee("Marina", "Eduardova", "FEMALE", 29, 12));
            employeeList.add(new Employee("Anna", "Arbuzova", "FEMALE", 41, 12));
            employeeList.add(new Employee("Evgeniy", "Kuzmin", "MALE", 35, 12));

            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            // Добавление новых работников в базу данных.
            for (Employee e : employeeList)
                employeeDAO.insertEmployeeIntoTable(e);

            employeeDAO.selectEmployeeById(4);
            employeeDAO.getAllEmployees();

            //Замените одного из сотрудников в городе,
            employeeDAO.updateEmployee(new Employee("Sergey", "Arturov", "MALE", 27, 11));

            employeeDAO.dropEmployee(employeeList.get(2));

            //Удаление экземпляра City из БД.
            cityDAO.dropCity(newCity);

        }
    }


}
