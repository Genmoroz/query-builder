package zodiac.builder.insert;

import zodiac.builder.ColumnValueProvider;
import zodiac.builder.preparer.DataPreparer;

public class InsertColumn implements ColumnValueProvider<InsertColumnValue> {

    private final InsertQuery insertQuery;
    private final DataPreparer preparer;

    InsertColumn(InsertQuery insertQuery) {
        this.insertQuery = insertQuery;
        preparer = insertQuery.getPreparer();
    }

    @Override
    public InsertColumnValue setString(String val) {
        insertQuery.addColumnValue(
                preparer.prepareString(val)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setInteger(Integer val) {
        insertQuery.addColumnValue(
                preparer.prepareInteger(val)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setLong(Long val) {
        insertQuery.addColumnValue(
                preparer.prepareLong(val)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setDouble(Double val, String formatPattern) {
        insertQuery.addColumnValue(
                preparer.prepareDouble(val, formatPattern)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setBoolean(Boolean condition, String trueReplacement, String falseReplacement) {
        insertQuery.addColumnValue(
                preparer.prepareBoolean(condition, trueReplacement, falseReplacement)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setDate(String val) {
        insertQuery.addColumnValue(
                preparer.prepareDate(val)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setTimestamp(Long val) {
        insertQuery.addColumnValue(
                preparer.prepareTimestamp(val)
        );
        return new InsertColumnValue(insertQuery);
    }

    @Override
    public InsertColumnValue setRawString(String val) {
        insertQuery.addColumnValue(val);
        return new InsertColumnValue(insertQuery);
    }
}
