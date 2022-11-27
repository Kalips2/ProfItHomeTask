package home2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacerXml {

  public static final String NEW_FILE_PATH =
      "D:\\Рабочий стол\\JavaPractice\\ProfItHomeTask\\src\\main\\resources\\home2\\task1\\personsEdited.xml";
  public static final String FILE_PATH =
      "D:\\Рабочий стол\\JavaPractice\\ProfItHomeTask\\src\\main\\resources\\home2\\task1\\persons.xml";

  public static void main(String[] args) {
    editXmlFile(NEW_FILE_PATH, FILE_PATH);
  }

  /**
   * Create a copy of file in which the value of the surname attribute is combined with the name.
   * The surname attribute is deleted.
   * Formatting of the output file repeated the formatting of the input file.
   *
   * @param newFilePath   - path to new xml file.
   * @param filePath - path to xml file we need to copying.
   */
  private static void editXmlFile(String newFilePath, String filePath) {
    try (BufferedWriter output = new BufferedWriter(new FileWriter(newFilePath));
         BufferedReader input = new BufferedReader(new FileReader(filePath))
    ) {

      Pattern namePattern = Pattern.compile("\\sname\\s*=\\s*\"(\\W+|\\w+)\"");
      Pattern surnamePattern = Pattern.compile("\\ssurname\\s*=\\s*\"(\\W+|\\w+)\"");
      Pattern endPersonPattern = Pattern.compile("\\/>|<\\/person>");

      String name = null;
      String surname = null;
      String temporary = "";

      while (input.ready()) {
        temporary += input.readLine() + "\r\n";
        Matcher nameMatcher = namePattern.matcher(temporary);
        Matcher surnameMatcher = surnamePattern.matcher(temporary);
        Matcher endMatcher = endPersonPattern.matcher(temporary);

        if (nameMatcher.find() && name == null) {
          name = nameMatcher.group(1);
        }

        if (surnameMatcher.find() && surname == null) {
          surname = surnameMatcher.group(1);
          temporary = temporary.replaceAll(surnamePattern.pattern(), "");
        }

        if (endMatcher.find()) {
          String replacement =
              nameMatcher.group(0).substring(1, nameMatcher.group(0).length() - 1) + " " + surname +
                  "\"";

          temporary = temporary.replaceAll("name\\s*=\\s*\"(\\W+|\\w+)\"", replacement);
          output.write(temporary);
          output.flush();
          temporary = "";
          name = null;
          surname = null;
        }

      }
      output.write(temporary);
      output.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
