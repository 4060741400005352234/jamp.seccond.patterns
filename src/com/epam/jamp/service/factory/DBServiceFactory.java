package com.epam.jamp.service.factory;

import com.epam.jamp.service.person.DBPersonService;
import com.epam.jamp.service.person.PersonService;

public class DBServiceFactory implements AbstractServiceFactory {

    @Override
    public PersonService createPeronService() {
        return new DBPersonService();
    }
}
