package zodiac.builder.update;

class UpdatePool {

    private final UpdateColumn updateColumn;
    private final UpdateColumnValue updateColumnValue;
    private final UpdateWhereClause updateWhereClause;
    private final UpdateWhereClauseValue updateWhereClauseValue;

    UpdatePool(UpdateQuery updateQuery) {
        updateColumn = new UpdateColumn(updateQuery);
        updateColumnValue = new UpdateColumnValue(updateQuery);
        updateWhereClause = new UpdateWhereClause(updateQuery);
        updateWhereClauseValue = new UpdateWhereClauseValue(updateQuery);
    }

    UpdateColumn getUpdateColumn() {
        return updateColumn;
    }

    UpdateColumnValue getUpdateColumnValue() {
        return updateColumnValue;
    }

    UpdateWhereClause getUpdateWhereClause() {
        return updateWhereClause;
    }

    UpdateWhereClauseValue getUpdateWhereClauseValue() {
        return updateWhereClauseValue;
    }
}
