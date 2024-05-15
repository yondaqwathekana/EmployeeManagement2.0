package ac.za.cput.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static boolean isNullOrEmpty(Object obj) {
        // Checking if the object is null
        if (obj == null)
            return true;
        // If the object is a String, checking if it's empty
        if (obj instanceof String) { return ((String) obj).isEmpty(); }
        // Validating for Number objects (including Long, Integer, Double)
        if (obj instanceof Number) { return ((Number) obj).doubleValue() == 0; }

        return false;
    }

    public static boolean isValidEmail(String email) {
        // validate email using regex
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; // Regular Expression
        final Pattern pattern = Pattern.compile(emailPattern);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}