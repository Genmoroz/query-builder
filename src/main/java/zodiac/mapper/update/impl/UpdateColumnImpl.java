package zodiac.mapper.update.impl;

import zodiac.mapper.update.UpdateColumn;
import zodiac.mapper.update.UpdateColumnValue;
import zodiac.mapper.utils.DateFormatter;
import zodiac.mapper.utils.StringFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.TimeZone;

class UpdateColumnImpl implements UpdateColumn {

    private final UpdateQueryImpl updateQuery;

    UpdateColumnImpl(UpdateQueryImpl updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateColumnValue setString(String val) {
        updateQuery.query
                .append(" = ")
                .append(StringFormatter.quote(val));

        return new UpdateColumnValueImpl(updateQuery);
    }

    @Override
    public UpdateColumnValue setLong(Long val) {
        updateQuery.query
                .append(" = ")
                .append(val.longValue());

        return new UpdateColumnValueImpl(updateQuery);
    }

    /**
     * @param formatPattern should be in [#0.00] or [#0.000] format (without square brackets)
     */
    @Override
    public UpdateColumnValue setDouble(Double val, String formatPattern) {
        String doubleVal;
        if (Objects.isNull(formatPattern)) {
            doubleVal = String.valueOf(val.doubleValue());
        } else {
            NumberFormat formatter = new DecimalFormat(formatPattern);
            doubleVal = formatter.format(val);
        }
        updateQuery.query
                .append(" = ")
                .append(doubleVal);

        return new UpdateColumnValueImpl(updateQuery);
    }

    @Override
    public UpdateColumnValue setBoolean(Boolean condition, String trueReplacement, String falseReplacement) {
        updateQuery.query
                .append(" = ")
                .append(
                        StringFormatter.convertBooleanToStringAndQuote(condition, trueReplacement, falseReplacement)
                );

        return new UpdateColumnValueImpl(updateQuery);
    }

    @Override
    public UpdateColumnValue setDate(String date) {
        String preparedDate = DateFormatter.convertToDateFormat(
                date, "yyyy-MM-dd", "dd/MM/yyyy", TimeZone.getTimeZone("UTC")
        );
        updateQuery.query
                .append(" = ")
                .append(preparedDate);

        return new UpdateColumnValueImpl(updateQuery);
    }

    @Override
    public UpdateColumnValue setTimestamp(Long val) {
        String preparedTimestamp = DateFormatter.convertToTimestampFormat(
                val, "dd/MM/yyyy HH:mm:ss", "DD/MM/YYYY HH24:MI:SS", TimeZone.getTimeZone("UTC")
        );
        updateQuery.query
                .append(" = ")
                .append(preparedTimestamp);

        return new UpdateColumnValueImpl(updateQuery);
    }
}
