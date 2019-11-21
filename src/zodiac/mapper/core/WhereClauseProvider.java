package zodiac.mapper.core;

public interface WhereClauseProvider<T extends WhereClauseValueProvider> {

    T whereClause(String columnName);
}
