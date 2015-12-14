package com.epam.jamp.main;

import com.epam.jamp.service.factory.AbstractServiceFactory;
import com.epam.jamp.service.factory.ServiceType;
import com.epam.jamp.service.person.PersonService;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserConsole {

    private static Logger log = Logger.getLogger(UserConsole.class);

    private BufferedReader bufferedReader;

    public void performUserControl() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {
                showCommandMenu();
                String userInput = bufferedReader.readLine();
                if ("EXIT".equalsIgnoreCase(userInput)) {
                    System.exit(1);
                }
                ServiceType serviceType = ServiceType.getByServiceNumber(userInput);
                if (serviceType != null) {
                    PersonService personService = createService(serviceType);
                    PersonOperator operator = new PersonOperator(personService);
                    operator.imitateOperations();
                    operator.imitateIqOperations();
                } else {
                    System.out.println("Incorrect parameter!");
                }
            } finally {
                bufferedReader.close();
            }
        } catch (IOException e) {
            log.error("Error during closing resources.", e);
        }
    }

    private PersonService createService(ServiceType serviceType) {
        AbstractServiceFactory abstractServiceFactory = serviceType.getServiceFactory();
        PersonService personService = abstractServiceFactory.createPeronService();
        return personService;
    }

    private void showCommandMenu() {
        System.out.println("Choice person manager type or print 'Exit' for exit:");
        for (ServiceType serviceType : ServiceType.values()) {
            System.out.println(serviceType.getServiceNumber() + " - " + serviceType.getDescription());
        }
    }
}
