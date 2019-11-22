package zodiac.mapper.update;

import zodiac.mapper.ColumnProvider;
import zodiac.mapper.WhereClauseProvider;

import java.util.Objects;

public class UpdateColumnValue implements ColumnProvider<UpdateColumn>, WhereClauseProvider<UpdateWhereClause> {

    private static boolean isFirstWhereClause = true;

    private final UpdateQuery updateQuery;

    UpdateColumnValue(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    @Override
    public UpdateColumn column(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        updateQuery.query
                .append(", ")
                .append(columnName);

        return new UpdateColumn(updateQuery);
    }

    @Override
    public UpdateWhereClause whereClause(String columnName) {
        if (isFirstWhereClause) {
            updateQuery.query
                    .append(" WHERE ")
                    .append(columnName);
            isFirstWhereClause = false;
        } else {
            updateQuery.query
                    .append(" ")
                    .append(columnName);
        }
        return new UpdateWhereClause(updateQuery);
    }
}
