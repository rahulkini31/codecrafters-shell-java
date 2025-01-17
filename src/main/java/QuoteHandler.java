// src/main/java/QuoteHandler.java
import java.util.ArrayList;
import java.util.List;

public class QuoteHandler {
    public static List<String> parseArguments(String input) {
        List<String> argsList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inSingleQuotes = false;
        boolean inDoubleQuotes = false;
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);

            if (c == '\'') {
                if (inDoubleQuotes) {
                    sb.append(c);
                } else {
                    inSingleQuotes = !inSingleQuotes;
                }
                i++;
                continue;
            }

            if (c == '\"') {
                if (inSingleQuotes) {
                    sb.append(c);
                } else {
                    inDoubleQuotes = !inDoubleQuotes;
                }
                i++;
                continue;
            }

            if (c == '\\' && !inDoubleQuotes) {
                if (i + 1 < input.length()) {
                    char nextChar = input.charAt(i + 1);
                    if (inSingleQuotes) {
                        sb.append(c);
                    }
                    sb.append(nextChar);
                    i += 2;
                    continue;
                }
            }

            if (Character.isWhitespace(c) && !inSingleQuotes && !inDoubleQuotes) {
                if (sb.length() > 0) {
                    argsList.add(sb.toString());
                    sb = new StringBuilder();
                }
                i++;
                continue;
            }

            sb.append(c);
            i++;
        }

        if (sb.length() > 0) {
            argsList.add(sb.toString());
        }

        return argsList;
    }
}