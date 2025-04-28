package com.java.crawler.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrawlerRequestDTO (
    @NotBlank(message = "Keyword is required")
    @Size(min = 4, max = 32, message = "Keyword must be between 4 and 32 characters")
    String keyword) {

}
