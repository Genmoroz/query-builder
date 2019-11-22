package zodiac;

import zodiac.mapper.QueryFactory;
import zodiac.mapper.preparer.danaos.DanaosDataPreparer;

import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) {

        String builder =
                new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("TABLE_NAME")
                        .column("SOME_TEXT").setString("Some text to 'FIRST_COLUMN'")
                        .column("TIMESTAMP").setTimestamp(1572966184855L)
                        .column("ACTIVE_DATE").setDate("2019-03-25")
                        .column("CODE").setLong(1241L)
                        .column("ACTIVE_FLAG").setBoolean(true, "Y", "N")
                        .column("IMO_NO").setDouble(9999.99999D, "#.##")
                        .whereClause("GUID").equalsString("910fd190-4b12-a4cb-80ce-2853d923d3e7")
                        .and()
                        .whereClause("CREATED").equalsDate("2011-11-25")
                        .and()
                        .whereClause("SOLD").equalsBoolean(true, "Y", "N")
                        .or()
                        .whereClause("TIMESTAMP").equalsTimestamp(12414141412414L)
                        .and()
                        .whereClause("TEMPERATURE").equalsDouble(1241.2414D, "#.####")
                        .and()
                        .whereClause("LENGTH").equalsLong(12414141412414L)
                        .and()
                        .whereClause("NULL_COLUMN").isNull()
                        .and()
                        .whereClause("NOT_NULL_COLUMN").nonNull()
                        .build();
        System.out.println(builder);
//
//        NumberFormat formatter = new DecimalFormat("#.0000");
//        formatter.setRoundingMode(RoundingMode.DOWN);
//        System.out.println(formatter.format(99999.00D));
    }
}
