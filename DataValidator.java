/*
 * Written by Mario Casas
 */

package myapp1.databaseLib;

public class DataValidator {
    
    public static boolean isNumber(String value) {
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
            updatedData[i] = formatValue(values[i]);
        }
        return updatedData;
    }
}
