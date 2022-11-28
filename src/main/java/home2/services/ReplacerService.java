package home2.services;

import home2.ReplacerXml;

/**
 * This class is needed just to test my methods in another classes.
 * */
public class ReplacerService {
  public static final ReplacerXml replacerXml = new ReplacerXml();

  public void createStatics(String file, String newFile) {
    replacerXml.editXmlFile(file, newFile);
  }
}
