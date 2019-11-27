package zodiac.builder;

public interface And<T extends WhereClauseProvider> {

    T and();
}
