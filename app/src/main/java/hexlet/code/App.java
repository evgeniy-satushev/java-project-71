package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format [default: stylish]")
    static String format;

    @Override
    public Integer call() {
        String diffBetweenFiles;
        try {
            Path firstPath = Paths.get(filepath1).toAbsolutePath().normalize();
            Path seconfPath = Paths.get(filepath2).toAbsolutePath().normalize();
            diffBetweenFiles = Differ.generate(Parser.getData(firstPath), Parser.getData(seconfPath), format);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getLocation());
            return 1;
        } catch (IOException e) {
            System.out.println("THE FILE DOES NOT EXIST IN THE SPECIFIED DIRECTORY\n" + e);
            return 1;
        }
        System.out.println(diffBetweenFiles);
        return 0;
    }

    public static void main(String[] args) {
        System.exit(new CommandLine(new App()).execute(args));
    }
//    private static boolean checkFilesFormat(String filepath1, String filepath2) {
//        String formatFile1 = filepath1.substring(filepath1.length() - 4);
//        String formatFile2 = filepath2.substring(filepath1.length() - 4);
//        return formatFile1.equals(formatFile2);
//    }
}
