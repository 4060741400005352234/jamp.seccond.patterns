package com.epam.jamp.service.person;

import com.epam.jamp.file.parser.PersonParser;
import com.epam.jamp.model.Person;
import com.epam.jamp.file.reader.FileItemReader;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePersonService implements PersonService {

    private static Logger log = Logger.getLogger(FilePersonService.class);

    private File file;

    public FilePersonService() {
        initFileStorage();
    }

    @Override
    public void write(Person person) {
        // TODO: refactoring with validate method
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.println(person.toString());
            out.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public void rewrite(Person person) {
        if (person == null) {
            throw new RuntimeException("Incorrect parameter.");
        }
        try {
            List<Person> persons = readAll();
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
            for (Person currentPerson : persons) {
                if (person.equals(currentPerson)) {
                    currentPerson = person;
                    out.println(currentPerson.toString());
                } else {
                    out.println(currentPerson.toString());
                }
            }
            out.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public List<Person> readAll() {
        List<Person> result = new ArrayList<Person>();
        List<Person> items;
        FileItemReader<Person> reader = null;
        try {
            reader = new FileItemReader<Person>(file, new PersonParser());
            while ((items = reader.readLines(5)).size() != 0) {
                result.addAll(items);
            }
        } catch (Exception e) {
            log.error(e);
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
            while ((items = reader.readLines(1)).size() != 0) {
                for (Person p : items) {
                    if (name.equalsIgnoreCase(p.getFirstName())) {
                        result.add(p);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }

    private void initFileStorage() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                System.out.println("Input file name (like C:/Person.txt):");
                String fileName = bufferedReader.readLine();
                file = createFileIfNotExists(fileName);
            } finally {
                bufferedReader.close();
            }
        } catch (IOException e) {
            log.error("Error during creating of file manager for person.", e);
        }
    }

    private File createFileIfNotExists(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
