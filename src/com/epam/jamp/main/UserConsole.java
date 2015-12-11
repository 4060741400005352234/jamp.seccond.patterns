package com.epam.jamp.main;

import com.epam.jamp.manager.Manager;
import com.epam.jamp.factory.PersonManagerFactory;
import com.epam.jamp.model.Person;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserConsole {

    private static Logger log = Logger.getLogger(UserConsole.class);

    private static BufferedReader bufferedReader;

    public void performUserControl() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            showCommandMenu();
            String command = bufferedReader.readLine();
            ManagerType managerType = ManagerType.resolveCommand(command);
            Manager<Person> manager = createManager(managerType);
            PersonOperator operator = new PersonOperator(manager);
            operator.imitateOperations();
        } catch (Exception e) {
            log.error("Error occur.", e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                log.error("Error during closing resources.", e);
            }
        }

    }

    private Manager createManager(ManagerType managerType) throws IOException {
        Manager<Person> manager = null;
        switch (managerType) {
            case DB:
                manager = new PersonManagerFactory().getDBManager();
                break;
            case FILE:
                System.out.println("Input file name (like C:/Person.txt):");
                String fileName = bufferedReader.readLine();
                manager = new PersonManagerFactory().getFleManager(createFileIfNotExists(fileName));
                break;
            case EXIT:
                System.exit(1);
        }
        return manager;
    }

    private File createFileIfNotExists(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    private void showCommandMenu() {
        System.out.println("Choice person manager type:");
        for (ManagerType command : ManagerType.values()) {
            System.out.println(command.commandNumber + " - " + command.commandDescription);
        }
    }

    private enum ManagerType {
        DB("1", "Database specified"),
        FILE("2", "File specified"),
        EXIT("3", "Exit");

        private String commandNumber;
        private String commandDescription;

        private ManagerType(String commandNumber, String commandDescription) {
            this.commandNumber = commandNumber;
            this.commandDescription = commandDescription;
        }

        public static ManagerType resolveCommand(String command) {
            for (ManagerType type : ManagerType.values()) {
                if (type.commandNumber.equals(command.trim())) {
                    return type;
                }
            }
            return null;
        }
    }
}
