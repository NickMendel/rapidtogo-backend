package io.rapidtogo.rapidtogo.utils.Date_Time;

import java.time.LocalTime;
import org.springframework.stereotype.Component;

@Component
public class IntervalFormatter {

  public LocalTime[] parseTimeInterval(String timeInterval) {

    String[] time = timeInterval.split("-");

    return new LocalTime[]{LocalTime.parse(time[0]), LocalTime.parse(time[1])};
  }

  public String formatTimeInterval(LocalTime[] timeInterval) {

    return timeInterval[0].toString() + "-" + timeInterval[1].toString();
  }
}
