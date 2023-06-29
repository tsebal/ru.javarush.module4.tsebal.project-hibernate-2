package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import ru.javarush.module4.projecthibernate2.entity.Inventory;

public class InventoryDAO extends AbstractDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
