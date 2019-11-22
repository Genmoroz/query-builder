package zodiac.mapper.update;

import zodiac.mapper.WhereClauseValueProvider;
import zodiac.mapper.preparer.DataPreparer;

import java.util.Objects;

public class UpdateWhereClause implements WhereClauseValueProvider<UpdateWhereClauseValue> {

    private final UpdateQuery updateQuery;

    private DataPreparer preparer;

    UpdateWhereClause(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
        preparer = updateQuery.getDataPreparer();
    }

    @Override
    public UpdateWhereClauseValue equalsString(String val) {
        return appendString(val, " = ");
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
        return appendLong(val, " = ");
    }

    @Override
    public UpdateWhereClauseValue equalsDouble(Double val, String formatPattern) {
        return appendDouble(val, formatPattern, " = ");
    }

    @Override
    public UpdateWhereClauseValue equalsBoolean(Boolean val, String trueReplacement, String falseReplacement) {
        return appendBoolean(val, trueReplacement, falseReplacement, " = ");
    }

    @Override
    public UpdateWhereClauseValue equalsDate(String date) {
        return appendDate(date, " = ");
    }

    @Override
    public UpdateWhereClauseValue equalsTimestamp(Long val) {
        return appendTimestamp(val, " = ");

    }

    @Override
    public UpdateWhereClauseValue notEqualsString(String val) {
        return appendString(val, " <> ");
    }

    @Override
    public UpdateWhereClauseValue notEqualsLong(Long val) {
        return appendLong(val, " <> ");
    }

    @Override
    public UpdateWhereClauseValue notEqualsDouble(Double val, String formatPattern) {
        return appendDouble(val, formatPattern, " <> ");
    }

    @Override
    public UpdateWhereClauseValue notEqualsBoolean(Boolean val, String trueReplacement, String falseReplacement) {
        return appendBoolean(val, trueReplacement, falseReplacement, " <> ");
    }

    @Override
    public UpdateWhereClauseValue notEqualsDate(String val) {
        return appendDate(val, " <> ");
    }

    @Override
    public UpdateWhereClauseValue notEqualsTimestamp(Long val) {
        return appendTimestamp(val, " <> ");
    }

    private UpdateWhereClauseValue appendString(String val, String equalityCondition) {
        String preparedVal = preparer.prepareString(val);
        validValue(preparedVal, "String");

        updateQuery.query
                .append(equalityCondition)
                .append(preparedVal);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private UpdateWhereClauseValue appendLong(Long val, String equalityCondition) {
        String preparedVal = preparer.prepareLong(val);
        validValue(preparedVal, "Long");

        updateQuery.query
                .append(equalityCondition)
                .append(preparedVal);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private UpdateWhereClauseValue appendDouble(Double val, String formatPattern, String equalityCondition) {
        String preparedVal = preparer.prepareDouble(val, formatPattern);
        validValue(preparedVal, "Double");

        updateQuery.query
                .append(equalityCondition)
                .append(preparedVal);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private UpdateWhereClauseValue appendBoolean(Boolean val, String trueReplacement, String falseReplacement, String equalityCondition) {
        String preparedVal = preparer.prepareBoolean(val, trueReplacement, falseReplacement);
        validValue(preparedVal, "Boolean");

        updateQuery.query
                .append(equalityCondition)
                .append(preparedVal);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private UpdateWhereClauseValue appendDate(String date, String equalityCondition) {
        String preparedVal = preparer.prepareDate(date);
        validValue(preparedVal, "String Date");

        updateQuery.query
                .append(equalityCondition)
                .append(preparedVal);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private UpdateWhereClauseValue appendTimestamp(Long val, String equalityCondition) {
        String preparedVal = preparer.prepareTimestamp(val);
        validValue(preparedVal, "String Timestamp");

        updateQuery.query
                .append(equalityCondition)
                .append(preparedVal);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private <T> void validValue(T val, String valueClass) {
        if (Objects.isNull(val)) {
            throw new NullPointerException(valueClass + " value cannot be null");
        }
    }
}
