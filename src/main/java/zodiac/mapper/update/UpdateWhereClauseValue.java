package zodiac.mapper.update;

public class UpdateWhereClauseValue implements BuilderLogicalOperatorsProvider {

    private final UpdateQuery updateQuery;

    UpdateWhereClauseValue(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateColumnValue and() {
        updateQuery
                .append(" AND");

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public UpdateColumnValue or() {
        updateQuery
                .append(" OR");

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public String build() {
        return updateQuery.getQuery();
    }
}