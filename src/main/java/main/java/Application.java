package main.java;

import java.sql.*;

public class Application {

    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "0451";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);

            Employee newEmployee = new Employee("Andrey", "Martynov", "MALE", 37, 4);
            employeeDAO.insertEmployeeIntoTable(newEmployee);

            employeeDAO.selectEmployeeById(4);

            employeeDAO.getAllEmployees();

            employeeDAO.updateEmployee(newEmployee);

            employeeDAO.dropEmployee(newEmployee);
        }

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("" +
                     "SELECT * FROM employee WHERE id = (?)")) {

            statement.setInt(1, 3);

            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String firstName = resultSet.getString("first_name") +
                        " " + resultSet.getString("last_name") +
                        ", " + resultSet.getString("gender") +
                        ", city id: " + resultSet.getString("city_id");
                System.out.println(firstName);
            }
        }
    }

}
