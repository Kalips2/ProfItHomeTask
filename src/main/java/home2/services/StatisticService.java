package home2.services;

import home2.JsonToXmlStatistics;

/**
 * This class is needed just to test my methods in another classes.
 * */
public class StatisticService {
  public static final JsonToXmlStatistics jsonToXmlStatistics = new JsonToXmlStatistics();

  public void createStatics(String file, String newFile) {
    jsonToXmlStatistics.createStatistics(file, newFile);
  }
}
