package home1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class with solution for First Task.
 * */
public class Task1 {


    /**
     * Return an array consisting only of positive number sorted in descending order
     *
     * @param array - Array of integer numbers
     * @return sorted in descending order array of positive numbers (>=0)
     * */

    public int[] checkAndReversePositiveNumber(int[] array) {
        return Arrays.stream(array)
                .boxed()
                .filter(a-> a>=0)
                .sorted(Comparator.reverseOrder())
                .mapToInt(a->a)
                .toArray();
    }

}
