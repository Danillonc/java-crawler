package com.java.crawler.application.service;

import com.java.crawler.application.port.out.LoadCrawlerTaskPort;
import com.java.crawler.application.port.out.SaveCrawlerTaskPort;
import com.java.crawler.domain.model.CrawlerTask;
import com.java.crawler.infrastructure.crawler.JsoupCrawlerEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrawlerServiceImplTest {

    @Mock
    private SaveCrawlerTaskPort savePort;

    @Mock
    private LoadCrawlerTaskPort loadPort;

    @Mock
    private JsoupCrawlerEngine crawler;

    @InjectMocks
    private CrawlerServiceImpl crawlerService;

    @Test
    public void getCrawlerTask_Success() {
        CrawlerTask crawler = new CrawlerTask("hjsa789", "security");

        when(this.loadPort.loadById(anyString())).thenReturn(Optional.of(crawler));

        this.crawlerService.getCrawlerTask("hjsa789");

        verify(this.loadPort, times(1)).loadById(anyString());
    }

    @Test
    public void getCrawlerTask_Exception() {
        CrawlerTask crawler = new CrawlerTask("hjsa789", "security");

        when(this.loadPort.loadById(anyString())).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> this.crawlerService.getCrawlerTask(anyString()));
    }

    @Test
    public void startSearch_Success() throws IOException {
        String keyword = "security";

        this.crawlerService.startSearch(keyword);

        verify(this.savePort, times(1)).save(any(CrawlerTask.class));
        verify(this.crawler, times(1)).crawlerAsync(any(CrawlerTask.class), anyString());
    }

    @Test
    public void startSearch_Exception() throws IOException {

        doThrow(new IOException()).when(this.crawler).crawlerAsync(any(CrawlerTask.class), anyString());

        assertThrows(RuntimeException.class, () -> this.crawlerService.startSearch(anyString()));
    }


}
