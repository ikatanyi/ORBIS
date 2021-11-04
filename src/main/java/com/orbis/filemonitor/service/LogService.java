package com.orbis.filemonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
public class LogService {

    private final FileService parser;

    @Value("${writer.dir.unread}")
    private String dir;


    // @Scheduled(fixedDelay = 5000)
    @Scheduled(cron = "${writer.log.job.cron}")
    public void fileReader() throws FileNotFoundException {
        //Creating a File object for directory
        File directoryPath = new File(dir);

        //List of all files and directories
        File filesList[] = directoryPath.listFiles((dir, name)-> name.toLowerCase().endsWith(".txt"));
        Scanner sc = null;
        for (File file : filesList) {

            //Instantiating the Scanner class
            sc = new Scanner(file);
            String input;
            int count = 0;
            StringBuffer sb = new StringBuffer();
            while (sc.hasNextLine()) {
                input = sc.nextLine();

                if (input.equals("ORBIS")) {
                    count++;
                }
            }
            sb.append(file.getName() + " - " + count);
            sc.close();
            parser.fileWriter(sb.toString(), "search_results", "log");
            parser.moveFile(file);
        }
    }
}
