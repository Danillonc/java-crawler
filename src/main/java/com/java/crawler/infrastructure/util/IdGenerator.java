package com.java.crawler.infrastructure.util;

import java.util.UUID;

public class IdGenerator {
    private IdGenerator(){}

    public static String generateId(){
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
