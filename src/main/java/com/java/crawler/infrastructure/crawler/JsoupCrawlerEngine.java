package com.java.crawler.infrastructure.crawler;

import com.java.crawler.domain.model.CrawlerTask;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class JsoupCrawlerEngine {

    @Async
    public void crawlerAsync(CrawlerTask task, String baseUrl) throws IOException {
        Set<String> visited = new HashSet<>();
        crawler(task, baseUrl, baseUrl, visited);
    }

    private void crawler(CrawlerTask task, String url, String baseUrl, Set<String> visited) throws IOException {
        if(visited.contains(url) || !url.startsWith(baseUrl)) return;
        visited.add(url);

        try {
            Document doc = Jsoup.connect(url).get();
            String html = doc.html();

            if(html.toLowerCase().contains(task.getKeyword().toLowerCase())) {
                task.getFoundUrls().add(url);
            }

            Elements links = doc.select("a[href]");
            for(Element link : links) {
                String href = link.absUrl("href");
                crawler(task, href, baseUrl, visited);
            }
        } catch(IOException ignored) {
            log.error("Error on crawler engine:", ignored);
            throw new IOException(ignored.getMessage());
        }
    }
}
