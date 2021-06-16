package ru.simbirsoft.task.Backend.impl;

import org.apache.log4j.Logger;     // Подключение логера log4j

import ru.simbirsoft.task.Backend.impl.TextCatcher;
import ru.simbirsoft.task.Backend.interfaces.Catcher;
import ru.simbirsoft.task.Backend.interfaces.Counter;
import ru.simbirsoft.task.Backend.interfaces.Downloader;
import ru.simbirsoft.task.Backend.interfaces.Runner;
import ru.simbirsoft.task.Main;


public class TaskRunner implements Runner {
    /** Здесь, а также во всех остальных классах программы будет использоваться логгер Log4j */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private Counter counter;
    private Downloader downloader;
    private Catcher catcher;


    public TaskRunner(Counter counter, Downloader downloader, Catcher catcher) {
        this.counter = counter;
        this.downloader = downloader;
        this.catcher = catcher;
    }

    public void perform() {
        LOGGER.info("Program has ran");
        try {
           // выполняется загрузка страницы
            downloader.downloadHtmlCode(counter);
            // извлекается текст из веб-страницы
            String processedText = catcher.insertPageText();
            // Подсчитываются статистика уникальных слов
            counter.countUniqueWords(processedText);
            counter.getResult();

            LOGGER.info("Result was displayed");
        } catch (Exception e) {     // Если сверху было получено исключение, то произойдёт вывод его сообщения на экран и программа прекратит работать
            System.out.println(e.getMessage());
        }
        LOGGER.info("End of program working");
    }


}
