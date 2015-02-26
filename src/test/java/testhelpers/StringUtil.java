package testhelpers;

public class StringUtil {
    public static String getOutputString(String... strings) {
        StringBuilder sb = new StringBuilder();
        for(String s: strings) {
            sb.append(s).append("\n");
        }
        return sb.toString();
    }
}
