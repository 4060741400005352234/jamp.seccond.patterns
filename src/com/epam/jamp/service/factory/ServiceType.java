package com.epam.jamp.service.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Factory pattern
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

    // Using map is more quicker than regular foreach style (just for fun!)
    // Benchmark                       Mode  Cnt       Score      Error  Units
    // Benchmark.getSTFrom     thrpt   20  365355.283 ? 6640.406  ops/s
    // Benchmark.getSTFromMap  thrpt   20  375165.015 ? 7256.233  ops/s
    //
    // For enum with 20 items
    // Benchmark                       Mode  Cnt       Score      Error  Units
    // JMHSortBenchmark.getSTFrom     thrpt   20  139567.666 ? 1642.406  ops/s
    // JMHSortBenchmark.getSTFromMap  thrpt   20  193020.907 ? 3546.147  ops/s
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
