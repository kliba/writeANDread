package com.matritellabs.utama.exam4.kliba;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Person {

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
        logger.info("1 person instance created, details: name: " + getName() +
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
     * From Person class calling this method if you would like to modify the name of the person
     * @param name modified name comes back
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Form Person class provide the information about the
     * @return birth day of the person
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * From Person class calling this method if you would like to modify the
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Form Person class provide the information about the
     * @return
     */
    public String getMothersName() {
        return mothersName;
    }

    /**
     *
     * @param mothersName
     */
    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    /**
     * Form Person class provide the information about the address
     * @return current address of the relevant person
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
