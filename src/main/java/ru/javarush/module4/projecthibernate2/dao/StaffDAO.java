package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import ru.javarush.module4.projecthibernate2.entity.Staff;

public class StaffDAO extends AbstractDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
