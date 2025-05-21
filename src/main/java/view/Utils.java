package view;

public class Utils {

    public static String padStart(String original, int length, char padChar) {
        if (original == null) original = "";
        if (original.length() >= length) return original;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - original.length()) {
            sb.append(padChar);
        }
        sb.append(original);
        return sb.toString();
    }

    public static String padEnd(String original, int length, char padChar) {
        if (original == null) original = "";
        if (original.length() >= length) return original;

        StringBuilder sb = new StringBuilder(original);
        while (sb.length() < length) {
            sb.append(padChar);
        }
        return sb.toString();
    }

    public static String truncateAndPad(String text, int width) {
        if (text == null) text = "";
        if (text.length() > width) {
            return text.substring(0, width - 3) + "...";
        }
        return padEnd(text, width, ' ');
    }
}
