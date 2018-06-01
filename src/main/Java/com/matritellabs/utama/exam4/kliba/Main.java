package com.matritellabs.utama.exam4.kliba;

import static com.matritellabs.utama.exam4.kliba.PersonFileFormat.CSV;

public class Main {

    public static void main(String[] args) {
        PersonListLoader.readPersonFromFile("/home/kliba/dev/lab/exam4Kliba/src/main/docs/ember.ONE_DATA_PER_LINE", CSV);
    }

}
