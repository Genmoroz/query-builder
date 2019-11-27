package zodiac.mapper.insert;

import zodiac.mapper.Builder;
import zodiac.mapper.ColumnProvider;

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
        return new InsertColumn(insertQuery);
    }
}
