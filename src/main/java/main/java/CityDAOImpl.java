package main.java;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class CityDAOImpl implements CityDAO {
    private Connection connection;

    public CityDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertCityIntoTable(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City selectCityById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
    }


    @Override
    public List<City> getAllCities() {

        List<City> cities = (List<City>) HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery("FROM City").list();
        return cities;

    }

    @Override
    public void updateCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }


    }

    @Override
    public void dropCity(City city) {
        try (
                Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
