package zodiac.builder;

public interface Or<T extends WhereClauseProvider<?>> {

    T or();
}
