package zodiac.mapper.insert.impl;

import zodiac.mapper.insert.InsertColumn;
import zodiac.mapper.insert.InsertColumnValue;

public class InsertColumnImpl implements InsertColumn {

    @Override
    public InsertColumnValue setString(String val) {
        return null;
    }

    @Override
    public InsertColumnValue setLong(Long val) {
        return null;
    }

    @Override
    public InsertColumnValue setDouble(Double val, String formatPattern) {
        return null;
    }

    @Override
    public InsertColumnValue setBoolean(Boolean condition, String trueReplacement, String falseReplacement) {
        return null;
    }

    @Override
    public InsertColumnValue setDate(String date) {
        return null;
    }

    @Override
    public InsertColumnValue setTimestamp(Long val) {
        return null;
    }
}
