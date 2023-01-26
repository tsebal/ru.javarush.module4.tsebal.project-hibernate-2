package ru.javarush.module4.projecthibernate2.dao;

import org.hibernate.SessionFactory;
import ru.javarush.module4.projecthibernate2.entity.Language;

public class LanguageDAO extends AbstractDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
