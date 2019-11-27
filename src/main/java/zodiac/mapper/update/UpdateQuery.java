package zodiac.mapper.update;

import zodiac.mapper.ColumnProvider;
import zodiac.mapper.preparer.DataPreparer;

import java.util.Objects;

public class UpdateQuery implements ColumnProvider<UpdateColumn> {

    private final DataPreparer dataPreparer;
    private final StringBuilder query;

    boolean isFirstWhereClause;

    public UpdateQuery(String tableName, DataPreparer dataPreparer) {
        if (Objects.isNull(dataPreparer)) {
            throw new IllegalArgumentException("The data preparer cannot be null");
        }
        if (Objects.isNull(tableName) || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("The table name cannot be null or empty");
        }

        isFirstWhereClause = true;
        this.dataPreparer = dataPreparer;

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

        return new UpdateColumn(this);
    }

    UpdateQuery append(Object val) {
        query.append(val);
        return this;
    }

    String getQuery() {
        return query.toString();
    }

    DataPreparer getDataPreparer() {
        return dataPreparer;
    }
}
