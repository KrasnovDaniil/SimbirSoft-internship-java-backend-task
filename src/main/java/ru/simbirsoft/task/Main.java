package ru.simbirsoft.task;

import ru.simbirsoft.task.Backend.impl.CounterUniqueWords;
import ru.simbirsoft.task.Backend.impl.DownloadPage;
import ru.simbirsoft.task.Backend.impl.TaskRunner;
import ru.simbirsoft.task.Backend.impl.TextCatcher;
import ru.simbirsoft.task.Backend.interfaces.Catcher;
import ru.simbirsoft.task.Backend.interfaces.Counter;
import ru.simbirsoft.task.Backend.interfaces.Downloader;
import ru.simbirsoft.task.UI.impl.InputUserData;
import ru.simbirsoft.task.UI.interfaces.InputData;

public class Main {
    /** Здесь запускается программа
     * При запуске программмы, нужно просто ввести ссылку на веб-страницу,
     * а далее всё произойдёт согласно описанию в "Руководстве по запуску"
     */
    static private String link = "";
    static private String path = "downloaded web-pages\\";
    static private int cnt = 0;

    public static void main(String[] args) {

        InputData inputData = new InputUserData();
        if (link.equals(""))
            link = inputData.getUserData();  // Получить ссылку от пользователя
        cnt++;
        path += "Source_" + cnt + ".html";

        // Создание необходимых объектов
        Counter mainCounter = new CounterUniqueWords();
        Downloader mainDownloader = new DownloadPage(link, path);
        Catcher mainCatcher = new TextCatcher(link);
        TaskRunner program = new TaskRunner(mainCounter, mainDownloader, mainCatcher);

        // Начало работы
        program.perform();
    }


    public static void run(String _link){
        link = _link;
        main(null);
    }

}
