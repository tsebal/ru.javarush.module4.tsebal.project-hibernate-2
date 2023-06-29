package ru.javarush.module4.projecthibernate2.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.javarush.module4.projecthibernate2.entity.Actor;
import ru.javarush.module4.projecthibernate2.entity.Address;
import ru.javarush.module4.projecthibernate2.entity.Category;
import ru.javarush.module4.projecthibernate2.entity.City;
import ru.javarush.module4.projecthibernate2.entity.Country;
import ru.javarush.module4.projecthibernate2.entity.Customer;
import ru.javarush.module4.projecthibernate2.entity.Film;
import ru.javarush.module4.projecthibernate2.entity.FilmText;
import ru.javarush.module4.projecthibernate2.entity.Inventory;
import ru.javarush.module4.projecthibernate2.entity.Language;
import ru.javarush.module4.projecthibernate2.entity.Payment;
import ru.javarush.module4.projecthibernate2.entity.Rental;
import ru.javarush.module4.projecthibernate2.entity.Staff;
import ru.javarush.module4.projecthibernate2.entity.Store;

public class MovieDBSessionFactory {
    private static MovieDBSessionFactory instance;
    private final SessionFactory sessionFactory;

    private MovieDBSessionFactory() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new MovieDBSessionFactory();
        }
        return instance.sessionFactory;
    }
}
