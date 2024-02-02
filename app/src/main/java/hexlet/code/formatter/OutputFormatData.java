package hexlet.code.formatter;


public class OutputFormatData {
    public static String getDiffData(String format, String sign, String key, Object value1, Object value2) {
        if (sign.equals("updated")) {
            if (format.equals("plain")) {
                return PlainFormat.getPlainFormatData(sign, key, value1, value2);
            } else {
                return StylishFormat.getStylishFormatData("removed", key, value1)
                    + StylishFormat.getStylishFormatData("added", key, value2);
            }
        } else {
            if (format.equals("plain")) {
                return PlainFormat.getPlainFormatData(sign, key, value1, value2);
            } else {
                return StylishFormat.getStylishFormatData(sign, key, value1);
            }
        }
    }
    public static String getDiffData(String format, String sign, String key, Object value) {
        return getDiffData(format, sign, key, value, "");
    }
}
