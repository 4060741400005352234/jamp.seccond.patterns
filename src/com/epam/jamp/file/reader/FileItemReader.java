package com.epam.jamp.file.reader;

import com.epam.jamp.file.parser.Parser;

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

    public List<T> readLines(int lineCount) throws IOException {
        List<T> list = new ArrayList<T>(lineCount);
        while (list.size() < lineCount) {
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
