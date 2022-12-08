package home3.task1.services;

import home3.task1.StatisticsMultithread;

/**
 * The class is needed to wrap StatisticsMultithread up and get convenient access to the things we need
 */
public class StatisticService {
  public static StatisticsMultithread statisticsMultithread;

  /**
   * The entry point of class. It's just create a new StatisticsMultithread class and passes on
   * all information users entered such as number of Thread and Iterations.
   * Displays a time to operate with files and generate statistics
   */
  public void createStatistics(int numberOfThread, int numberOfIteration,
                               String path_to_dir, String path_to_xml) {
    statisticsMultithread = new StatisticsMultithread(numberOfThread);
    long time = statisticsMultithread.createStatistics(numberOfIteration, path_to_dir, path_to_xml);
    System.out.println(
        "For " + numberOfThread + " threads is take " + time + " ms an average for " +
            numberOfIteration + " iterations");
  }
}
