package zodiac.mapper.update.impl;

import zodiac.mapper.core.WhereClauseProvider;
import zodiac.mapper.update.UpdateWhereClauseValue;

class UpdateWhereClauseValueImpl implements UpdateWhereClauseValue {

    private final UpdateQueryImpl updateQuery;

    UpdateWhereClauseValueImpl(UpdateQueryImpl updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public WhereClauseProvider and() {
        updateQuery.query
                .append(" AND");

        return new UpdateColumnValueImpl(updateQuery);
    }

    @Override
    public WhereClauseProvider or() {
        updateQuery.query
                .append(" OR");

        return new UpdateColumnValueImpl(updateQuery);
    }

    @Override
    public String build() {
        return updateQuery.getQuery();
    }
}