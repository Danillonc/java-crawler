package com.java.crawler.application.port.out;

import com.java.crawler.domain.model.CrawlerTask;

import java.util.Optional;

public interface LoadCrawlerTaskPort {
    Optional<CrawlerTask> loadById(String id);
}
