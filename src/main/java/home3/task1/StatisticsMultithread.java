package home3.task1;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import home2.model.ViolationDTO;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class in charge of process files with violations and create a new xml file with statistics.
 * The main a
 */
public class StatisticsMultithread {

  private ExecutorService executorService;
  ConcurrentMap<String, BigDecimal> violations;
  List<ViolationDTO> result;

  //Needed to create one executor for one number of thread. It's simplifies things when we want to
  //create statistics with particular number of thread more than once. We don't need to do a new
  //time-consuming creation of Executor service.

  public StatisticsMultithread(int numberOfThread) {
    if (numberOfThread < 1) {
      throw new IllegalArgumentException("Incorrect input");
    }
    executorService = Executors.newFixedThreadPool(numberOfThread);
  }

  /**
   * Create a new file statistics.xml that store the total amount of fines for each type of
   * violation for all years, sorted by amount (first by the highest amount of fine).
   *
   * @param path_to_dir       - path to folder with json files that contain information from the database
   * @param path_to_xml       - path to new Xml file we want to create
   * @param numberOfIteration - number of repeating the core method startStatistics.
   *                          It's needed for optimize, "heat up" jit
   */
  public long createStatistics(int numberOfIteration,
                               String path_to_dir, String path_to_xml) {
    if (numberOfIteration < 1) {
      throw new IllegalArgumentException("Incorrect input");
    }

    long sum = 0;
    for (int i = 0; i < numberOfIteration; i++) {
      sum += startStatistics(path_to_dir, path_to_xml);
    }

    executorService.shutdown();
    return sum / numberOfIteration;
  }

  /**
   * The basic method which contain all logic. It processes files ana write statistics to new xml file.
   *
   * @return time in ms taken to perform the method
   */
  private long startStatistics(String pathToJson, String pathToXml) {

    long start = System.currentTimeMillis();
    long finish = start;

    try (Stream<Path> resources = Files
        .walk(Paths.get(pathToJson))
        .filter(Files::isRegularFile)) {

      violations = new ConcurrentHashMap<>();

      //New logic: for our Paths we're matching a new thread (Completable future)
      var future = resources.map(a -> CompletableFuture.runAsync(() -> {
        workWithFile(a, violations);
      }, executorService)).toList();

      //Saying our threads to start and wait while another processing file
      future.stream().map(CompletableFuture::join).toList();

      result = violations.entrySet().stream()
          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
          .map(a -> new ViolationDTO(a.getKey(), a.getValue()))
          .collect(Collectors.toList());

      writeToXml(pathToXml, result);

      finish = System.currentTimeMillis();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return finish - start;
  }

  /**
   * Fills the xml file with data obtained from the list of all violations.
   *
   * @param path       - path to xml file.
   * @param violations - Map of all type of violation with according amount of fine.
   */

  private void writeToXml(String path, List<ViolationDTO> violations) {
    try {
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
      xmlMapper.writer()
          .withRootName("Statistics")
          .writeValue(new File(path), violations);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method parse line by line particular json file, analyze him and change the total result.
   *
   * @param filePath   - path to particular json file.
   * @param violations - Map of all type of violation with according amount of fine.
   */

  private void workWithFile(Path filePath,
                            ConcurrentMap<String, BigDecimal> violations) {

    JsonFactory jfactory = new JsonFactory();
    try (JsonParser jParser = jfactory.createParser(filePath.toFile())) {

      String currentToken = null;
      BigDecimal currentFine = null;

      while (jParser.nextToken() != JsonToken.END_ARRAY) {
        String fieldname = jParser.getCurrentName();

        if ("type".equals(fieldname)) {
          jParser.nextToken();
          currentToken = jParser.getText();
        }

        if ("fine_amount".equals(fieldname)) {
          jParser.nextToken();
          currentFine = BigDecimal.valueOf(Double.valueOf(jParser.getText()));
          currentFine = currentFine.setScale(2, RoundingMode.HALF_UP);
        }

        if (currentFine != null && currentToken != null) {
          //Add synchronized block for correct access to Map without race condition
          synchronized (violations) {
            if (violations.containsKey(currentToken)) {
              violations.put(currentToken, currentFine.add(violations.get(currentToken)));
            } else {
              violations.put(currentToken, currentFine);
            }
          }
          currentFine = null;
          currentToken = null;

        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
