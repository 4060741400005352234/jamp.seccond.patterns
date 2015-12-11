package com.epam.jamp.manager;

import com.epam.jamp.model.Person;
import com.epam.jamp.parser.PersonParser;
import com.epam.jamp.reader.FileItemReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersonManager implements Manager<Person> {

    private File file;

    public FilePersonManager(File file) {
        this.file = file;
    }

    @Override
    public void write(Person person) {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.println(person.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<Person>();
        List<Person> items;
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<Person>(file, new PersonParser());
            while ((items = reader.readN(5)).size() != 0) {
                result.addAll(items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    @Override
    public List<Person> read(String name) {
        if (name == null || name.length() == 0) {
            throw new RuntimeException("Incorrect parameter.");
        }
        List<Person> items;
        List<Person> result = new ArrayList<Person>();
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<Person>(file, new PersonParser());
            while ((items = reader.readN(1)).size() != 0) {
                for (Person p : items) {
                    if (name.equalsIgnoreCase(p.getFirstName())) {
                        result.add(p);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }
}
