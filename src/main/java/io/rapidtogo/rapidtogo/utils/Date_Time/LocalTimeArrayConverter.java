package io.rapidtogo.rapidtogo.utils.Date_Time;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Converter
public class LocalTimeArrayConverter implements AttributeConverter<LocalTime[], String> {

  @Override
  public String convertToDatabaseColumn(LocalTime[] attribute) {

    if (attribute == null) {
      return null;
    }

    return Arrays.stream(attribute)
        .map(LocalTime::toString)
        .collect(Collectors.joining(","));
  }

  @Override
  public LocalTime[] convertToEntityAttribute(String dbData) {

    if (dbData == null || dbData.isEmpty()) {
      return new LocalTime[0];
    }

    return Arrays.stream(dbData.split(","))
        .map(LocalTime::parse)
        .toArray(LocalTime[]::new);
  }

}
