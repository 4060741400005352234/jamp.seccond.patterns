package com.epam.jamp.file.parser;

import com.epam.jamp.model.Person;

public class PersonFileItemParser extends AbstractFileItemParser<Person> {

    @Override
    protected Person getResult() {
        return new Person();
    }

    @Override
    protected void fillResult(Person result, String[] data) {
        result.setFirstName(data[0]);
        result.setSecondName(data[1]);
        result.setAge(Integer.valueOf(data[2]));
        result.setIq(Integer.valueOf(data[3]));
    }
}
