package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.module4.projecthibernate2.entity.City;
import ru.javarush.module4.projecthibernate2.entity.Film;

public class FilmDAO extends AbstractDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    public Film getFirstAvailableFilmForRent() {
        Query<Film> query = getCurrentSession().createQuery(
                "select f from Film f where f.id not in (select distinct film.id from Inventory)", Film.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
