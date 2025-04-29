package com.java.crawler.application.service;

import com.java.crawler.application.port.in.GetCrawlerServiceResultQuery;
import com.java.crawler.application.port.in.StartCrawlerServiceUseCase;
import com.java.crawler.application.port.out.LoadCrawlerTaskPort;
import com.java.crawler.application.port.out.SaveCrawlerTaskPort;
import com.java.crawler.domain.model.CrawlerTask;
import com.java.crawler.infrastructure.crawler.JsoupCrawlerEngine;
import com.java.crawler.infrastructure.util.EnvUtils;
import com.java.crawler.infrastructure.util.IdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CrawlerServiceImpl implements StartCrawlerServiceUseCase, GetCrawlerServiceResultQuery {

    private final SaveCrawlerTaskPort savePort;
    private final LoadCrawlerTaskPort loadPort;
    private final JsoupCrawlerEngine crawler;

    @Override
    public CrawlerTask getCrawlerTask(String id) {
        return this.loadPort.loadById(id).orElseThrow(() -> new NoSuchElementException("Task not found!"));
    }

    @Override
    public String startSearch(String keyword) {
        String id = IdGenerator.generateId();
        CrawlerTask crawlerTask = new CrawlerTask(id, keyword);
        this.savePort.save(crawlerTask);
        this.crawler.crawlerAsync(crawlerTask, EnvUtils.getBaseUrl());
        return id;
    }
}
