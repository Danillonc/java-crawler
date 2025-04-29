package com.java.crawler.adapter.in.web;

import com.java.crawler.adapter.in.web.dto.CrawlerRequestDTO;
import com.java.crawler.adapter.in.web.dto.CrawlerResponseDTO;
import com.java.crawler.application.port.in.GetCrawlerServiceResultQuery;
import com.java.crawler.application.port.in.StartCrawlerServiceUseCase;
import com.java.crawler.domain.model.CrawlerTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CrawlerController {

    private final StartCrawlerServiceUseCase startCrawlerServiceUseCase;
    private final GetCrawlerServiceResultQuery getCrawlerServiceResultQuery;

    public CrawlerController(StartCrawlerServiceUseCase startCrawlerServiceUseCase, GetCrawlerServiceResultQuery getCrawlerServiceResultQuery) {
        this.startCrawlerServiceUseCase = startCrawlerServiceUseCase;
        this.getCrawlerServiceResultQuery = getCrawlerServiceResultQuery;
    }

    @Operation(summary = "Start crawler search", description = "Start the crawler to search the keyword provided in request.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Crawler executed",
                    content = @Content(
                            schema = @Schema(implementation = CrawlerRequestDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @PostMapping("/crawler")
    public ResponseEntity<Map<String, String>> startCrawler(@Valid @RequestBody CrawlerRequestDTO request) {
        String id = this.startCrawlerServiceUseCase.startSearch(request.keyword());
        return ResponseEntity.ok(Map.of("id", id));
    }


    @Operation(summary = "Get keyword data by ID.", description = "Get the keyword by ID searched by crawler.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Keyword found",
                    content = @Content(
                            schema = @Schema(implementation = CrawlerRequestDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Keyword not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<CrawlerResponseDTO> get(@PathVariable String id) {
        CrawlerTask task = getCrawlerServiceResultQuery.getCrawlerTask(id);
        return ResponseEntity.ok(new CrawlerResponseDTO(task));
    }



}
