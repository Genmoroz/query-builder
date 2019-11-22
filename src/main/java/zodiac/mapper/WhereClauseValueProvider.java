package zodiac.mapper;

public interface WhereClauseValueProvider<T extends BuilderLogicalOperatorsProvider> {

    T equalsString(String val);

    T isNull();

    T nonNull();

    T equalsLong(Long val);

    T equalsDouble(Double val, String formatPattern);

    T equalsBoolean(Boolean condition, String trueReplacement, String falseReplacement);

    T equalsDate(String date);

    T equalsTimestamp(Long val);

    T notEqualsString(String val);

    T notEqualsLong(Long val);

    T notEqualsDouble(Double val, String formatPattern);

    T notEqualsBoolean(Boolean condition, String trueReplacement, String falseReplacement);

    T notEqualsDate(String date);

    T notEqualsTimestamp(Long val);
}
