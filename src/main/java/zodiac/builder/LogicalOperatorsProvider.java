package zodiac.builder;

public interface LogicalOperatorsProvider<T extends WhereClauseProvider<?>> extends Or<T>, And<T> {
}
