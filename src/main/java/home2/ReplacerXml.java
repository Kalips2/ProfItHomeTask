package home2;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplacerXml {

  /**
   * Create a copy of file in which the value of the surname attribute is combined with the name.
   * The surname attribute is deleted.
   * Formatting of the output file repeated the formatting of the input file.
   *
   * @param newFilePath - path to new xml file.
   * @param filePath    - path to xml file we need to copying.
   */
  public void editXmlFile(String newFilePath, String filePath) {
    try (BufferedWriter output = new BufferedWriter(new FileWriter(newFilePath));
         Scanner scanner = new Scanner(new FileReader(filePath))
    ) {

      Pattern namePattern = Pattern.compile("\\sname\\s*=\\s*\"(\\W+|\\w+)\"");
      Pattern surnamePattern = Pattern.compile("\\ssurname\\s*=\\s*\"(\\W+|\\w+)\"");
      Pattern endPersonPattern = Pattern.compile("/>|</person>");

      String name = null;
      String surname = null;
      String temporary = "";

      while (scanner.hasNextLine()) {

        String nextLine = scanner.nextLine();
        //Deleting redundant tabulation and get surname
        Matcher surnameMatcher = surnamePattern.matcher(nextLine);
        if (surnameMatcher.find() && surname == null) {
          surname = surnameMatcher.group(1);
          nextLine = nextLine.replaceAll("\\ssurname\\s*=\\s*\"(\\W+|\\w+)\"", "");
        }
        if (nextLine.replaceAll("\\s*", "").equals("")) {
          nextLine = "";
        }

        //In order to don't add to new file redundant \n in the end
        temporary += nextLine + (scanner.hasNextLine() ? "\r\n" : "");

        Matcher nameMatcher = namePattern.matcher(temporary);
        Matcher endMatcher = endPersonPattern.matcher(temporary);

        if (nameMatcher.find() && name == null) {
          name = nameMatcher.group(1);
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

  private String deleteSurnameFromLine(String nextLine, String surname) {
    System.out.println(nextLine);
    Pattern surnamePattern = Pattern.compile("\\ssurname\\s*=\\s*\"(\\W+|\\w+)\"");
    Matcher surnameMatcher = surnamePattern.matcher(nextLine);
    if (surnameMatcher.find() && surname == null) {
      surname = surnameMatcher.group(1);
      nextLine = nextLine.replaceAll("\\ssurname\\s*=\\s*\"(\\W+|\\w+)\"", "");
    }

    if (nextLine.replaceAll("\\s*", "").equals("")) {
      nextLine = "";
    }
    System.out.println(nextLine + " " + surname);
    return nextLine;
  }
}
