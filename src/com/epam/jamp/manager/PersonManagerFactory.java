package com.epam.jamp.manager;

import com.epam.jamp.factory.ManagerFactory;
import com.epam.jamp.model.Person;

public class PersonManagerFactory implements ManagerFactory {

    @Override
    public Manager<Person> getFleManager() {
        return new FilePersonManager();
    }

    @Override
    public Manager<Person> getDBManager() {
        return new DBPersonManager();
    }
}
