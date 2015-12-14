package com.epam.jamp.file.parser;

// Template method pattern
public abstract class AbstractFileItemParser<T> {

    public T parseItem(String line) {
        if (line == null) {
            return null;
        }
        T result = getResult();
        String[] data = line.split(",");
        if (data.length == 0) {
            return null;
        }
        fillResult(result, data);
        return result;
    }

    protected abstract T getResult();
    protected abstract void fillResult(T result, String[] data);
}
