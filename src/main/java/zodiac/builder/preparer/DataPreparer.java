package zodiac.builder.preparer;

public interface DataPreparer {

    String prepareString(String val);

    String prepareDouble(Double val, String formatPattern);

    String prepareInteger(Integer val);

    String prepareLong(Long val);

    String prepareBoolean(Boolean condition, String trueReplacement, String falseReplacement);

    String prepareDate(String stringDate);

    String prepareTimestamp(Long val);
}
