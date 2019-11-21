package zodiac.mapper;

import java.io.Serializable;

public class Main implements Serializable {

    public static void main(String[] args) {

        String builder =
                new QueryBuilder()
                        .update("VESSEL_DATA")
                        .column("VESSEL_CODE").setLong(1241L)
                        .column("IMO_NO").setLong(326236L)
                        .column("ACTIVE_FLAG").setBoolean(true, "Y", "N")
                        .whereClause("GUID").equalsString("21414124-12412-412412412-41241-42")
                        .and()
                        .whereClause("DELIVERED").equalsDate("2011-11-25").build();
        System.out.println(builder);
    }
}
