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
            }
            else if (c == '\'' && !inDoubleQuotes) {
                inSingleQuotes = !inSingleQuotes;    // toggle single quotes
            }
            else if(c == '\\' && i+1 < n) {
                char next = original.charAt(i + 1);
                // check if inside double quotes only
                if(inDoubleQuotes && !inSingleQuotes) {
                    if(next == '"' || next == '\\') {
                        current.append(next);
                        i += 1;
                    }
                    else {
                        // backslash is literal itself, no special treatment
                        current.append(c);
                    }
                }
                // neither inside the double quote not inside the single quote
                // special treatment outside
                else if(!inSingleQuotes && !inDoubleQuotes) {
                    current.append(next);
                    i += 1;
                }
                // inside single quotes
                // no special treatment
                else {
                    current.append(c);
                }

                
            }
            else if (c == ' ' && !inDoubleQuotes && !inSingleQuotes) {
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
