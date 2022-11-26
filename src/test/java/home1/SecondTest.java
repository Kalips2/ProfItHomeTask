package home1;

import home1.Task2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SecondTest {
    private static final Task2 task2 = new Task2();

    @Test
    public void checkTheCorrectResult() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        LinkedHashMap<String, Integer> expected = new LinkedHashMap<>();
        expected.put("#Lol", 6);
        expected.put("#GoodDay", 4);
        expected.put("#ILoveYou", 3);
        expected.put("#Break", 2);
        expected.put("#%Haha", 2);

        assertEquals(expected, result);
    }

    //If equals would incorrect, we need to check each element:

    @Test
    public void checkIsCorrectSizeOfResult() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 5;

        assertSame(expected, result.size());
    }

    @Test
    public void checkIsCorrectNumberOfLol() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);


        String hashtag = "#Lol";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 6;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkIsCorrectNumberOfGoodDay() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#GoodDay";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 4;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkIsCorrectNumberOfILoveYou() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#ILoveYou";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 3;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkIsCorrectNumberOfBreak() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#Break";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 2;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkUnexistHashTag() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);

        assertSame(null, result.get("#UnExisthashTag"));
    }

    @Test
    public void checkIsCorrectNumberOfHaha() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#%Haha";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 2;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkTheSizeLesserThanFive() {
        ArrayList<String> input = new ArrayList<>();
        initializeInputIncorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 4;

        assertSame(expected, result.size());
    }

    @Test
    public void checkTheResultIfInputIsNull() {
        ArrayList<String> input = new ArrayList<>();

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag(input);
        int expected = 0;

        assertSame(expected, result.size());
    }


    private static void initializeInputCorrect(ArrayList<String> input) {
        input.add("1 - Hello subscribes! #GoodDay#ILoveYou  #CantLiveWithoutU #Lol #Break");
        input.add("2 - My love is you! #ILoveYou You are my bestie #Cool #GoodDay ahahha #Lol ");
        input.add("3 - It was good cinema! #Lol #Afraid #Afraid #Afraid #Break sdsdsd #Goodday #GoodDay");
        input.add("4 - #Lol #Lol #Lol hello my friend ahaha #%Haha");
        input.add("5 - #GoodDayToAll #GoodDayy #GoodDay - is me the best hashtags! ");
        input.add("6 - #Enemy today was difficult day! #Lol I cant say anything!");
        input.add("7 - #%Haha It was so funny! Thx to all my friend #Lol#AfraidTaxi#ILoveYou");
        input.add("8 - ");
    }

    private static void initializeInputIncorrect(ArrayList<String> input) {
        input.add("7 - #%Haha It was so funny! Thx to all my friend #Lol#AfraidTaxi#ILoveYou");
        input.add("8 - ");
    }
}
