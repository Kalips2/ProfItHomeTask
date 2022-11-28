package home2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;

public class ReplacerXmlTest {
  public static final  ReplacerXml replacerXml = new ReplacerXml();

  @Test
  public void correctGenerationFileWithRightData1() throws IOException {
    String expected = "src/test/resources/home2/replacer1/expected.xml";
    String pathTo = "src/test/resources/home2/replacer1/persons.xml";
    String pathFrom = "src/test/resources/home2/replacer1/personsEdited.xml";
    replacerXml.editXmlFile(pathFrom, pathTo);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));

  }

  @Test
  public void correctGenerationFileWithRightData2() throws IOException {
    String expected = "src/test/resources/home2/replacer2/expected.xml";
    String pathTo = "src/test/resources/home2/replacer2/persons.xml";
    String pathFrom = "src/test/resources/home2/replacer2/personsEdited.xml";
    replacerXml.editXmlFile(pathFrom, pathTo);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));
  }

}
