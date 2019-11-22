package zodiac;

import zodiac.mapper.QueryFactory;
import zodiac.mapper.preparer.danaos.DanaosDataPreparer;

import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) {

        String builder =
                new QueryFactory(new DanaosDataPreparer())
                        .createUpdateQuery("VESSEL_DATA")
                        .column("DESCRIPTION").setString("SOME DESCRIPTION THIS TEXT WILL BE WITH 'QUOTE' THIS IS IN [BRACKETS]")
                        .column("VESSEL_CODE").setLong(1241L)
                        .column("SOLD_DATE").setDate("2019-03-25")
                        .column("TIMESTAMP").setTimestamp(1572966184855L)
                        .column("IMO_NO").setLong(326236L)
                        .column("ACTIVE_FLAG").setBoolean(true, "Y", "N")
                        .whereClause("GUID").equalsString("21414124-12412-412412412-41241-42")
                        .and()
                        .whereClause("DELIVERED").equalsDate("2011-11-25").build();
        System.out.println(builder);
//
//        NumberFormat formatter = new DecimalFormat("0.0");
//        System.out.println(formatter.format(132412.214124));
    }
}
