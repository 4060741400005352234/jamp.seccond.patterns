package com.epam.jamp.manager;

import com.epam.jamp.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersonManager implements Manager<Person> {

    @Override
    public void write(Person person) {
        System.out.println("I'm " + person.getFirstName() + " from file.");
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("persons.txt", true)));
            out.println("NAME:" + person.getFirstName());
            out.println("S_NAME:" + person.getSecondName());
            out.println("AGE:" + person.getAge());
            out.println("@@@");
            out.close();
        } catch (IOException e) {
            // TODO
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> persons = new ArrayList<Person>();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("persons.txt"));
            Person person = new Person();
            while ((sCurrentLine = br.readLine()) != null) {
                if (!"@@@".equals(sCurrentLine)) {
                    providePersonFieldWithInfo(person, sCurrentLine);
                } else {
                    persons.add(person);
                    person = new Person();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return persons;
    }

    @Override
    public Person read(String name) {
        Person person = new Person();
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("persons.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                if (!"@@@".equals(sCurrentLine)) {
                    providePersonFieldWithInfo(person, sCurrentLine);
                } else if (name.equalsIgnoreCase(person.getFirstName())) {
                    return person;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return person;
    }

    private void providePersonFieldWithInfo(Person person, String info) {
        if (info == null || info.length() == 0) {
            return;
        }
        String[] parts = info.split(":");
        if (parts.length != 2) {
            return;
        }
        String value = parts[1];
        if ("NAME".equalsIgnoreCase(parts[0])) {
            person.setFirstName(value);
        } else if ("S_NAME".equalsIgnoreCase(parts[0])) {
            person.setSecondName(value);
        } else if ("AGE".equalsIgnoreCase(parts[0])) {
            person.setAge(Long.valueOf(value));
        }
    }
}
