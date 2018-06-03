package com.matritellabs.utama.exam4.kliba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonListLoader extends Exception {


    public static final Logger logger = LoggerFactory.getLogger("PersonListLoader Class ");

    public static List<Person> readPersonFromFile(String fileURL, PersonFileFormat fileFormat) {
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
                    //generate parameters of Person() --constructor

                    String[] personList = line.split(";");
                    System.out.println("personList.length()1 = " + personList.length);
 //                   for (String details : personList) {
 //                       System.out.println("details: " +details);
 //                   }

                    tempName = personList[0];
                   tempBirthday = personList[1];
        /*             tempMothersName = personList[2];
                    tempAddress = personList[3];

                    //Check the temp variables
                    System.out.println("personList.length = " + personList.length);
                    System.out.println("tempName = " + tempName);
                    System.out.println("tempBirthday = " + tempBirthday);
                    System.out.println("tempMothersName = " + tempMothersName);
                    System.out.println("tempAddress = " + tempAddress);


                    String[] dateList = tempBirthday.split("-");
                    for (String date : dateList) {
//                        System.out.println("date = " + date);
                    }

                    int year = Integer.parseInt(dateList[0]);
                    int month = Integer.parseInt(dateList[1]);
                    int day = Integer.parseInt(dateList[2]);
                    LocalDate brthDate = LocalDate.of(year, month, day);
                    System.out.println("brthDate = " + brthDate);
                    System.out.println("year = " + year);
                    System.out.println("month = " + month);
                    System.out.println("day = " + day);

                    //adding a new Person to the list named: people
                    //    constr: public Person(String name, Date birthday, String mothersName, String address)
                    people.add(new Person(tempName, asDate(brthDate), tempMothersName, tempAddress));
*/
                    //here is possible check the poeple list, toString() should be created somehow
                    System.out.println("people = " + people);

                }
            } catch (IOException e) {
                e.getMessage();
            }

            return people;
        } else if (fileFormat == PersonFileFormat.ONE_DATA_PER_LINE) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileURL))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.getMessage();
            }
            return people;
        }
        return people;
    }

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

}
