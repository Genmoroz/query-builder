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
}
