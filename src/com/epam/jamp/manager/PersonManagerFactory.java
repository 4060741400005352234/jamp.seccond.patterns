package com.epam.jamp.manager;

import com.epam.jamp.factory.ManagerFactory;
import com.epam.jamp.model.Person;

public class PersonManagerFactory implements ManagerFactory {

    @Override
    public Manager<Person> getFleManager(String fileName) {
        return new FilePersonManager(fileName);
    }

    @Override
    public Manager<Person> getDBManager() {
        return new DBPersonManager();
    }
}
