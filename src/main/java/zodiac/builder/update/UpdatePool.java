package zodiac.builder.update;

import java.util.Objects;

public class UpdatePool {

    private final UpdateQuery updateQuery;

    private UpdateColumn updateColumn;
    private UpdateColumnValue updateColumnValue;
    private UpdateWhereClause updateWhereClause;
    private UpdateWhereClauseValue updateWhereClauseValue;

    public UpdatePool(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
    }

    public UpdateColumn getUpdateColumn() {
        return Objects.isNull(updateColumn) ?
                updateColumn = new UpdateColumn(updateQuery) : updateColumn;
    }

    public UpdateColumnValue getUpdateColumnValue() {
        return Objects.isNull(updateColumnValue) ?
                updateColumnValue = new UpdateColumnValue(updateQuery) : updateColumnValue;
    }

    public UpdateWhereClause getUpdateWhereClause() {
        return Objects.isNull(updateWhereClause) ?
                updateWhereClause = new UpdateWhereClause(updateQuery) : updateWhereClause;
    }

    public UpdateWhereClauseValue getUpdateWhereClauseValue() {
        return Objects.isNull(updateWhereClauseValue) ?
                updateWhereClauseValue = new UpdateWhereClauseValue(updateQuery) : updateWhereClauseValue;
    }
}
