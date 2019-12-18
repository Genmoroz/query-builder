package zodiac.builder.insert;

class InsertPool {

    private final InsertColumn insertColumn;
    private final InsertColumnValue insertColumnValue;

    InsertPool(InsertQuery insertQuery) {
        insertColumn = new InsertColumn(insertQuery);
        insertColumnValue = new InsertColumnValue(insertQuery);
    }

    InsertColumn getInsertColumn() {
        return insertColumn;
    }

    InsertColumnValue getInsertColumnValue() {
        return insertColumnValue;
    }
}
