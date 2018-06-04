package com.matritellabs.utama.exam4.kliba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class PersonListLoader2 {

    private static final Logger logger = LoggerFactory.getLogger("PersonListLoader Class ");


    /**
     * calling relevant file format (ONE_DATA_PER_LINE or CSV) and revoking the relevant method.
     * @param fileURL file what should be written
     * @param fileFormat format how it should be written to a file
     * @param personList list of person whom data should be written
     */
    public static void writePersonsToFile(String fileURL, PersonFileFormat fileFormat, List<Person> personList) {
        switch (fileFormat) {
            case CSV:
                writeCSVtoFile(fileURL, personList);
                break;
            case ONE_DATA_PER_LINE:
                writeONE_DATA_PER_LINEToFile(fileURL, personList);
                break;
            default:
                logger.error("Nothing is created. Probably the file is not exist on" + fileURL +
                        " or the file format not recognized.");
        }
    }

    /**
     * If the file format is CSV it writes evey fields value on a different lines
     * into a file
     * @param fileURL file path where the ONE_DATA_PER_LINE is located
     * @param personList list what should be written to a File
     */
    private static void writeCSVtoFile(String fileURL, List<Person> personList) {
        Path path = Paths.get(fileURL);
        String line = "";
        String all = "";
        for (int i = 0; i < personList.size(); i++) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) {
                line = personList.get(i).getName() + ";" + personList.get(i).getBirthday() + ";"
                        + personList.get(i).getMothersName() + ";" + personList.get(i).getAddress()
                        + System.lineSeparator();
                all += line;
                writer.write(all);
            } catch (IOException ex) {
                logger.error("Path you added: " + fileURL + " is not valid ");
                ex.getMessage();
            }
        }
    }

    /**
     * If the file format is ONE_DATA_PER_LINE it writes evey fields value on a different lines
     * into a file
     * @param fileURL file path where the ONE_DATA_PER_LINE is located
     * @param personList list what should be written to a File
     */
    private static void writeONE_DATA_PER_LINEToFile(String fileURL, List<Person> personList) {
        Path path = Paths.get(fileURL);

        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))) {
            for (int i = 0; i < personList.size(); i++) {
                writer.write(personList.get(i).getName() + "\n");
                writer.write(personList.get(i).getBirthday() + "\n");
                writer.write(personList.get(i).getMothersName() + "\n");
                writer.write(personList.get(i).getAddress() + "\n");
                logger.info("One person was written to " + fileURL
                        + " with " + PersonFileFormat.ONE_DATA_PER_LINE);
            }
        } catch (IOException ex) {
            logger.error("Path you added: " + fileURL + " is not valid ");
            ex.getMessage();
        }

    }

    /**
     * It can load tow tipes of file format (CSV, ONE_DATA_PER_LINE). From those it creates list of Person.
     * For further details read the docs of
     * handleONE_DATA_PER_LINEFileFormat()
     * and
     * handleCSVFileFormat()
     *
     * @param fileURL    this is the file path what shows the location of the file. I has to be containing
     *                   the file name as well.
     * @param fileFormat If one line contains all of the necessary details of a Person constructor parameters
     *                   and separated by semicolon select the: CSV
     *                   If every line contains one data please select the ONE_DATA_PER_LINE file format
     *                   note: Both of them must contain -> name, birth date, mother name, address
     * @return list of Person
     */
    public static List<Person> readPersonFromFile(String fileURL, PersonFileFormat fileFormat) {
        switch (fileFormat) {
            case CSV:
                return handleCSVFileFormat(fileURL);
            case ONE_DATA_PER_LINE:
                return handleONE_DATA_PER_LINEFileFormat(fileURL);
            default:
                logger.error("Wanted to load a not recognized File format");
        }
        return null;
    }

    /**
     * If the external file type is ONE_DATA_PER_LINE it will load those parts of line into the relevant
     * variables then creates instances of Person finally add them to a list.
     *
     * @param fileURL this is the path what shows the location of the file what should be loaded
     * @return List of Persons
     */
    private static List<Person> handleONE_DATA_PER_LINEFileFormat(String fileURL) {
        List<Person> people = new ArrayList<>();
        Path path = Paths.get(fileURL);
        String tempName = "";
        String tempBirthday = "";
        String tempMothersName = "";
        String tempAddress = "";

        int lineCounter = 0;
        try {
            BufferedReader br = Files.newBufferedReader(path, Charset.forName("UTF-8"));
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                if (currentLine.length() == 0)
                    break;
//                    System.out.println("currentLine: " + currentLine);

                if (lineCounter == 0) {
                    tempName = currentLine;
                    lineCounter++;
                } else if (lineCounter == 1) {
                    tempBirthday = currentLine;
                    lineCounter++;
                } else if (lineCounter == 2) {
                    tempMothersName = currentLine;
                    lineCounter++;
                } else if (lineCounter == 3) {
                    tempAddress = currentLine;
                    lineCounter = 0;

                    //put person into people list
                    people.add(new Person(tempName, splitADate(tempBirthday),
                            tempMothersName, tempAddress));
//                        System.out.println("size of people " + people.size())
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (lineCounter != 0) {
                logger.warn("In the ONE_DATA_PER_LINE file might not been filled successfully. Probably 1 or " +
                        "more details missing to create a Person instance");
            }
        }
        return people;
    }

    /**
     * If the external file type is CSV it will load those parts of line into the relevant variables then
     * creates instances of Person finally add them to a list.
     *
     * @param fileURL this is the path what shows the location of the file what should be loaded
     * @return List of Persons
     */
    private static List<Person> handleCSVFileFormat(String fileURL) {
        List<Person> people = new ArrayList<>();
        Path path = Paths.get(fileURL);
        String tempName = "";
        String tempBirthday = "";
        String tempMothersName = "";
        String tempAddress = "";

        try {
            BufferedReader br = Files.newBufferedReader(path, Charset.forName("UTF-8"));
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                if (currentLine.length() == 0)
                    break;
//                    System.out.println("currentLine: " + currentLine);

                if (verifyFileFormat(currentLine)) {
                    //put person details to temp fields

                    tempName = splitALine(currentLine).get(0);
                    tempBirthday = splitALine(currentLine).get(1);
                    tempMothersName = splitALine(currentLine).get(2);
                    tempAddress = splitALine(currentLine).get(3);

//                        System.out.println("currentLine = " + currentLine);
//                        System.out.println("tempName = " + tempName);
//                        System.out.println("tempBirthday = " + tempBirthday);
//                        System.out.println("tempMothersName = " + tempMothersName);
//                        System.out.println("tempAddress = " + tempAddress);

                    //put person into people list
                    people.add(new Person(tempName, splitADate(tempBirthday),
                            tempMothersName, tempAddress));
//                        System.out.println("size of people " + people.size());

                } else {
                    logger.error("Not verified file format, it is RuntimeException");
                    throw new RuntimeException();
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return people;
    }

    /**
     * Input String checked. If exactly 3 piece of ';' found return: true
     * other hand returns false
     *
     * @param line input string that should contain ';'
     * @return boolean
     */
    private static boolean verifyFileFormat(String line) {
        int semiCount = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ';') {
                semiCount++;
            }
        }
        if (semiCount != 3) {
            logger.warn("File is not containing 3 of ';'s ");
            return false;
        } else {
            logger.info("File is containing exactly 3 of ';'s ");
            return true;
        }
    }

    /**
     * Method waits an input string what is cut at every ';' and add them to list on new index.
     * note: is input does not contain ';' then list.size() = 1
     *
     * @param line input string what should be split
     * @return string of List
     */
    private static List<String> splitALine(String line) {
        List<String> partsList = new ArrayList<>();
        StringTokenizer token = new StringTokenizer(line, ";");

        while (token.hasMoreTokens()) {
            partsList.add(token.nextToken() + "");
        }
//        System.out.println("partsList = " + partsList);
//        System.out.println("partsList size = " + partsList.size());
        return partsList;
    }

    /**
     * Creates a simpleDate form an input string. The  year, month and day must be separated and use the agreed format
     * (eg:"yyyy-MM-dd")
     *
     * @param date string what should be Date
     * @return Date or if the input string is on a different format the return value is null
     */
    private static Date splitADate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            logger.warn(e.getMessage());
            return null;
        }
    }


}
