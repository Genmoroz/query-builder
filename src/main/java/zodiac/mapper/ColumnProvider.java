package zodiac.mapper;

public interface ColumnProvider<T extends ColumnValueProvider> {

    T column(String columnName);
}
