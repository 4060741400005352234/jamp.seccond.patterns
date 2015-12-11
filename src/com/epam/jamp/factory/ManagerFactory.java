package com.epam.jamp.factory;

import com.epam.jamp.manager.Manager;

public interface ManagerFactory {

    public Manager getFleManager();
    public Manager getDBManager();
}
