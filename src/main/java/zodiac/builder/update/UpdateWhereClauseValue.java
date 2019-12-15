package zodiac.builder.update;

public class UpdateWhereClauseValue implements BuilderLogicalOperatorsProvider {

    private final UpdateQuery updateQuery;

    UpdateWhereClauseValue(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateColumnValue and() {
        updateQuery
                .append(" AND");

        return updateQuery
                .getPool()
                .getUpdateColumnValue();
    }

    @Override
    public UpdateColumnValue or() {
        updateQuery
                .append(" OR");

        return updateQuery
                .getPool()
                .getUpdateColumnValue();
    }

    @Override
    public String build() {
        return updateQuery.getQuery();
    }
}