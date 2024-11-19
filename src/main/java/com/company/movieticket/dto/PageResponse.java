package com.company.movieticket.dto;

import java.util.function.Function;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@RequiredArgsConstructor
public class PageResponse<T> {
    private final List<T> elements;
    private final long totalElements;
    private final long totalPages;

    public static <T> PageResponse<T> of(Page<T> page) {
        return new PageResponse<>(page.getContent(), page.getTotalElements(), page.getTotalPages());
    }
}
