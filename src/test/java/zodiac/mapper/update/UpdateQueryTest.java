package zodiac.mapper.update;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import zodiac.builder.QueryFactory;
import zodiac.builder.preparer.danaos.DanaosDataPreparer;
import zodiac.builder.update.UpdateWhereClause;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class UpdateQueryTest {

    private static final Logger logger = LoggerFactory.getLogger(UpdateQueryTest.class);

    @ParameterizedTest
    @CsvFileSource(resources = "/correct-update-query.sql", delimiter = '\n')
    void correctQuery(String expectedQuery) {
        String actualQuery =
                    new QueryFactory(new DanaosDataPreparer())
                            .createUpdateQuery("TABLE_NAME")
                            .column("SOME_TEXT").setString("Some text to 'FIRST_COLUMN'")
                            .column("INTEGER_COLUMN").setInteger(213412)
                            .column("TIMESTAMP").setTimestamp(1572966184855L)
                            .column("ACTIVE_DATE").setDate("2019-03-25")
                            .column("CODE").setLong(1241L)
                            .column("ACTIVE_FLAG").setBoolean(true, "Y", "N")
                            .column("IMO_NO").setDouble(9999.99999D, "#.##")
                            .column("RAW_COLUMN").setRawString("SEQUENCE.NEXTVAL")
                            .whereClause("GUID").equalsString("910fd190-4b12-a4cb-80ce-2853d923d3e7")
                            .and()
                            .whereClause("CREATED").equalsDate("2011-11-25")
                            .and()
                            .whereClause("COUNT_COLUMN").equalsInteger(1241412)
                            .and()
                            .whereClause("SOLD").equalsBoolean(true, "Y", "N")
                            .or()
                            .whereClause("TIMESTAMP").equalsTimestamp(12414141412414L)
                            .and()
                            .whereClause("TEMPERATURE").equalsDouble(1241.2414D, "#.##")
                            .and()
                            .whereClause("LENGTH").equalsLong(12414141412414L)
                            .and()
                            .whereClause("NULL_COLUMN").isNull()
                            .and()
                            .whereClause("NOT_NULL_COLUMN").nonNull()
                            .and()
                            .whereClause("COUNTRY").notEqualsString("Russian")
                            .and()
                            .whereClause("NOT_EQUALS_INTEGER_COLUMN").notEqualsInteger(12421412)
                            .or()
                            .whereClause("SOLD").notEqualsBoolean(true, "Y", "N")
                            .and()
                            .whereClause("LAST_UPDATED_AT").notEqualsLong(12412412421L)
                            .or()
                            .whereClause("HEIGHT").notEqualsDouble(21414.25323D, "#.##")
                            .or()
                            .whereClause("DATE_COLUMN").notEqualsDate("2011-11-25")
                            .and()
                            .whereClause("TIMESTAMP_COLUMN").notEqualsTimestamp(124124124L)
                            .and()
                            .whereClause("MORE_THAN_INTEGER_COLUMN").moreThanInteger(124141241)
                            .and()
                            .whereClause("MORE_THAN_LONG_COLUMN").moreThanLong(241241241L)
                            .or()
                            .whereClause("MORE_THAN_DOUBLE").moreThanDouble(2141241.124124D, "#.#######")
                            .and()
                            .whereClause("LESS_THAN_INTEGER_COLUMN").lessThanInteger(124141241)
                            .and()
                            .whereClause("LESS_THAN_LONG_COLUMN").lessThanLong(241241241L)
                            .or()
                            .whereClause("LESS_THAN_DOUBLE").lessThanDouble(2141241.124124D, "#.#######")
                            .build();

        assertQueries(expectedQuery, actualQuery);
    }

    @Test
    void nullTable() {
        Executable exec = () -> new QueryFactory(new DanaosDataPreparer())
                .createUpdateQuery(null);
        Assertions.assertThrows(IllegalArgumentException.class, exec, "The table name cannot be null or empty");
    }

    @Test
    void nullDataPreparer() {
        Executable exec =
                () -> new QueryFactory(null);
        Assertions.assertThrows(IllegalArgumentException.class, exec, "The data preparer cannot be null");
    }

    @Test
    void nullOrEmptyColumn() {
        Executable nullColumnExec =
                () -> new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("TABLE_NAME").column(null);
        Executable emptyColumnExec =
                () -> new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("TABLE_NAME").column("");

        Assertions.assertThrows(IllegalArgumentException.class, nullColumnExec, "The column name cannot be null or empty");
        Assertions.assertThrows(IllegalArgumentException.class, emptyColumnExec, "The column name cannot be null or empty");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/null-value-to-set-update-query.sql", delimiter = '\n')
    void nullValueToSet(String expectedQuery) {
        String actualQuery =
                new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("TABLE_NAME")
                        .column("SOME_TEXT").setString(null)
                        .column("INTEGER_COLUMN").setInteger(null)
                        .column("TIMESTAMP").setTimestamp(null)
                        .column("ACTIVE_DATE").setDate(null)
                        .column("CODE").setLong(null)
                        .column("ACTIVE_FLAG").setBoolean(null, "Y", "N")
                        .column("IMO_NO").setDouble(null, null)
                        .whereClause("COLUMN_NAME").equalsString("SOME TEXT")
                        .build();

        assertQueries(expectedQuery, actualQuery);
    }

    @Test
    void nullOrEmptyWhereClause() {
        Executable nullWhereClauseExec =
                () -> new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("TABLE_NAME").column("COLUMN_NAME")
                        .setInteger(1)
                        .whereClause(null);
        Executable emptyWhereClauseExec =
                () -> new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("TABLE_NAME").column("COLUMN_NAME")
                        .setInteger(1)
                        .whereClause("");

        Assertions.assertThrows(IllegalArgumentException.class, nullWhereClauseExec, "The column name cannot be null or empty");
        Assertions.assertThrows(IllegalArgumentException.class, emptyWhereClauseExec, "The column name cannot be null or empty");
    }

    @Test
    void nullValueToSearch() {
        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsString(null)),
                String.class.getSimpleName() + " value cannot be null"
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsString(null)),
                String.class.getSimpleName() + " value cannot be null"
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsInteger(null)),
                Integer.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsInteger(null)),
                Integer.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsBoolean(null, null, null)),
                Boolean.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsBoolean(null, null, null)),
                Boolean.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsTimestamp(null)),
                "String Timestamp"
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsTimestamp(null)),
                "String Timestamp"
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsDouble(null, null)),
                Double.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsDouble(null, null)),
                Double.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsLong(null)),
                Long.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsLong(null)),
                Long.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.equalsDate(null)),
                "String Date"
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.notEqualsDate(null)),
                "String Date"
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.moreThanInteger(null)),
                Integer.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.lessThanInteger(null)),
                Integer.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.moreThanLong(null)),
                Long.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.lessThanLong(null)),
                Long.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.moreThanDouble(null, null)),
                Double.class.getSimpleName()
        );

        Assertions.assertThrows(
                NullPointerException.class,
                createExecutableWithNullValueToSearch((updateWhereClause) -> updateWhereClause.lessThanDouble(null, null)),
                Double.class.getSimpleName()
        );
    }

    @Test
    void formatDouble() {
        String query = new QueryFactory(new DanaosDataPreparer())
                .createUpdateQuery("TABLE_NAME")
                .column("COLUMN_NAME").setDouble(1241D, null).build();
        Assertions.assertEquals("1241.0", getValueFromColumn(query, "COLUMN_NAME"));

        query = new QueryFactory(new DanaosDataPreparer())
                .createUpdateQuery("TABLE_NAME")
                .column("ANOTHER_COLUMN_NAME").setDouble(1241.352D, "#.##").build();
        Assertions.assertEquals("1241.35", getValueFromColumn(query, "ANOTHER_COLUMN_NAME"));

        query = new QueryFactory(new DanaosDataPreparer())
                .createUpdateQuery("TABLE_NAME")
                .column("SOME_COLUMN_NAME").setDouble(1241.352D, "#.###").build();
        Assertions.assertEquals("1241.352", getValueFromColumn(query, "SOME_COLUMN_NAME"));
    }

    private String getValueFromColumn(String query, String column) {
        Matcher matcher = Pattern
                .compile(column + " = (.+[0123456789.])")
                .matcher(query);
        return matcher.find() ? matcher.group(1) : null;
    }

    private Executable createExecutableWithNullValueToSearch(Consumer<UpdateWhereClause> consumer) {
        return () -> {
            UpdateWhereClause whereClause = new QueryFactory(new DanaosDataPreparer())
                    .createUpdateQuery("TABLE_NAME")
                    .column("COLUMN_NAME").setInteger(1)
                    .whereClause("SOME_COLUMN");
            consumer.accept(whereClause);
        };
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