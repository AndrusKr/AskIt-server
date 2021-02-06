package by.andrus.askit.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {
    public static String toIsoString(Date date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
//        return sdf.format(date);
        return DateTimeFormatter.ISO_INSTANT.format(date.toInstant());
    }

    public static Date toDate(String timeString) {
        return Date.from(Instant.from(DateTimeFormatter.ISO_INSTANT.parse(timeString)));
    }
}
