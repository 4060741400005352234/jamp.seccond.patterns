package com.epam.jamp.manager;

import com.epam.jamp.model.Person;
import com.epam.jamp.parser.PersonParser;
import com.epam.jamp.reader.FileItemReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersonManager implements Manager<Person> {

    @Override
    public void write(Person person) {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        System.out.println("I'm " + person.getFirstName() + " from file.");
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("persons.txt", true)));
            out.println(person.toString());
            out.close();
        } catch (IOException e) {
            // TODO
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<Person>();
        List<Person> items;
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<Person>(new File("persons.txt"), new PersonParser());
            while ((items = reader.readN(5)).size() != 0) {
                result.addAll(items);
            }
        } catch (Exception e) {
            // TODO
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    @Override
    public Person read(String name) {
        if (name == null || name.length() == 0) {
            throw new RuntimeException("Incorrect parameter.");
        }
        List<Person> items;
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<Person>(new File("persons.txt"), new PersonParser());
            while ((items = reader.readN(1)).size() != 0) {
                for (Person p : items) {
                    if (name.equalsIgnoreCase(p.getFirstName())) {
                        return p;
                    }
                }
            }
        } catch (Exception e) {
            // TODO
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }
}
