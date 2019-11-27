package zodiac.builder.preparer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.TimeZone;

import static zodiac.builder.preparer.utils.StringFormatter.quote;

public class DateFormatter {

    public static String convertToTimestampFormat(Long val, String targetPattern, String databasePattern, TimeZone timeZone) {
        if (Objects.isNull(val)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(targetPattern);
        sdf.setTimeZone(timeZone);
        Calendar calendar = GregorianCalendar.getInstance(timeZone);
        calendar.setTimeInMillis(val);

        String timestamp = sdf.format(calendar.getTime());

        return String.format("CAST(TO_TIMESTAMP(%s, '%s') AS DATE)", quote(timestamp), databasePattern);
    }

    public static String convertToDateFormat(String val, String sourcePattern, String targetPattern, TimeZone timeZone) {
        if (Objects.isNull(val)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(sourcePattern);
        sdf.setTimeZone(timeZone);
        Calendar calendar = GregorianCalendar.getInstance(timeZone);
        Date date;
        try {
            calendar.setTime(date = sdf.parse(val));
        } catch (ParseException e) {
            throw new RuntimeException(
                    String.format(
                            "ParseException caught! Value %s cannot be parsed, expected pattern %s", val, sourcePattern
                    )
            );
        }

        sdf = new SimpleDateFormat(targetPattern);

        return String.format("CAST(TO_DATE(%s, '%s') AS DATE)", quote(sdf.format(date)), targetPattern.toUpperCase());
    }
}
