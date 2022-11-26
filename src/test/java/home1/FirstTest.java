package home1;

import home1.Task1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FirstTest {

    private static final Task1 task1 = new Task1();

    @Test
    public void checkLengthOfResult() {
        int[] input = {1, -10, 0, 9, -2, 3};
        int expected = 4;
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertEquals(expected, result.length);
    }

    @Test
    public void checkIsCorrectResultFirst() {
        int[] input = {1, -10, 0, 9, -2, 3};
        int[] expected = {9, 3, 1, 0};
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkIsCorrectResultSecond() {
        int[] input = {1245, -163, 982, 0, -24, 82, 0, 63, 98, 2, -9};
        int[] expected = {1245, 982, 98, 82, 63, 2, 0, 0};
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkIsCorrectResultThird() {
        int[] input = {63, 0, 0, 0, 0, 123, 24, 63, 9, -2, 1235};
        int[] expected = {1235, 123, 63, 63, 24, 9, 0, 0, 0, 0};
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkIsCorrectResultFourth() {
        int[] input = {-5, -10, 34, -65, -257, -246};
        int[] expected = {34};
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkIsCorrectResultForAllPositiveNumbers() {
        int[] input = {23, 12, 282, 32, 9, 2, 0};
        int[] expected = {282, 32, 23, 12, 9, 2, 0};
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkIsCorrectResultForAllNegativeNumbers() {
        int[] input = {-3, -84, -2083, -5};
        int[] expected = {};
        int[] result = task1.checkAndReversePositiveNumber(input);
        assertArrayEquals(expected, result);
    }

}
