package com.epam.jamp.factory;

import com.epam.jamp.manager.Manager;

import java.io.File;

public interface ManagerFactory {

    public Manager getFleManager(File file);
    public Manager getDBManager();
}
