package home2;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import home2.model.ViolationDTO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class JsonToXmlStatistics {

  public static final String PATH_TO_DIRECTORY = "src/main/resources/home2/task2/violations";
  public static final String PATH_TO_XML = "src/main/resources/home2/task2/statistics.xml";

  public static void main(String[] args) {

    try (Stream<Path> resources = Files
        .walk(Paths.get(PATH_TO_DIRECTORY))
        .filter(Files::isRegularFile)) {

      Map<String, Integer> listOfViolation = new HashMap<>();
      List<ViolationDTO> result = new ArrayList<>();
      resources.forEach(a -> workWithFile(a, result, listOfViolation));

      result.sort(Comparator.comparing(ViolationDTO::getFine_amount).reversed());

      writeToXml(PATH_TO_XML, result);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void writeToXml(String path, List<ViolationDTO> result) {
    try {
      XmlMapper xmlMapper = new XmlMapper();
      xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
      xmlMapper.writeValue(new File(path), result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void workWithFile(Path filePath, List<ViolationDTO> result,
                                   Map<String, Integer> listOfViolation) {

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
          if (listOfViolation.containsKey(currentToken)) {
            int index = listOfViolation.get(currentToken);
            result.set(index, new ViolationDTO(currentToken,
                result.get(index).getFine_amount() + jParser.getDoubleValue()));
          } else {
            listOfViolation.put(currentToken, listOfViolation.size());
            result.add(new ViolationDTO(currentToken, jParser.getDoubleValue()));
          }
        }
      }
      jParser.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
