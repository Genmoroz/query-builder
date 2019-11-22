package zodiac.mapper;

public interface ColumnValueProvider<T extends ColumnProvider> {

    T setString(String val);

    T setLong(Long val);

    T setDouble(Double val, String formatPattern);

    T setBoolean(Boolean condition, String trueReplacement, String falseReplacement);

    T setDate(String date);

    T setTimestamp(Long val);
}
