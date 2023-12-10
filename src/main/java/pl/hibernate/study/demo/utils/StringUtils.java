package pl.hibernate.study.demo.utils;

public class StringUtils {
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }
}
