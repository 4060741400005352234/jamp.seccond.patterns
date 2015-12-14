package com.epam.jamp.service.factory;

import com.epam.jamp.service.person.PersonService;

// Abstract factory pattern
public interface AbstractServiceFactory {

    PersonService createPeronService();
}
