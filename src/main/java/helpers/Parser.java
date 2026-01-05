package helpers;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private String original;

    public Parser(String original) {
        this.original = original;
    }


    public String[] parse() {

        int n = original.length();

        List<String> args = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        boolean inDoubleQuotes = false;
        boolean inSingleQuotes = false;

        for(int i=0; i<n; i++) {
            char c = original.charAt(i);
            
            if (c == '"' && !inSingleQuotes) {
                inDoubleQuotes = !inDoubleQuotes;    // toggle double quotes
                continue;
            }

            if (c == '\'' && !inDoubleQuotes) {
                inSingleQuotes = !inSingleQuotes;    // toggle single quotes
                continue;
            }

            if (c == ' ' && !inDoubleQuotes && !inSingleQuotes) {
                if (current.length() > 0) {
                    args.add(current.toString());
                    current.setLength(0); // reset
                }
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            args.add(current.toString());
        }

        return args.toArray(new String[0]);
    }
}
