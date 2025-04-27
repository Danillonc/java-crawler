package com.java.crawler.application.port.in;

import com.java.crawler.domain.model.CrawlerTask;

public interface GetCrawlerResultQuery {
    CrawlerTask getCrawlerTask(String id);
}
