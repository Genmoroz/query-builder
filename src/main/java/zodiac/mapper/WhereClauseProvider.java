package zodiac.mapper;

public interface WhereClauseProvider<T extends WhereClauseValueProvider> {

    T whereClause(String columnName);
}
