package zodiac.builder.preparer.utils;

import java.util.Objects;

public class StringFormatter {

    public static String formatQuotedString(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
        return str
                .replace("''", "'")
                .replace("'", "''");
    }

    public static String quote(String str) {
        return Objects.isNull(str) ? null : String.format("'%s'", str);
    }

    public static String convertBooleanToStringAndQuote(boolean statement, String trueReplacement, String falseReplacement) {
        return quote(statement ? trueReplacement : falseReplacement);
    }

    public static String bracket(String str) {
        return String.format("(%s)", str);
    }
}
