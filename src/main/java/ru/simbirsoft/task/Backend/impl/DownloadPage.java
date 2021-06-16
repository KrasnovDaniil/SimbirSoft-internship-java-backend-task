package ru.simbirsoft.task.Backend.impl;

import java.io.*;
import java.net.*;

import org.apache.log4j.Logger;
import ru.simbirsoft.task.Backend.interfaces.Counter;
import ru.simbirsoft.task.Backend.interfaces.Downloader;


public class DownloadPage implements Downloader {
    private String link;    // ссылка
    private File out;       // файл для записи
    static private final Logger LOGGER = Logger.getLogger(DownloadPage.class.getName());

    public DownloadPage(String link, String path) {
        this.link = link;
        this.out = new File(path);
    }


    /** Метод, выполняющий загрузку страницы в файл */
    public void downloadHtmlCode(Counter counter) throws Exception {
        LOGGER.info("run() of " + DownloadPage.class.getName() + " has ran");
        BufferedOutputStream bos = null;
        BufferedInputStream in = null;
        FileOutputStream fos;
        HttpURLConnection http;

        try {
            URL url = new URL(link);
            LOGGER.info("Given link: " + link);
            http = (HttpURLConnection) url.openConnection();
            LOGGER.info("Connection established");

            in = new BufferedInputStream(http.getInputStream());
            fos = new FileOutputStream(this.out);
            bos = new BufferedOutputStream(fos, 1024);

            byte[] buffer = new byte[1024];
            int read = 0;

            while ((read = in.read(buffer, 0, 1024)) >= 0) {
                bos.write(buffer, 0, read);
            }
            LOGGER.info("Downloading page complete");
        }
        catch (IOException ioe) {
            LOGGER.error("Downloading page error");
            throw new IOException("No internet connection or link is incorrect.\n" +
                    "Please check this out and try again");
        }
        finally {
            try {
                if (bos != null) bos.close();
                if (in != null) in.close();
                LOGGER.info("Streams are closed");
            }
            catch (Exception e) {
                LOGGER.error("Closing stream error");
            }
        }
    }

}
