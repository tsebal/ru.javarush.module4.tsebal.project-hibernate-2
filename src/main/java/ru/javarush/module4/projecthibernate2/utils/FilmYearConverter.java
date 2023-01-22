package ru.javarush.module4.projecthibernate2.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

@Converter(autoApply = true)
public class FilmYearConverter implements AttributeConverter<Year, Short> {
    @Override
    public Short convertToDatabaseColumn(Year filmYear) {
        return (filmYear != null) ? (short) filmYear.getValue() : null;
    }

    @Override
    public Year convertToEntityAttribute(Short dbData) {
        return (dbData != null) ? Year.of(dbData) : null;
    }
}
