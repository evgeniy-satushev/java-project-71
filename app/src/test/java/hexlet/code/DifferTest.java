package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    Path filepath1 = Paths.get("./src/test/resources/test-file1.json");
    Path filepath2 = Paths.get("./src/test/resources/test-file2.json");
    Path filepath3 = Paths.get("./src/test/resources/empty-file.json");
    @Test
    void generateTest() {
        Map<String, Object> firstFile;
        Map<String, Object> secondFile;
        Map<String, Object> emptyFile;
        String expected = "";
        String expected2 = "";
        try {
            firstFile = Differ.getData(filepath1);
            secondFile = Differ.getData(filepath2);
            emptyFile = Differ.getData(filepath3);
            expected = Differ.generate(firstFile, secondFile);
            expected2 = Differ.generate(emptyFile, emptyFile);
        } catch (IOException e) {
            System.out.println("INCORRECT PATH TO FILE");
        }
        String actual = """
                {
                  - Abbrev: ISO 8879:1986
                  + Abbrev: ISO 8856:1994
                    Acronym: SGML
                  - GlossDef: A meta-markup language, used to create markup languages such as DocBook.
                  + GlossDef: A meta-markup language, used to create markup languages such as DocsFiles.
                    GlossList: GlossEntry
                    GlossSee: markup
                    GlossSeeAlso: [GML, XML]
                    GlossTerm: Standard Generalized Markup Language
                  - ID: SGML
                  + ID: 40
                    SortAs: SGML
                  - glossary: example glossary
                  + glossary: example dictionary
                  - title: S
                  + title: D
                }""";
        assertEquals(actual, expected);
        // testing empty json file.
        String actual2 = "{\n}";
        assertEquals(actual2, expected2);
    }
}
