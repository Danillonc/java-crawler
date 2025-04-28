package com.java.crawler.adapter.in.web;

import com.java.crawler.adapter.in.web.dto.CrawlerRequestDTO;
import com.java.crawler.adapter.in.web.dto.CrawlerResponseDTO;
import com.java.crawler.application.port.in.GetCrawlerResultQuery;
import com.java.crawler.application.port.in.StartCrawlerUseCase;
import com.java.crawler.domain.model.CrawlerTask;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CrawlerController {

    private final StartCrawlerUseCase startCrawlerUseCase;
    private final GetCrawlerResultQuery getCrawlerResultQuery;

    public CrawlerController(StartCrawlerUseCase startCrawlerUseCase, GetCrawlerResultQuery getCrawlerResultQuery) {
        this.startCrawlerUseCase = startCrawlerUseCase;
        this.getCrawlerResultQuery = getCrawlerResultQuery;
    }

    @PostMapping("/crawler")
    public ResponseEntity<Map<String, String>> startCrawler(@Valid @RequestBody CrawlerRequestDTO request) {
        String id = this.startCrawlerUseCase.startSearch(request.keyword());
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrawlerResponseDTO> get(@PathVariable String id) {
        CrawlerTask task = getCrawlerResultQuery.getCrawlerTask(id);
        return ResponseEntity.ok(new CrawlerResponseDTO(task));
    }



}
