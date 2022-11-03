package home1;

import java.util.Arrays;


public class Task1 {


    /**
     * Return an array consisting only of positive number sorted in descending order
     * */

    public int[] checkAndReversePositiveNumber(int[] array) {

        array = Arrays.stream(array)
                .filter(a-> a>=0)
                .sorted()
                .toArray();

        for(int i = 0, j = array.length - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }

}
