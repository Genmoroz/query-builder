package zodiac.mapper.update;

import zodiac.mapper.Builder;
import zodiac.mapper.ColumnProvider;
import zodiac.mapper.WhereClauseProvider;

import java.util.Objects;

public class UpdateColumnValue implements ColumnProvider<UpdateColumn>, WhereClauseProvider<UpdateWhereClause>, Builder {

    private final UpdateQuery updateQuery;

    UpdateColumnValue(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateColumn column(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        updateQuery
                .append(", ")
                .append(columnName);

        return new UpdateColumn(updateQuery);
    }

    @Override
    public UpdateWhereClause whereClause(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        if (updateQuery.isFirstWhereClause) {
            updateQuery
                    .append(" WHERE ")
                    .append(columnName);
            updateQuery.isFirstWhereClause = false;
        } else {
            updateQuery
                    .append(" ")
                    .append(columnName);
        }
        return new UpdateWhereClause(updateQuery);
    }

    @Override
    public String build() {
        return updateQuery.getQuery();
    }
}
