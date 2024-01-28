package hexlet.code;

import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format;

    @Override
    public Integer call() {
        try {
            Path firstPath = Paths.get(filepath1).toAbsolutePath().normalize();
            Path seconfPath = Paths.get(filepath2).toAbsolutePath().normalize();
            String diffBetweenJson = Differ.generate(Differ.getData(firstPath), Differ.getData(seconfPath));
            System.out.println(diffBetweenJson);
        } catch (IOException e) {
            System.out.println("THE FILE DOES NOT EXIST IN THE SPECIFIED DIRECTORY \n" + e);
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) {
//        String file1 = "./src/main/resources/file1.json";
//        String file2 = "./src/main/resources/file2.json";
//        System.exit(new CommandLine(new App()).execute(file1, file2));
        System.exit(new CommandLine(new App()).execute(args));
    }
}
