package hexlet.code;

import picocli.CommandLine;

import java.io.IOException;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;
import static picocli.CommandLine.Option;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "1.0",
        description = "Compares two configuration files and shows a difference")
public class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    String filepath1;
    @Parameters(index = "1", description = "path to second file")
    String filepath2;
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    String format;

    @Override
    public Integer call() {
        String diffBetweenFiles;
        try {
            diffBetweenFiles = Differ.generate(filepath1, filepath2, format);
        } catch (IOException e) {
            System.out.println("THE FILE DOES NOT EXIST IN THE SPECIFIED DIRECTORY\n" + e);
            return 1;
        }
        System.out.print(diffBetweenFiles);
        return 0;
    }

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
}
