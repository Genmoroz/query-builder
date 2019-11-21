package zodiac.mapper.update.impl;

import zodiac.mapper.update.UpdateColumn;
import zodiac.mapper.update.UpdateColumnValue;
import zodiac.mapper.update.UpdateWhereClause;

import java.util.Objects;

class UpdateColumnValueImpl implements UpdateColumnValue {

    private final UpdateQueryImpl updateQuery;

    UpdateColumnValueImpl(UpdateQueryImpl updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateColumn column(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        updateQuery.query
                .append(", ")
                .append(columnName)
                .append(" ");

        return new UpdateColumnImpl(updateQuery);
    }

    @Override
    public UpdateWhereClause whereClause(String columnName) {
        updateQuery.query
                .append(" WHERE ")
                .append(columnName);

        return new UpdateWhereClauseImpl(updateQuery);
    }
}
