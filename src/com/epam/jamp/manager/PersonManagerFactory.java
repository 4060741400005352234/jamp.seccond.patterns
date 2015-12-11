package com.epam.jamp.manager;

import com.epam.jamp.factory.ManagerFactory;
import com.epam.jamp.model.Person;

import java.io.File;

public class PersonManagerFactory implements ManagerFactory {

    @Override
    public Manager<Person> getFleManager(File file) {
        return new FilePersonManager(file);
    }

    @Override
    public Manager<Person> getDBManager() {
        return new DBPersonManager();
    }
}
