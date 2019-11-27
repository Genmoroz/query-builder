package zodiac.builder;

public interface WhereClauseValueProvider<T extends LogicalOperatorsProvider> {

    T equalsString(String val);

    T isNull();

    T nonNull();

    T equalsInteger(Integer val);

    T equalsLong(Long val);

    T equalsDouble(Double val, String formatPattern);

    T equalsBoolean(Boolean condition, String trueReplacement, String falseReplacement);

    T equalsDate(String date);

    T equalsTimestamp(Long val);

    T notEqualsString(String val);

    T notEqualsInteger(Integer val);

    T notEqualsLong(Long val);

    T notEqualsDouble(Double val, String formatPattern);

    T notEqualsBoolean(Boolean condition, String trueReplacement, String falseReplacement);

    T notEqualsDate(String date);

    T notEqualsTimestamp(Long val);

    T moreThanInteger(Integer val);

    T lessThanInteger(Integer val);

    T moreThanLong(Long val);

    T lessThanLong(Long val);

    T moreThanDouble(Double val, String formatPattern);

    T lessThanDouble(Double val, String formatPattern);
}
