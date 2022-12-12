package home3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import home3.task1.service.StatisticService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Test;

public class StatisticsTest {
  public static final StatisticService statisticService = new StatisticService();

  @Test
  public void correctResultForOneThread() throws IOException {
    int numberOfThreads = 1;
    String expected = "src/test/resources/home3/statistics/expected.xml";
    String pathTo = "src/test/resources/home3/statistics/violations";
    String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
    statisticService.createStatistics(numberOfThreads, 1, pathTo, pathFrom);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));
  }

  @Test
  public void correctResultForTwoThreads() throws IOException {
    int numberOfThreads = 2;
    String expected = "src/test/resources/home3/statistics/expected.xml";
    String pathTo = "src/test/resources/home3/statistics/violations";
    String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
    statisticService.createStatistics(numberOfThreads, 1, pathTo, pathFrom);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));
  }

  @Test
  public void correctResultForFourThreads() throws IOException {
    int numberOfThreads = 4;
    String expected = "src/test/resources/home3/statistics/expected.xml";
    String pathTo = "src/test/resources/home3/statistics/violations";
    String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
    statisticService.createStatistics(numberOfThreads, 1, pathTo, pathFrom);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));
  }

  @Test
  public void correctResultForEightThreads() throws IOException {
    int numberOfThreads = 8;
    String expected = "src/test/resources/home3/statistics/expected.xml";
    String pathTo = "src/test/resources/home3/statistics/violations";
    String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
    statisticService.createStatistics(numberOfThreads, 1, pathTo, pathFrom);
    assertEquals(-1L, Files.mismatch(Path.of(expected), Path.of(pathFrom)));
  }

  @Test
  public void throwExceptionWhenNumberOfThreadsLesser1() throws IOException {
    int numberOfThreads = -2;
    String pathTo = "src/test/resources/home3/statistics/violations";
    String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
    assertThrows(IllegalArgumentException.class,
        () -> statisticService.createStatistics(numberOfThreads, 1, pathTo, pathFrom));
  }

  @Test
  public void throwExceptionWhenNumberOfIterationsLesser1() throws IOException {
    int numberOfThreads = 2;
    String pathTo = "src/test/resources/home3/statistics/violations";
    String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
    assertThrows(IllegalArgumentException.class,
        () -> statisticService.createStatistics(numberOfThreads, -2, pathTo, pathFrom));
  }

}
