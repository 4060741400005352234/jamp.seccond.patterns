package com.epam.jamp.manager;

import java.util.List;

public interface Manager<T> {

    public void write(T person);
    public List<T> readAll();
    public T read(String name);
}
