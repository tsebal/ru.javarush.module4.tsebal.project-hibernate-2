package ru.javarush.module4.projecthibernate2.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.javarush.module4.projecthibernate2.entity.Rating;

import java.util.Arrays;

@Converter(autoApply = true)
public class FilmRatingConverter implements AttributeConverter<Rating, String> {
    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        Rating[] ratingValues = Rating.values();
        return Arrays.stream(ratingValues)
                .filter((x) -> x.getValue().equals(dbData))
                .findFirst()
                .orElse(null);
    }
}
