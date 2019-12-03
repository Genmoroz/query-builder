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

    /**
     *  Example to use:
     *  <code>
     *      String updateQuery = new QueryFactory(new DanaosDataPreparer())
     *                              .createUpdateQuery("TABLE_NAME")
     *                              .column("TEXT_COLUMN").setString("Some text")
     *                              .column("INTEGER_COLUMN").setInteger(1996)
     *                              .whereClause("ID_COLUMN").equalsLong(1241241L)
     *                              .build();
     *  </code>
     *  This could be used without:
     *  <code>
     *      .whereClause("id_column").equalsLong(1241241L)
     *  </code>
     *  Just drop it,
     *  in this case the sql query will be prepared to update all records in the table
     *  <code>
     *      String updateQuery = new QueryFactory(new DanaosDataPrepare())
     *                              .createUpdateQuery("TABLE_NAME")
     *                              .column("TEXT_COLUMN").setString("Some text")
     *                              .column("INTEGER_COLUMN").setInteger(1996)
     *                              .build();
     *  </code>
     */
    public UpdateQuery createUpdateQuery(String tableName) {
        return new UpdateQuery(tableName, dataPreparer);
    }

    /**
     *  Example to use:
     *  <code>
     *      String insertQuery = new QueryFactory(new DanaosDataPreparer())
     *                              .createInsertQuery("TABLE_NAME")
     *                              .column("TEXT_COLUMN").setString("Some text")
     *                              .column("INTEGER_COLUMN").setInteger(1996)
     *                              build();
     *  </code>
     */
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
