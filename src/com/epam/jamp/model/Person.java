package com.epam.jamp.model;

public class Person {

    private String firstName;
    private String secondName;
    private int age;
    private int iq;

    public Person() {
    }

    public Person(String firstName, String secondName, int age, int iq) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.iq = iq;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIq() {
        return iq;
    }

    public void increaseIq(int iqAmount) {
        this.iq += iqAmount;
    }

    public void reduceIq(int iqAmount) {
        this.iq -= iqAmount;
    }

    public void setIq(int iq) {
        this.iq = iq;
    }

    @Override
    public String toString() {
        return firstName + "," + secondName + "," + age + "," + iq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!secondName.equals(person.secondName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + age;
        return result;
    }
}
