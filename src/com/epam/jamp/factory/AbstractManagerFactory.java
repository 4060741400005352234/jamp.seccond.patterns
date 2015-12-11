package com.epam.jamp.factory;

import com.epam.jamp.manager.Manager;

public interface AbstractManagerFactory {

    public Manager getFleManager();
    public Manager gteFileManager();
}
