package zodiac.mapper.preparer.danaos;

import zodiac.mapper.preparer.DataPreparer;
import zodiac.mapper.utils.DateFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.TimeZone;

import static zodiac.mapper.utils.StringFormatter.convertBooleanToStringAndQuote;
import static zodiac.mapper.utils.StringFormatter.formatQuotedString;
import static zodiac.mapper.utils.StringFormatter.quote;

public class DanaosDataPreparer implements DataPreparer {

    @Override
    public String prepareString(String str) {
        return quote(formatQuotedString(str));
    }

    @Override
    public String prepareDouble(Double val, String formatPattern) {
        if (Objects.isNull(val)) {
            return null;
        }
        String doubleVal;
        if (Objects.isNull(formatPattern)) {
            doubleVal = String.valueOf(val.doubleValue());
        } else {
            NumberFormat formatter = new DecimalFormat(formatPattern);
            doubleVal = formatter.format(val);
        }
        return doubleVal;
    }

    @Override
    public String prepareLong(Long val) {
        if (Objects.isNull(val)) {
            return null;
        }
        return String.valueOf(val.longValue());
    }

    @Override
    public String prepareBoolean(Boolean condition, String trueReplacement, String falseReplacement) {
        if (Objects.isNull(condition)) {
            return null;
        }
        return convertBooleanToStringAndQuote(condition, trueReplacement, falseReplacement);
    }

    @Override
    public String prepareDate(String stringDate) {
        if (Objects.isNull(stringDate)) {
            return null;
        }
        return DateFormatter.convertToDateFormat(
                stringDate, "yyyy-MM-dd", "dd/MM/yyyy", TimeZone.getTimeZone("UTC")
        );
    }

    @Override
    public String prepareTimestamp(Long val) {
        if (Objects.isNull(val)) {
            return null;
        }
        return DateFormatter.convertToTimestampFormat(
                val, "dd/MM/yyyy HH:mm:ss", "DD/MM/YYYY HH24:MI:SS", TimeZone.getTimeZone("UTC")
        );
    }
}
