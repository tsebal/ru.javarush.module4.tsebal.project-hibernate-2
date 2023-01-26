package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import ru.javarush.module4.projecthibernate2.entity.Address;

public class AddressDAO extends AbstractDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
