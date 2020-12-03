package TaskTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Processes {
    /**
     * @return a list of strings which are the names of each running process with no duplicates.
     */
    public static List<String> getRunningProcesses() {
        ArrayList<String> processes = new ArrayList<>();
        Stream<String> stream = ProcessHandle.allProcesses()
                .filter(handle -> handle.info().command().isPresent())
                .map(handle -> handle.info().command().get());  // maps the present ProcessHandle's to their command string
        stream.forEach(s -> {
            String[] pieces = s.split("\\\\");  // split on \
            if(!processes.contains(pieces[pieces.length-1]))
                processes.add(pieces[pieces.length-1]);   // add the last element into processes
        });
        return processes;
    }

    /**
     * Checks against all running process if any match the regex
     * @param regex - name to check as regex
     * @return if a process matching the regex was found
     */
    public static boolean checkRunning(Pattern regex) {
        List<String> currentRunning = getRunningProcesses();
        return currentRunning.stream().anyMatch(regex.asPredicate());
    }
}
