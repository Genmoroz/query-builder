package zodiac.builder;

public interface ColumnProvider<T extends ColumnValueProvider> {

    T column(String columnName);
}
