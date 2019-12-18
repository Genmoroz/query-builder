package zodiac.builder.insert;

import zodiac.builder.ColumnProvider;
import zodiac.builder.Builder;

import java.util.Objects;

public class InsertColumnValue implements ColumnProvider<InsertColumn>, Builder {

    private final InsertQuery insertQuery;

    InsertColumnValue(InsertQuery insertQuery) {
        this.insertQuery = insertQuery;
    }

    @Override
    public String build() {
        return insertQuery.build();
    }

    @Override
    public InsertColumn column(String columnName) {
        if (Objects.isNull(columnName) || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("The column name cannot be null or empty");
        }
        insertQuery.addColumn(columnName);
        return insertQuery.getPool().getInsertColumn();
    }
}
