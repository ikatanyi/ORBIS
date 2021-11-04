package com.orbis.filemonitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

@Service
@PropertySource("classpath:/application.properties")
public class FileService {

    @Value("${writer.dir.unread}")
    private String unread_dir;

    @Value("${writer.dir.read}")
    private String read_dir;
    public void fileWriter(String content, String fileName, String ext) {

        File directory = new File(unread_dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String SourceDir = directory.getName() + "/" + fileName+"."+ext;

        try {
            File file = new File(SourceDir);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);


            // Write in file
            bw.write(content+"\n");

            // Close connection
            bw.close();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void moveFile(File file)  {
        String fileName = file.getName();

        try {

            File directory = new File(read_dir);
            if (!directory.exists()) {
                directory.mkdir();
            }

            Path temp = Files.move(Paths.get(file.getPath()), Paths.get(read_dir + "\\" + fileName), StandardCopyOption.REPLACE_EXISTING);

            if (temp != null) {
                System.out.println("File renamed and moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
