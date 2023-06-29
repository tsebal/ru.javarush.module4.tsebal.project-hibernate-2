package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.module4.projecthibernate2.entity.City;

public class CityDAO extends AbstractDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getByName(String cityName) {
         Query<City> query = getCurrentSession().createQuery("select c from City c where c.cityName = :cityName", City.class);
         query.setParameter("cityName", cityName);
         query.setMaxResults(1);
         return query.getSingleResult();
    }
}
