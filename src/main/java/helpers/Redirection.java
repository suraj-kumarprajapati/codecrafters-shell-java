package helpers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Redirection {

    private String[] args;
    private Path currDir;

    public Path stdoutFile;
    public Path stderrFile;
    public boolean stdoutAppend = false;
    public boolean stderrAppend = false;

    public Redirection(String[] args, Path currDir) {
        this.args = args;
        this.currDir = currDir;
    }

    public String[] redirectArguments() throws FileNotFoundException {
        int n = args.length;
        List<String> cleanedArgs = new ArrayList<>();

        for(int i=0; i<n; ) {
            String token = args[i];

            if(isRedirectOperatorFileOverwrite(token)) {
                String file = args[i+1];
                stdoutFile = currDir.resolve(file);
                stdoutAppend = false;
                
                FileOutputStream fileOutputStream = new FileOutputStream(
                    stdoutFile.toFile(), 
                    stdoutAppend
                );
                PrintStream printStream = new PrintStream(fileOutputStream);
                System.setOut(printStream);
                i += 2;
            }
            else if(isRedirectOperatorFileAppend(token)) {
                String file = args[i+1];
                stdoutFile = currDir.resolve(file);
                stdoutAppend = true;
                
                FileOutputStream fileOutputStream = new FileOutputStream(
                    stdoutFile.toFile(), 
                    stdoutAppend
                );
                PrintStream printStream = new PrintStream(fileOutputStream);
                System.setOut(printStream);
                i += 2;
            }
            else if(isRedirectOperatorErrorFileOverwrite(token)) {
                String file = args[i+1];
                stderrFile = currDir.resolve(file);
                stderrAppend = false;
                
                FileOutputStream fileOutputStream = new FileOutputStream(
                    stderrFile.toFile(), 
                    stderrAppend
                );
                PrintStream errorStream = new PrintStream(fileOutputStream);
                System.setErr(errorStream);
                i += 2;
            }
            else if(isRedirectOperatorErrorFileAppend(token)) {
                String file = args[i+1];
                stderrFile = currDir.resolve(file);
                stderrAppend = true;
                
                FileOutputStream fileOutputStream = new FileOutputStream(
                    stderrFile.toFile(), 
                    stderrAppend
                );
                PrintStream errorStream = new PrintStream(fileOutputStream);
                System.setErr(errorStream);
                i += 2;
            }
            else {
                // normal arg
                cleanedArgs.add(token);
                i += 1;
            }
        }

        return cleanedArgs.toArray(new String[0]);
    }

    private boolean isRedirectOperatorFileOverwrite(String arg) {
        if (arg.equals(">") || arg.equals("1>")) {
            return true;
        }

        return false;
    }

    private boolean isRedirectOperatorFileAppend(String arg) {
        if(arg.equals(">>") || arg.equals("1>>"))
            return true;
        return false;
    }

    private boolean isRedirectOperatorErrorFileOverwrite(String arg) {
        if (arg.equals("2>"))
            return true;

        return false;
    }

    private boolean isRedirectOperatorErrorFileAppend(String arg) {
        if (arg.equals("2>>"))
            return true;

        return false;
    }



}
