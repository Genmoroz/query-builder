package zodiac.mapper.update;

import zodiac.mapper.WhereClauseValueProvider;
import zodiac.mapper.utils.DateFormatter;
import zodiac.mapper.utils.StringFormatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.TimeZone;

import static zodiac.mapper.utils.StringFormatter.formatQuotedString;
import static zodiac.mapper.utils.StringFormatter.quote;

public class UpdateWhereClause implements WhereClauseValueProvider<UpdateWhereClauseValue> {

    private final UpdateQuery updateQuery;

    UpdateWhereClause(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateWhereClauseValue equalsString(String val) {
        validValue(val, "String");
        updateQuery.query
                .append(" = ")
                .append(quote(formatQuotedString(val)));

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue isNull() {
        updateQuery.query
                .append(" IS NULL ");

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue nonNull() {
        updateQuery.query
                .append(" IS NOT NULL ");

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue equalsLong(Long val) {
        validValue(val, "Long");
        updateQuery.query
                .append(" = ")
                .append(val);

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue equalsDouble(Double val, String formatPattern) {
        validValue(val, "Double");
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

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue equalsBoolean(Boolean condition, String trueReplacement, String falseReplacement) {
        validValue(condition, "Boolean");
        updateQuery.query
                .append(" = ")
                .append(
                        StringFormatter.convertBooleanToStringAndQuote(condition, trueReplacement, falseReplacement)
                );

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue equalsDate(String date) {
        String preparedDate = DateFormatter.convertToDateFormat(
                date, "yyyy-MM-dd", "dd/MM/yyyy", TimeZone.getTimeZone("UTC")
        );
        validValue(preparedDate, "String Date");
        updateQuery.query
                .append(" = ")
                .append(preparedDate);


        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue equalsTimestamp(Long val) {
        String preparedTimestamp = DateFormatter.convertToTimestampFormat(
                val, "dd/MM/yyyy HH:mm:ss", "DD/MM/YYYY HH24:MI:SS", TimeZone.getTimeZone("UTC")
        );
        validValue(preparedTimestamp, "String Timestamp");
        updateQuery.query
                .append(" = ")
                .append(preparedTimestamp);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private <T> void validValue(T val, String valueClass) {
        if (Objects.isNull(val)) {
            throw new NullPointerException(valueClass + " value cannot be null");
        }
    }
}
