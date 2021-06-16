package ru.simbirsoft.task.Backend.impl;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.simbirsoft.task.Backend.interfaces.Catcher;

import java.io.IOException;

public class TextCatcher implements Catcher {

    static private final Logger LOGGER = Logger.getLogger(DownloadPage.class.getName());

    private String link;

    public TextCatcher(String link) {
        this.link = link;
    }

    /** Выделение текста из веб-страницы*/
    @Override
    public String insertPageText() throws IOException {

        /** Для выделения текста из html кода страницы, я использовал библиотеку Jsoup, которой пользуюсь ниже */
        Document doc = null;
        Connection connection = null;
        /** Jsoup.Document хранит в себе html документ для удобного его парсинга */

        try {
            connection = Jsoup.connect(link);
            doc = connection.get();
        }
        catch (IOException e) {
            LOGGER.error("Wrong link!");
            throw new IOException("The given link is wrong");
        }
        LOGGER.info("Jsoup document was formed");
        String pageText = doc.text();          // Получен html код
        LOGGER.info("The text was captured");
        String insertedText = Jsoup.parse(pageText).text();   // Получен текст страницы
        LOGGER.info("Parsing completed");
        return insertedText;
    }


}
