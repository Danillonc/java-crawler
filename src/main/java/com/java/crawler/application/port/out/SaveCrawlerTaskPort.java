package com.java.crawler.application.port.out;

import com.java.crawler.domain.model.CrawlerTask;

public interface SaveCrawlerTaskPort {
    void save(CrawlerTask task);
}
