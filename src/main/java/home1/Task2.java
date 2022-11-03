package home1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {

    /**
     * Return the most using hashtags from the list of text
     * Notes:
     *  Hashtags is expression like #...
     *  Hashtags like #[#*]... count as one hashtag
     * */
    public HashMap<String, Integer> fivePopularHashtag(ArrayList<String> array) {
        Map<String, Integer> result = new HashMap<>();

        Pattern hashtag = Pattern.compile("#+[^# ]+");

        for (int i = 0; i < array.size(); i++) {
            Matcher matcher = hashtag.matcher(array.get(i));
            while (matcher.find()) {

                //Add in HashMap
                if (result.containsKey(matcher.group())) {
                    result.replace(matcher.group(), result.get(matcher.group()) + 1);
                } else {
                    result.put(matcher.group(), 1);
                }

                //Replace the same hashtag in String
                array.set(i, array.get(i).replace(matcher.group(), ""));
                matcher = hashtag.matcher(array.get(i));
            }
        }
        String s;
        System.out.println(s = result.toString());

        //Sorting and limiting
        Map<String, Integer> finalResult =
                result.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(5)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(s = finalResult.toString());

        return (HashMap<String, Integer>) finalResult;
    }
}
