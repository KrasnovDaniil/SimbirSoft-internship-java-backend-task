package ru.simbirsoft.task.Backend.interfaces;

import java.util.Map;

public interface Counter {
    void countUniqueWords(String text);
    Map getResult();
}
