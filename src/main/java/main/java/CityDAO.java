package main.java;

import java.util.List;

public interface CityDAO {

    void insertCityIntoTable(City city);
    City selectCityById(int id);
    List<City> getAllCities();
    void updateCity(City city);
    void dropCity(City city);

}
