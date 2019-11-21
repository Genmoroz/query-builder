package zodiac.mapper.update.impl;

import zodiac.mapper.update.UpdateColumn;
import zodiac.mapper.update.UpdateQuery;

import java.util.Objects;

public class UpdateQueryImpl implements UpdateQuery {

    StringBuilder query;

    public UpdateQueryImpl(String tableName) {
        if (Objects.isNull(tableName) || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("The table name cannot be null or empty");
        }

        query = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET ");
    }

    @Override
    public UpdateColumn column(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        query.append(columnName);

        return new UpdateColumnImpl(this);
    }

    String getQuery() {
        return query.toString();
    }
}
