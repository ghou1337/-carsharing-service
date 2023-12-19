package pl.hibernate.study.demo.utils;

public class StringUtils {
    public static String capitalizeFirstLetter(String input) { // method for capitalizing first letter of string
        if (input == null || input.isEmpty()) {
            return input;
        }

        return Character.toUpperCase(input.charAt(0)) + input.substring(1).toLowerCase();
    }
}
