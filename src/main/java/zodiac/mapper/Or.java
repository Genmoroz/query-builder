package zodiac.mapper;

public interface Or<T extends WhereClauseProvider> {

    T or();
}
