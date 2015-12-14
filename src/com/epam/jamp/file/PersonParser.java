package com.epam.jamp.file;

import com.epam.jamp.model.Person;

public class PersonParser implements Parser<Person> {

    @Override
    public Person parse(String st) {
        return new PersonFileItemParser().parseItem(st);
    }
}
