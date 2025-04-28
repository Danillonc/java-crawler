package com.java.crawler.adapter.in.web.dto;

import com.java.crawler.domain.model.CrawlerTask;

import java.util.Set;

public record CrawlerResponseDTO (
    String id,
    String status,
    Set<String> urls) {

    public CrawlerResponseDTO(CrawlerTask task) {
        this(task.getId(), task.getStatus(), task.getFoundUrls());
    }
}
