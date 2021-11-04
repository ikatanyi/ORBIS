package com.orbis.filemonitor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class WriterService {

    private final FileService parser;

    @Scheduled(cron = "${writer.generate.job.cron}")
    private void generateRandomString(){
        List<String> files = new ArrayList();
        String ext ="txt";
        String file1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern( "yyyy-MM-dd HHmmss" ))+"_1";
        String file2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern( "yyyy-MM-dd HHmmss" ))+"_2";

        files.add(file1);
        files.add(file2);

        for(int i=1;i<=20;i++){
            int idx = new Random().nextInt(2);
            String randomFile = files.get(idx);
            if(i%2==0){
                parser.fileWriter("ORBIS",randomFile,ext);
            }
            else{
                parser.fileWriter(RandomAlphabeticString(),randomFile,ext);
            }
        }
    }

    public String RandomAlphabeticString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
