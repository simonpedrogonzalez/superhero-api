package com.example.demo.utils;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ContentResponse<T> {
    public List<T> content;
}
