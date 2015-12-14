package com.epam.jamp.service.factory;

import com.epam.jamp.service.person.FilePersonService;
import com.epam.jamp.service.person.PersonService;

public class FileServiceFactory implements AbstractServiceFactory {

    @Override
    public PersonService createPeronService() {
        return new FilePersonService();
    }
}
