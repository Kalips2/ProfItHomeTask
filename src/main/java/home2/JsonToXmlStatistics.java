package home2;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonToXmlStatistics {

  public static final String PATH_TO_DIRECTORY = "src/main/resources/home2/task2/violations";
  public static final String PATH_TO_XML = "src/main/resources/home2/task2/statistics.xml";

  /**
   * Create a new file statistics.xml that store the total amount of fines for each type of
   * violation for all years, sorted by amount (first by the highest amount of fine).
   *
   * @param pathToJson - path do folder with json files that contain information from the database
   */
  private static void createStatistics(String pathToJson, String pathToXml) {
    try (Stream<Path> resources = Files
        .walk(Paths.get(pathToJson))
        .filter(Files::isRegularFile)) {

      Map<String, Double> violations = new HashMap<>();

      resources.forEach(a -> workWithFile(a, violations));

      Map<String,Double> result =  violations.entrySet().stream()
          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1,
              LinkedHashMap::new));

      writeToXml(pathToXml, result);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fills the xml file with data obtained from the list of all violations.
   *
   * @param path   - path to xml file.
   * @param violations - Map of all type of violation with according amount of fine.
   */

  private static void writeToXml(String path, Map<String, Double> violations) {
    try {
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
      xmlMapper.writeValue(new File(path), violations);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method parse line by line particular json file, analyze him and change the total result.
   *
   * @param filePath        - path to particular json file.
   * @param violations - Map of all type of violation with according amount of fine.
   */

  private static void workWithFile(Path filePath,
                                   Map<String, Double> violations) {

    try {
      JsonFactory jfactory = new JsonFactory();
      JsonParser jParser = jfactory.createParser(filePath.toFile());

      String currentToken = "";

      while (jParser.nextToken() != JsonToken.END_ARRAY) {
        String fieldname = jParser.getCurrentName();

        if ("type".equals(fieldname)) {
          jParser.nextToken();
          currentToken = jParser.getText();
        }

        if ("fine_amount".equals(fieldname)) {
          jParser.nextToken();
          Double fine = jParser.getDoubleValue();
          if (violations.containsKey(currentToken)) {
            violations.put(currentToken, violations.get(currentToken) + fine);
          } else {
            violations.put(currentToken, fine);
          }
        }
      }
      jParser.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    createStatistics(PATH_TO_DIRECTORY, PATH_TO_XML);
  }
}
