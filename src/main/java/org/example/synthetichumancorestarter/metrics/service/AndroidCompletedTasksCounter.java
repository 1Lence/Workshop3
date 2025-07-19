package org.example.synthetichumancorestarter.metrics.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AndroidCompletedTasksCounter {
    private final MeterRegistry registry;
    public final ConcurrentHashMap<String, AtomicInteger> countMap = new ConcurrentHashMap<>();

    public AndroidCompletedTasksCounter(MeterRegistry registry) {
        this.registry = registry;
    }

    public void addCompletedTask(String author) {
        countMap.computeIfAbsent(author,
                k -> new AtomicInteger(0)).incrementAndGet();

        registry.counter("completed_tasks_by_authors_counter", List.of(Tag.of("author", author))).increment();
    }
}