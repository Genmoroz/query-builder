package zodiac.mapper.update;

import zodiac.mapper.WhereClauseValueProvider;
import zodiac.mapper.preparer.DataPreparer;

import java.util.Objects;
import java.util.function.Supplier;

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
        updateQuery
                .append(" IS NULL");

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue nonNull() {
        updateQuery
                .append(" IS NOT NULL");

        return new UpdateWhereClauseValue(updateQuery);
    }

    @Override
    public UpdateWhereClauseValue equalsInteger(Integer val) {
        return appendInteger(val, " = ");
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
    public UpdateWhereClauseValue equalsDate(String val) {
        return appendDate(val, " = ");
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
    public UpdateWhereClauseValue notEqualsInteger(Integer val) {
        return appendInteger(val, " <> ");
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

    @Override
    public UpdateWhereClauseValue moreThanInteger(Integer val) {
        return appendInteger(val, " > ");
    }

    @Override
    public UpdateWhereClauseValue lessThanInteger(Integer val) {
        return appendInteger(val, " < ");
    }

    @Override
    public UpdateWhereClauseValue moreThanLong(Long val) {
        return appendLong(val, " > ");
    }

    @Override
    public UpdateWhereClauseValue lessThanLong(Long val) {
        return appendLong(val, " < ");
    }

    @Override
    public UpdateWhereClauseValue moreThanDouble(Double val, String formatPattern) {
        return appendDouble(val, formatPattern, " > ");
    }

    @Override
    public UpdateWhereClauseValue lessThanDouble(Double val, String formatPattern) {
        return appendDouble(val, formatPattern, " < ");
    }

    private UpdateWhereClauseValue appendString(String val, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareString(val);

        return appendValue(supplier, equalityCondition, String.class.getSimpleName());
    }

    private UpdateWhereClauseValue appendInteger(Integer val, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareInteger(val);

        return appendValue(supplier, equalityCondition, Integer.class.getSimpleName());
    }

    private UpdateWhereClauseValue appendLong(Long val, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareLong(val);

        return appendValue(supplier, equalityCondition, Long.class.getSimpleName());
    }

    private UpdateWhereClauseValue appendDouble(Double val, String formatPattern, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareDouble(val, formatPattern);

        return appendValue(supplier, equalityCondition, Double.class.getSimpleName());
    }

    private UpdateWhereClauseValue appendBoolean(Boolean val, String trueReplacement, String falseReplacement, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareBoolean(val, trueReplacement, falseReplacement);

        return appendValue(supplier, equalityCondition, Boolean.class.getSimpleName());
    }

    private UpdateWhereClauseValue appendDate(String val, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareDate(val);

        return appendValue(supplier, equalityCondition, "String Date");
    }

    private UpdateWhereClauseValue appendTimestamp(Long val, String equalityCondition) {
        Supplier<String> supplier =
                () -> preparer.prepareTimestamp(val);

        return appendValue(supplier, equalityCondition, "String Timestamp");
    }

    private UpdateWhereClauseValue appendValue(Supplier<String> supplier, String equalityCondition, String valueType) {
        String val = supplier.get();
        validValue(val, valueType);

        updateQuery
                .append(equalityCondition)
                .append(val);

        return new UpdateWhereClauseValue(updateQuery);
    }

    private <T> void validValue(T val, String valueType) {
        if (Objects.isNull(val)) {
            throw new NullPointerException(valueType + " value cannot be null");
        }
    }
}
