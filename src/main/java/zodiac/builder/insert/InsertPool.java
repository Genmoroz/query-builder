package zodiac.builder.insert;

public class InsertPool {

    private final InsertColumn insertColumn;
    private final InsertColumnValue insertColumnValue;

    public InsertPool(InsertQuery insertQuery) {
        insertColumn = new InsertColumn(insertQuery);
        insertColumnValue = new InsertColumnValue(insertQuery);
    }

    public InsertColumn getInsertColumn() {
        return insertColumn;
    }

    public InsertColumnValue getInsertColumnValue() {
        return insertColumnValue;
    }
}
