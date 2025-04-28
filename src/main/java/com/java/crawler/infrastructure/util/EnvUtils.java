package com.java.crawler.infrastructure.util;

public class EnvUtils {
    private EnvUtils(){}

    public static String getBaseUrl() {
        return System.getenv().getOrDefault("CRAWLER_BASE_URL", "http://hiring.axreng.com/");
    }
}
