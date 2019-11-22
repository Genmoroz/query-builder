package zodiac.mapper.update;

import zodiac.mapper.ColumnValueProvider;
import zodiac.mapper.preparer.DataPreparer;

public class UpdateColumn implements ColumnValueProvider<UpdateColumnValue> {

    private final UpdateQuery updateQuery;

    private DataPreparer preparer;

    UpdateColumn(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
        preparer = updateQuery.getDataPreparer();
    }

    @Override
    public UpdateColumnValue setString(String val) {
        updateQuery.query
                .append(" = ")
                .append(preparer.prepareString(val));

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public UpdateColumnValue setLong(Long val) {
        updateQuery.query
                .append(" = ")
                .append(preparer.prepareLong(val));

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public UpdateColumnValue setDouble(Double val, String formatPattern) {
        updateQuery.query
                .append(" = ")
                .append(preparer.prepareDouble(val, formatPattern));

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public UpdateColumnValue setBoolean(Boolean condition, String trueReplacement, String falseReplacement) {
        updateQuery.query
                .append(" = ")
                .append(preparer.prepareBoolean(condition, trueReplacement, falseReplacement));

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public UpdateColumnValue setDate(String stringDate) {
        updateQuery.query
                .append(" = ")
                .append(preparer.prepareDate(stringDate));

        return new UpdateColumnValue(updateQuery);
    }

    @Override
    public UpdateColumnValue setTimestamp(Long val) {
        updateQuery.query
                .append(" = ")
                .append(preparer.prepareTimestamp(val));

        return new UpdateColumnValue(updateQuery);
    }
}
