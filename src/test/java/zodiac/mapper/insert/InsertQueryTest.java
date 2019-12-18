package zodiac.mapper.insert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import zodiac.builder.QueryFactory;
import zodiac.builder.preparer.danaos.DanaosDataPreparer;

class InsertQueryTest {

    private static final Logger logger = LoggerFactory.getLogger(InsertQueryTest.class);

    @ParameterizedTest
    @CsvFileSource(resources = "/correct-insert-query.sql", delimiter = '\n')
    void correctQuery(String expectedQuery) {
        String actualQuery = createCorrectInsertQuery();
        assertQueries(expectedQuery, actualQuery);
    }

    @Test
    void performanceTest() {
        short iterationNumber = 30_000;
        long startTime = System.nanoTime();
        for (int step = 0; step < iterationNumber; step++) {
            createCorrectInsertQuery();
        }
        long finishTime = System.nanoTime();
        long executingTime = (finishTime - startTime) / 1_000_000;
        logger.info(
                () -> "30 000 iterations executed for " + executingTime + " ms"
        );
    }

    @Test
    void nullDataPreparer() {
        Executable exec =
                () -> new QueryFactory(null);
        Assertions.assertThrows(IllegalArgumentException.class, exec, "The data preparer cannot be null");
    }

    @Test
    void nullTable() {
        Executable exec = () -> new QueryFactory(new DanaosDataPreparer())
                .createInsertQuery(null);
        Assertions.assertThrows(IllegalArgumentException.class, exec, "The table name cannot be null or empty");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/null-value-to-set-insert-query.sql", delimiter = '\n')
    void nullValueToInsert(String expectedQuery) {
        String actualQuery =
                new QueryFactory(new DanaosDataPreparer())
                        .createInsertQuery("TABLE_NAME")
                        .column("STRING_COLUMN").setString(null)
                        .column("STRING_RAW_COLUMN").setRawString(null)
                        .column("INTEGER_COLUMN").setInteger(null)
                        .column("LONG_COLUMN").setLong(null)
                        .column("DOUBLE_COLUMN").setDouble(null, null)
                        .column("BOOLEAN_COLUMN").setBoolean(null, null, null)
                        .column("DATE_COLUMN").setDate(null)
                        .column("TIMESTAMP_COLUMN").setTimestamp(null)
                        .build();

        assertQueries(expectedQuery, actualQuery);
    }

    @Test
    void nullOrEmptyColumn() {
        Executable nullColumnExec =
                () -> new QueryFactory(new DanaosDataPreparer())
                        .createInsertQuery("TABLE_NAME").column(null);
        Executable emptyColumnExec =
                () -> new QueryFactory(new DanaosDataPreparer())
                        .createInsertQuery("TABLE_NAME").column("");

        Assertions.assertThrows(IllegalArgumentException.class, nullColumnExec, "The column name cannot be null or empty");
        Assertions.assertThrows(IllegalArgumentException.class, emptyColumnExec, "The column name cannot be null or empty");
    }

    private String createCorrectInsertQuery() {
        return new QueryFactory(new DanaosDataPreparer())
                .createInsertQuery("TABLE_NAME")
                .column("STRING_COLUMN").setString("SOME TEXT FOR INSERT")
                .column("STRING_RAW_COLUMN").setRawString("SEQ.NEXTVAL")
                .column("INTEGER_COLUMN").setInteger(213412)
                .column("LONG_COLUMN").setLong(124124142L)
                .column("DOUBLE_COLUMN").setDouble(12412414.352532, "#.##")
                .column("BOOLEAN_COLUMN").setBoolean(true, "Y", "N")
                .column("DATE_COLUMN").setDate("2019-03-25")
                .column("TIMESTAMP_COLUMN").setTimestamp(235235235L)
                .build();
    }

    private void assertQueries(String expected, String actual) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("\n")
                .append("Actual query:   ").append(actual).append("\n")
                .append("Expected query: ").append(expected).append("\n");

        logger.info(stringBuilder::toString);

        Assertions.assertEquals(expected, actual);
    }

}