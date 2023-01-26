package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.module4.projecthibernate2.entity.Rental;

public class RentalDAO extends AbstractDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getAnyUnreturnedRental() {
        Query<Rental> query = getCurrentSession().createQuery("select r from Rental r where r.returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
