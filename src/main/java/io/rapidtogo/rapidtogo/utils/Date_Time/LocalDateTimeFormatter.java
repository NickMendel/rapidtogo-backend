package io.rapidtogo.rapidtogo.utils.Date_Time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormatter {

  /**
   * Formats a LocalDateTime object to a string in ISO format. E.g. 2021-07-01T12:00:00.000Z
   *
   * @param localDateTime the LocalDateTime object to format
   * @return the string representation of the LocalDateTime object in ISO format
   */
  public static String formatToIso(LocalDateTime localDateTime) {

    if (localDateTime == null) {
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    return localDateTime.atOffset(ZoneOffset.UTC).format(formatter);
  }
}
