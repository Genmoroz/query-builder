package zodiac.mapper.update;

import zodiac.mapper.Builder;
import zodiac.mapper.BuilderLogicalOperatorsProvider;
import zodiac.mapper.WhereClauseProvider;

public class UpdateWhereClauseValue implements BuilderLogicalOperatorsProvider, Builder {

    private final UpdateQuery updateQuery;

    UpdateWhereClauseValue(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public WhereClauseProvider and() {
        updateQuery.query
                .append(" AND");

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public WhereClauseProvider or() {
        updateQuery.query
                .append(" OR");

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public String build() {
        return updateQuery.getQuery();
    }
}