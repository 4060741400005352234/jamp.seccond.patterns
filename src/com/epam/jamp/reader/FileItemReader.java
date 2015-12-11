package com.epam.jamp.reader;

import com.epam.jamp.parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileItemReader<T> {

    private final BufferedReader reader;
    private final Parser<T> parser;


    public FileItemReader(File file, Parser<T> parser) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(file));
        this.parser = parser;
    }

    public List<T> readN(int n) throws IOException {
        List<T> list = new ArrayList<T>(n);
        while (list.size() < n) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            T obj = parser.parse(line);
            list.add(obj);
        }
        return list;
    }

    public void close() {
        try {
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
