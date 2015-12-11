package com.epam.jamp.manager;

import com.epam.jamp.factory.AbstractManagerFactory;

public class PersonManagerFactory implements AbstractManagerFactory {

    @Override
    public Manager getFleManager() {
        return new FilePersonManager();
    }

    @Override
    public Manager gteFileManager() {
        return new DBPersonManager();
    }
}
