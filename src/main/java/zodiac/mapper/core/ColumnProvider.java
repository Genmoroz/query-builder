package zodiac.mapper.core;

public interface ColumnProvider<T extends ColumnValueProvider> {

    T column(String columnName);
}
