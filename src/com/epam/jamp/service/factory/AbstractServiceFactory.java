package com.epam.jamp.service.factory;

import com.epam.jamp.service.person.PersonService;

public interface AbstractServiceFactory {

    PersonService createPeronService();
}
