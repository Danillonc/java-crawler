package com.java.crawler.adapter.out.persistence;

import com.java.crawler.application.port.out.LoadCrawlerTaskPort;
import com.java.crawler.application.port.out.SaveCrawlerTaskPort;
import com.java.crawler.domain.model.CrawlerTask;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCrawlerTaskRepository implements SaveCrawlerTaskPort, LoadCrawlerTaskPort {

    private final Map<String, CrawlerTask> db = new ConcurrentHashMap<>();

    @Override
    public Optional<CrawlerTask> loadById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public void save(CrawlerTask task) {
        db.put(task.getId(), task);
    }
}
