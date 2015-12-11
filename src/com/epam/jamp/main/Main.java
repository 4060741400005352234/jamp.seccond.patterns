package com.epam.jamp.main;

import com.epam.jamp.factory.ManagerFactory;
import com.epam.jamp.manager.Manager;
import com.epam.jamp.manager.PersonManagerFactory;
import com.epam.jamp.model.Person;

public class Main {

    public static void main(String[] args) throws Exception {

        Person person = new Person("John", "Sidorov", 25);

        ManagerFactory factory = new PersonManagerFactory();

        Manager<Person> dbManager = factory.getDBManager();
        dbManager.write(person);

        Manager<Person> fileManager = factory.getFleManager();
        //fileManager.write(person);
        Person newPerson = fileManager.read("Martin");
        System.out.println("New person name - " + newPerson.getFirstName());

    }



//    try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("myfile.txt", true)))) {
//        out.println("the text");
//        //more code
//        out.println("more text");
//        //more code
//    }catch (IOException e) {
//        //exception handling left as an exercise for the reader
//    }
}
