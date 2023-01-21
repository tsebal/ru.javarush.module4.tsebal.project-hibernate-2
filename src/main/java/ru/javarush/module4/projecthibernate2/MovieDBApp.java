package ru.javarush.module4.projecthibernate2;

import org.hibernate.SessionFactory;
import ru.javarush.module4.projecthibernate2.service.MovieDBConnectionProvider;

public class MovieDBApp {
    private final SessionFactory sessionFactory;

    public MovieDBApp() {
        sessionFactory =  MovieDBConnectionProvider.getSessionFactory();
    }

    public static void main(String[] args) {
        MovieDBApp movieDBApp = new MovieDBApp();

    }
}
