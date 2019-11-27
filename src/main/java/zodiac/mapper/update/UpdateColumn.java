package zodiac.mapper.update;

import zodiac.mapper.ColumnValueProvider;
import zodiac.mapper.preparer.DataPreparer;

import java.util.function.Supplier;

public class UpdateColumn implements ColumnValueProvider<UpdateColumnValue> {

    private final UpdateQuery updateQuery;

    private DataPreparer preparer;

    UpdateColumn(UpdateQuery updateQuery) {
        this.updateQuery = updateQuery;
        preparer = updateQuery.getDataPreparer();
    }

    @Override
    public UpdateColumnValue setString(String val) {
        return setValue(
                () -> preparer.prepareString(val)
        );
    }

    @Override
    public UpdateColumnValue setInteger(Integer val) {
        return setValue(
                () -> preparer.prepareInteger(val)
        );
    }

    @Override
    public UpdateColumnValue setLong(Long val) {
        return setValue(
                () -> preparer.prepareLong(val)
        );
    }

    @Override
    public UpdateColumnValue setDouble(Double val, String formatPattern) {
        return setValue(
                () -> preparer.prepareDouble(val, formatPattern)
        );
    }

    @Override
    public UpdateColumnValue setBoolean(Boolean val, String trueReplacement, String falseReplacement) {
        return setValue(
                () -> preparer.prepareBoolean(val, trueReplacement, falseReplacement)
        );
    }

    @Override
    public UpdateColumnValue setDate(String val) {
        return setValue(
                () -> preparer.prepareDate(val)
        );
    }

    @Override
    public UpdateColumnValue setTimestamp(Long val) {
        return setValue(
                () -> preparer.prepareTimestamp(val)
        );
    }

    @Override
    public UpdateColumnValue setRawString(String val) {
        return setValue(
                () -> val
        );
    }

    private UpdateColumnValue setValue(Supplier<String> supplier) {
        updateQuery
                .append(" = ")
                .append(supplier.get());

        return new UpdateColumnValue(updateQuery);
    }
}
