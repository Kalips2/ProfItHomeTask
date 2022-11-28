package home2;

import home2.services.StatisticService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class JsonToXmlStatisticsTest {
  public static final StatisticService statisticService = new StatisticService();

  @Test
  public void correctGenerationFileAndRightStatistics() throws IOException {
    String expected = "src/test/resources/home2/statistics1/expected.xml";
    String pathTo = "home2/statistics1/violations";
    String pathFrom = "src/test/resources/home2/statistics1/statistics.xml";
    statisticService.createStatics(pathTo, pathFrom);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));

  }
}
