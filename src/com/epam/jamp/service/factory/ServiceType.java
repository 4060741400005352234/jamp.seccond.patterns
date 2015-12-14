package com.epam.jamp.service.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ServiceType implements ServiceFactory {

    DB("1", "Database specified") {
        @Override
        public AbstractServiceFactory getServiceFactory() {
            return new DBServiceFactory();
        }
    },
    FILE("2", "File specified") {
        @Override
        public AbstractServiceFactory getServiceFactory() {
            return new FileServiceFactory();
        }
    };

    private static final Map<String, ServiceType> serviceTypeMap = Collections.unmodifiableMap(initializeServiceTypeMap());

    private String serviceNumber;
    private String description;

    private ServiceType(String serviceNumber, String description) {
        this.serviceNumber = serviceNumber;
        this.description = description;
    }

    public static ServiceType getByServiceNumber(String serviceNumber) {
        return serviceTypeMap.get(serviceNumber);
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public String getDescription() {
        return description;
    }

    private static Map<String, ServiceType> initializeServiceTypeMap() {
        Map<String, ServiceType> serviceTypeMap = new HashMap<String, ServiceType>();
        for (ServiceType type : ServiceType.values()) {
            serviceTypeMap.put(type.serviceNumber, type);
        }
        return serviceTypeMap;
    }
}
