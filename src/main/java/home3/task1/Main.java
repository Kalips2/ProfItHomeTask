package home3.task1;

import home3.task1.services.StatisticService;

/**
 * The entry point of application to show the different among time-consuming of work using 2, 4, 8 threads.
 * On my computer I have 6 core, it's affected on result of my experiment.
 * My findings:
 *  To operate 21 files with 10 000 records it's took in average:
 *    with 1 threads -  1685 ms
 *    with 2 threads -  901 ms
 *    with 4 threads -  620 ms
 *    with 8 threads -  709 ms
 * (I've taken 5 iterations for each method and result it's average from all iteration of method)
 * Explanations: in my situation with 8 threads we see that threads do not make my CPU go any faster,
 * it just adds extra work. I have the case when threads fighting over the CPU resource and this
 * take a larger time than simple operation on thread. It's cause a bigger time of processing
 * than on 4 threads, where threads not faced with waiting for disk I/O.
 * Also, to this task attached some tests.
 */
public class Main {
  public static String pathTo = "src/test/resources/home3/statistics/violations";
  public static String pathFrom = "src/test/resources/home3/statistics/statistics.xml";
  public static final int numberOfIterations = 5;

  public static void main(String[] args) {

    StatisticService statisticService = new StatisticService();

    statisticService.createStatistics(1, numberOfIterations, pathTo, pathFrom);
    statisticService.createStatistics(2, numberOfIterations, pathTo, pathFrom);
    statisticService.createStatistics(4, numberOfIterations, pathTo, pathFrom);
    statisticService.createStatistics(8, numberOfIterations, pathTo, pathFrom);

  }
}
