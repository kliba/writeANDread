package com.matritellabs.utama.exam4.kliba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {


        PersonListLoader2.readPersonFromFile(
                "/home/kliba/dev/lab/exam4Kliba/src/main/docs/ember.csv", PersonFileFormat.CSV);
        PersonListLoader2.readPersonFromFile("null", PersonFileFormat.ONE_DATA_PER_LINE);


        System.out.println(PersonListLoader2.readPersonFromFile(
                "/home/kliba/dev/lab/exam4Kliba/src/main/docs/ember.csv", PersonFileFormat.CSV));

        System.out.println(PersonListLoader2.readPersonFromFile(
                "/home/kliba/dev/lab/exam4Kliba/src/main/docs/ember.ONE_DATA_PER_LINE"
                , PersonFileFormat.ONE_DATA_PER_LINE));


        try {
            Person p1 = new Person("A",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-02"),
                    "Momy", "homeless");

            Person p2 = new Person("A",
                    new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-02"),
                    "Momy", "homelesssssss");

            System.out.println(p1.compare(p1, p2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Person> testList = new ArrayList<>();
        try {
            testList.add(new Person("Adolf", new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-12"),
                    "Mom", "Sheol sziget"));
            testList.add(new Person("Adolf", new SimpleDateFormat("yyyy-MM-dd").parse("2010-11-12"),
                    "Mom", "Sheol sziget"));
            testList.add(new Person("Adolf", new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-12"),
                    "Mom", "Sheol sziget"));
            testList.add(new Person("Aldi", new SimpleDateFormat("yyyy-MM-dd").parse("2010-12-12"),
                    "Mummy", "Who knows"));

            PersonListLoader2.writePersonsToFile(
                    "/home/kliba/dev/lab/exam4Kliba/src/main/docs/written/csv.txt",
                    PersonFileFormat.CSV, testList);

            PersonListLoader2.writePersonsToFile(
                    "/home/kliba/dev/lab/exam4Kliba/src/main/docs/written/online.txt",
                    PersonFileFormat.ONE_DATA_PER_LINE, testList);

            PersonListLoader2.writePersonsToFile(
                    "/home/kliba/dev/lab/exam4Kliba/src/main/docs/written/jajj/miezte/online.teeext",
                    PersonFileFormat.ONE_DATA_PER_LINE, testList);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
