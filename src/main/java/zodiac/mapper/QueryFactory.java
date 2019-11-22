package zodiac.mapper;

import zodiac.mapper.insert.InsertQuery;
import zodiac.mapper.insert.impl.InsertQueryImpl;
import zodiac.mapper.preparer.DataPreparer;
import zodiac.mapper.update.UpdateQuery;

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
        return new InsertQueryImpl(tableName);
    }

    public DataPreparer getDataPreparer() {
        return dataPreparer;
    }

    public void setDataPreparer(DataPreparer dataPreparer) {
        this.dataPreparer = dataPreparer;
    }
}
