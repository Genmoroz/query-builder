package zodiac.builder.insert;

import zodiac.builder.ColumnProvider;
import zodiac.builder.preparer.DataPreparer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsertQuery implements ColumnProvider<InsertColumn> {

    private final DataPreparer preparer;
    private final InsertPool pool;

    private List<String> columns;
    private List<String> columnValues;
    private StringBuilder query;

    public InsertQuery(String tableName, DataPreparer preparer) {
        if (Objects.isNull(preparer)) {
            throw new IllegalArgumentException("The data preparer cannot be null");
        }
        if (Objects.isNull(tableName) || tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("The table name cannot be null or empty");
        }

        this.preparer = preparer;

        pool = new InsertPool(this);
        columns = new ArrayList<>();
        columnValues = new ArrayList<>();
        query = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(" ");
    }

    @Override
    public InsertColumn column(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        addColumn(columnName);
        return getPool().getInsertColumn();
    }

    void addColumn(String columnName) {
        columns.add(columnName);
    }

    void addColumnValue(String columnValue) {
        columnValues.add(columnValue);
    }

    String build() {
        query.append("(");
        for (int step = 0; step < columns.size() - 1; step++) {
            query
                    .append(columns.get(step))
                    .append(", ");
        }
        query.append(
                columns.get(columns.size() - 1)
        );

        query
                .append(")")
                .append(" VALUES ")
                .append("(");

        for (int step = 0; step < columnValues.size() - 1; step++) {
            query
                    .append(columnValues.get(step))
                    .append(", ");
        }
        query.append(
                columnValues.get(columnValues.size() - 1)
        );

        query.append(")");

        return query.toString();
    }

    DataPreparer getPreparer() {
        return preparer;
    }

    public InsertPool getPool() {
        return pool;
    }
}
