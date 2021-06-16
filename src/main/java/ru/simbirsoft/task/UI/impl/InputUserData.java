package ru.simbirsoft.task.UI.impl;

import org.apache.log4j.Logger;
import ru.simbirsoft.task.UI.interfaces.InputData;

import java.util.Scanner;

public class InputUserData implements InputData {

    static private final Logger LOGGER = Logger.getLogger(InputUserData.class.getName());
    private String link;

    public InputUserData() {}


    /**
     * Метод для ввода ссылки от пользователя
     */
    @Override
    public String getUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a link:");
        String link = scanner.nextLine();
        System.out.println("Request accepted");
        return link;
    }
}
