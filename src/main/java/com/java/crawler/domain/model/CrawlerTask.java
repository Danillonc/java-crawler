package com.java.crawler.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@AllArgsConstructor
public class CrawlerTask {
    private final String id;
    private final String keyword;
    private final Set<String> foundUrls = ConcurrentHashMap.newKeySet();
    private volatile String status = "active";
}
