package com.epam.jamp.main;

import com.epam.jamp.factory.ManagerFactory;
import com.epam.jamp.manager.Manager;
import com.epam.jamp.manager.PersonManagerFactory;
import com.epam.jamp.model.Person;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Person person = new Person("Ivan", "Kotov", 22);

        ManagerFactory factory = new PersonManagerFactory();

        Manager<Person> dbManager = factory.getDBManager();
        dbManager.write(person);
        List<Person> ppp = dbManager.readAll();
        ppp = dbManager.read("Ivan");

        Manager<Person> fileManager = factory.getFleManager("persons.txt");
        fileManager.write(person);

        List<Person> foundedPersons = fileManager.read("Michael");
        for (Person p : foundedPersons) {
            System.out.println("Found person - " + p.getFirstName() + " ages - " + p.getAge());
        }

        List<Person> persons = fileManager.readAll();
        System.out.println("Persons count " + persons.size());
        for (Person p : persons) {
            System.out.println(p.getFirstName());
        }

    }
}
