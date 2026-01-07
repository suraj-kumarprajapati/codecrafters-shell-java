package helpers;

import java.util.List;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

public class BuiltinCompleter implements Completer{

    private static final List<String> BUILTINS = List.of("echo", "exit");

    @Override
    public void complete(LineReader reader, ParsedLine parsedLine, List<Candidate> candidates) {

        if(parsedLine.wordIndex() != 0) 
            return;

        String word = parsedLine.word();

        for(String cmd : BUILTINS) {
            if(cmd.startsWith(word)) {
                candidates.add(new Candidate(
                    cmd + " ", // value -> value inserted
                    cmd,  //  displ -> user sees as suggestion
                    null,  // group -> label (optional)
                    null,  // descr -> description (optional)
                    null, // suffix -> optional because already appended in value
                    null, // key -> internal identifier
                    false // complete -> whether this is full completion
                ));
            }
        }
    }

}
