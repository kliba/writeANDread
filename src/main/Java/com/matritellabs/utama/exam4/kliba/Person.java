package com.matritellabs.utama.exam4.kliba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Objects;

public class Person extends PersonComparator{

    private String name;
    private Date birthday;
    private String mothersName;
    private String address;
    public static final Logger logger = LoggerFactory.getLogger("Person Class ");




    /**
     *
     * @param name name of the person as a string type
     * @param birthday birth day of the named person using java.util.date
     * @param mothersName name of the Mother of person using string type
     * @param address address as a home of the person as a string
     */
    public Person(String name, Date birthday, String mothersName, String address) {
        this.name = name;
        this.birthday = birthday;
        this.mothersName = mothersName;
        this.address = address;
        logger.info("1 instance of Person class is created, details: name: " + getName() +
        " brth: " + getBirthday() + " momName: " + getMothersName() + " address: "
        + getAddress());
    }

    /**
     * Form Person class provide the information about the name
     * @return name as a string
     */
    public String getName() {
        return name;
    }


    /**
     * Form Person class provide the information about the
     * @return birth day of the person
     */
    public Date getBirthday() {
        return birthday;
    }


    /**
     * Form Person class provide the information about the mother name
     * @return mother's name
     */
    public String getMothersName() {
        return mothersName;
    }


    /**
     * Form Person class provide the information about the address
     * @return current address of the relevant person
     */
    public String getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName()) &&
                Objects.equals(getBirthday(), person.getBirthday()) &&
                Objects.equals(getMothersName(), person.getMothersName()) &&
                Objects.equals(getAddress(), person.getAddress());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getBirthday(), getMothersName(), getAddress());
    }

    @Override
    public int compare(Person p1, Person p2) {
       return super.compare(p1, p2);
    }
}
