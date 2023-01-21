package ru.javarush.module4.projecthibernate2.service;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MovieDBConnectionProvider {

    public static SessionFactory getSessionFactory() {

        Configuration configuration = new Configuration().addResource("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration
                .buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }
}
