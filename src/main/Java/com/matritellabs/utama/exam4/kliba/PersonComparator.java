package com.matritellabs.utama.exam4.kliba;

import java.util.Comparator;

public abstract class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.equals(p2)) {
            return 0;
        } else {
            if (p1.getBirthday().equals(p2.getBirthday())) {
                if (p1.getName().equals(p2.getName())) {
                    if (p1.getMothersName().length() >= p2.getMothersName().length()) {
                        for (int i = 0; i < p2.getMothersName().length(); i++) {
                            if (p1.getMothersName().charAt(i) > p2.getMothersName().charAt(i)) {
                                return -1;
                            } else if (p1.getMothersName().charAt(i) < p2.getMothersName().charAt(i)) {
                                return 1;
                            }
                        }
                    }
                } else {
                    if (p1.getName().length() >= p2.getName().length()) {
                        for (int i = 0; i < p2.getName().length(); i++) {
                            if (p1.getName().charAt(i) > p2.getName().charAt(i)) {
                                return 1;
                            } else if (p1.getName().charAt(i) < p2.getName().charAt(i)) {
                                return -1;
                            }
                        }
                    } else if (p1.getName().length() < p2.getName().length()) {
                        for (int i = 0; i < p1.getName().length(); i++) {
                            if (p1.getName().charAt(i) > p2.getName().charAt(i)) {
                                return 1;
                            } else if (p1.getName().charAt(i) < p2.getName().charAt(i)) {
                                return -1;
                            }
                        }
                    }
                }
            } else if (p1.getBirthday().before(p2.getBirthday())) {
                return -1;
            } else if (p2.getBirthday().before(p1.getBirthday())) {
                return 1;
            }
        }
    return 0;
    }

}
