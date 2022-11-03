import home1.Task2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SecondTest {
    private Task2 task2;

    @Before
    public void newTask() {
        task2 = new Task2();
    }


    @Test
    public void checkIsCorrectSizeOfResult() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 5;

        assertSame(expected, result.size());
    }

    @Test
    public void checkIsCorrectNumberOfLol() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);


        String hashtag = "#Lol";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 6;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkIsCorrectNumberOfGoodDay() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#GoodDay";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 4;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkIsCorrectNumberOfILoveYou() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#ILoveYou";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 3;

        assertSame(expected, result.get(hashtag));
    }
    @Test
    public void checkIsCorrectNumberOfBreak() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#Break";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 2;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkUnExistHashTag() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);

        assertSame(null, result.get("#UnExisthashTag"));
    }
    @Test
    public void checkIsCorrectNumberOfHaha() {
        List<String> input = new ArrayList<>();
        initializeInputCorrect(input);

        String hashtag = "#%Haha";
        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 2;

        assertSame(expected, result.get(hashtag));
    }

    @Test
    public void checkTheSizeLesserThanFive() {
        List<String> input = new ArrayList<>();
        initializeInputIncorrect(input);

        Map<String, Integer> result = new HashMap<>();
        result = task2.fivePopularHashtag((ArrayList<String>) input);
        int expected = 4;

        assertSame(expected, result.size());
    }



    @After
    public void deleteTask() {
        task2 = null;
    }

    private static void initializeInputCorrect(List<String> input) {
        input.add("1 - Hello subscribes! #GoodDay#ILoveYou  #CantLiveWithoutU #Lol #Break");
        input.add("2 - My love is you! #ILoveYou You are my bestie #Cool #GoodDay ahahha #Lol ");
        input.add("3 - It was good cinema! #Lol #Afraid #Afraid #Afraid #Break sdsdsd #Goodday #GoodDay");
        input.add("4 - #Lol #Lol #Lol hello my friend ahaha #%Haha");
        input.add("5 - #GoodDayToAll #GoodDayy #GoodDay - is me the best hashtags! ");
        input.add("6 - #Enemy today was difficult day! #Lol I cant say anything!");
        input.add("7 - #%Haha It was so funny! Thx to all my friend #Lol#AfraidTaxi#ILoveYou");
        input.add("8 - ");
    }
    private static void initializeInputIncorrect(List<String> input) {
        input.add("7 - #%Haha It was so funny! Thx to all my friend #Lol#AfraidTaxi#ILoveYou");
        input.add("8 - ");
    }
}
