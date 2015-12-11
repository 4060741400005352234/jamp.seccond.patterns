package com.epam.jamp.manager;

import com.epam.jamp.factory.ManagerFactory;

public class PersonManagerFactory implements ManagerFactory {

    @Override
    public Manager getFleManager() {
        return new FilePersonManager();
    }

    @Override
    public Manager getDBManager() {
        return new DBPersonManager();
    }
}
