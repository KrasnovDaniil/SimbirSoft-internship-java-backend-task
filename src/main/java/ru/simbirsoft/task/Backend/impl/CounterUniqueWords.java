package ru.simbirsoft.task.Backend.impl;

import org.apache.log4j.Logger;
import ru.simbirsoft.task.Backend.interfaces.Counter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс подсчёта уникальных слов в тексте
 */
public class CounterUniqueWords implements Counter {
    private Logger LOGGER = Logger.getLogger(CounterUniqueWords.class.getName()); // логгер

    /**
     * Суть такова: в качестве параметра text подаётся текст на сайте, затем он разбивается на массив строк (слов),
     * далее регулярными выражениями выбираются слова, которые и подсчитываются.
     */
    private String[] text;  // встреченный текст
    private Map<String, Integer> uniqueWords;    // хэш-таблица для статистики
    private List<String> order; // вспомогательный массив для вывода статистики по порядку встреченных слов

    public CounterUniqueWords() {
        uniqueWords = new HashMap<>();
        order = new LinkedList<>();
        LOGGER.info(CounterUniqueWords.class.getName() + " got the user data");
    }


    /**
     * Метод подсчёта уникальных слов
     */
    @Override
    public void countUniqueWords(String rawText) {
        /* Суть:
         * 1) рег. выр-ем выбираются слова из букв
         * 2) если слово не встречалось ранее, то учесть
         * 3) если встречалось, то увеличить его счётчик на 1
         * */
        String regExp = "(,|<|>|\\.|\\s|!|\\?|\"|;|:|[|]|\\(|\\)|\\n|\\r|\\t)+";

        LOGGER.info("Counting unique words has begun");
        String regexpForWords = "([а-яё]*|[a-z]*)";
        text = rawText.split(regExp);
        Pattern pattern = Pattern.compile(regexpForWords);
        int newAmount;
        for (String word : text) {
            word = word.toLowerCase();
            Matcher matcher = pattern.matcher(word);
            if (!word.equals("") && matcher.matches()) {
                if (!uniqueWords.containsKey(word)) {
                    uniqueWords.put(word, 1);
                    order.add(word);
                }
                else {
                    newAmount = uniqueWords.get(word) + 1;
                    uniqueWords.put(word, newAmount);
                }
            }
        }
    }


    /**
     * Метод вывода на экран статистики распространения уникальных слов
     */
    @Override
    public Map getResult() {
        LOGGER.info("Print the statistics on screen");
        // Вывод согласно порядку встречи слов в тексте
        for (String item : order) {
            System.out.println(item + " - " + uniqueWords.get(item));
        }
        return uniqueWords;
    }

}
