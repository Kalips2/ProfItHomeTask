package home2;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonToXmlStatistics {

  /**
   * Create a new file statistics.xml that store the total amount of fines for each type of
   * violation for all years, sorted by amount (first by the highest amount of fine).
   *
   * @param pathToJson - path to folder with json files that contain information from the database
   * @param pathToXml  - path to new Xml file we want to create
   */
  public void createStatistics(String pathToJson, String pathToXml) {
    try (Stream<Path> resources = Files
        .walk(Paths.get(pathToJson))
        .filter(Files::isRegularFile)) {

      Map<String, BigDecimal> violations = new HashMap<>();

      resources.forEach(a -> workWithFile(a, violations));

      List<ViolationDTO> result = violations.entrySet().stream()
          .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
          .map(a -> new ViolationDTO(a.getKey(), a.getValue()))
          .collect(Collectors.toList());

      writeToXml(pathToXml, result);

    } catch (IOException e) {
      e.printStackTrace();
    }
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
                            Map<String, BigDecimal> violations) {

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
          if (violations.containsKey(currentToken)) {
            violations.put(currentToken, currentFine.add(violations.get(currentToken)));
          } else {
            violations.put(currentToken, currentFine);
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
