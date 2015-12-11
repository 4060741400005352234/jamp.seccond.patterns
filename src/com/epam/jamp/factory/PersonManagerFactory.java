package com.epam.jamp.factory;

import com.epam.jamp.manager.DBPersonManager;
import com.epam.jamp.manager.FilePersonManager;
import com.epam.jamp.manager.Manager;
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
