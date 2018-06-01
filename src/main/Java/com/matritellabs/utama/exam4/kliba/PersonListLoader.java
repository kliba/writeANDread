package com.matritellabs.utama.exam4.kliba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonListLoader extends Exception {

    public static final Logger logger = LoggerFactory.getLogger("PersonListLoader Class ");

    public static List<Person> readPersonsFromFile(String fileURL, PersonFileFormat fileFormat) {
        List<Person> people = new ArrayList<>();
        Path filePath = Paths.get(fileURL);

        String tempName;
        String tempBirthday;
        String tempMothersName;
        String tempAddress;

        if (fileFormat == PersonFileFormat.CSV) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileURL))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    int semicolonCounter = 0;

                    //check the file content
                    for (int i = 0; i < line.length(); i++) {
                        if (line.substring(i, i + 1).equals(";")) {
                            semicolonCounter++;
                            if (semicolonCounter > 3) {
                                logger.error("Incorrect file");
                                throw new RuntimeException("Too many ; in your file");
                            }
                        }
                    }
                    semicolonCounter = 0;

                    //creates Person instances

                    String[] personList = line.split(";");
                    for(String details : personList){
                        System.out.println(details);
                    }

                    tempName = personList[0];
                    tempBirthday = personList[1];
                    tempMothersName = personList[2];
                    tempAddress = personList[3];
                    System.out.println("personList.length = " + personList.length);
                    System.out.println("tempName = " + tempName);
                    System.out.println("tempBirthday = " + tempBirthday);
                    System.out.println("tempMothersName = " + tempMothersName);
                    System.out.println("tempAddress = " + tempAddress);


                    List<String> lineList = new ArrayList<>();
                    for (int i = 0; i < line.length() - 1; i++) {
                        lineList.add(line.substring(i, i + 1));
                    }

                }
            } catch (IOException ioex) {
                ioex.getMessage();
            }
            return people;
        }
        return people;
    }


}
