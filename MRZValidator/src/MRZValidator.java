// The main class
// This establishes the arguments from the commandline and runs the validation.

import com.beust.jcommander.JCommander;

public class MRZValidator {
    public static void main(String[] argv) throws Exception {
        Args args = new Args();
        JCommander.newBuilder()
        .addObject(args)
        .build()
        .parse(argv);
    }
}
