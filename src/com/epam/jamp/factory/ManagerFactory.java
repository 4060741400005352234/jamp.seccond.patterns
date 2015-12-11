package com.epam.jamp.factory;

import com.epam.jamp.manager.Manager;

public interface ManagerFactory {

    public Manager getFleManager(String fileName);
    public Manager getDBManager();
}
