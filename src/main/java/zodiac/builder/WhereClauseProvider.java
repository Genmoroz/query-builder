package zodiac.builder;

public interface WhereClauseProvider<T extends WhereClauseValueProvider> {

    T whereClause(String columnName);
}
