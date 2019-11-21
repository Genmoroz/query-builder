package zodiac.mapper;

import zodiac.mapper.insert.InsertQuery;
import zodiac.mapper.insert.impl.InsertQueryImpl;
import zodiac.mapper.update.UpdateQuery;
import zodiac.mapper.update.impl.UpdateQueryImpl;

public class QueryBuilder {

    public UpdateQuery update(String tableName) {
        return new UpdateQueryImpl(tableName);
    }

    public InsertQuery insert(String tableName) {
        return new InsertQueryImpl(tableName);
    }
}
