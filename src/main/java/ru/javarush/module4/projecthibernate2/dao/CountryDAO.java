package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import ru.javarush.module4.projecthibernate2.entity.Country;

public class CountryDAO extends AbstractDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
