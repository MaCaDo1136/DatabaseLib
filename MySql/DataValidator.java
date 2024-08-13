/*
 * Written by Mario Casas
 */

 package MySql;

 public class DataValidator {
    
    public static boolean isNumber(String value) {
        if (value == null) {
            return false;
        }
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatValue (String value) {
        if (isNumber(value)) {
            return value;
        } else {
            String formatValue = "'" + value + "'";
            return formatValue;
        }
    }

    public static String[] arrayFormatValue (String[] values) {
        String[] updatedData = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                updatedData[i] = formatValue(values[i]);
            } else {
                continue;
            }
        }
        return updatedData;
    }
}