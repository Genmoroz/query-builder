package zodiac.builder;

import zodiac.builder.insert.InsertQuery;
import zodiac.builder.preparer.DataPreparer;
import zodiac.builder.update.UpdateQuery;

import java.util.Objects;

public class QueryFactory {

    private DataPreparer dataPreparer;

    public QueryFactory(DataPreparer dataPreparer) {
        if (Objects.isNull(dataPreparer)) {
            throw new IllegalArgumentException("The dataPreparer cannot be null");
        }
        this.dataPreparer = dataPreparer;
    }

    public UpdateQuery createUpdateQuery(String tableName) {
        return new UpdateQuery(tableName, dataPreparer);
    }

    public InsertQuery createInsertQuery(String tableName) {
        return new InsertQuery(tableName, dataPreparer);
    }

    public DataPreparer getDataPreparer() {
        return dataPreparer;
    }

    public void setDataPreparer(DataPreparer dataPreparer) {
        this.dataPreparer = dataPreparer;
    }
}
