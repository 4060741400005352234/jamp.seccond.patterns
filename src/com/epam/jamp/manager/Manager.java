package com.epam.jamp.manager;

import java.util.List;

public interface Manager<T> {

    public void write(T obj) throws Exception;
    public List<T> readAll();
    public List<T> read(String name);
}
