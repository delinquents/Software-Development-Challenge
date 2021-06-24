package com.assignment.spring.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class GenericMapper {

    public <U, T> List<U> toList(List<T> atributs, Function<T, U> f) {
        List<U> results = new ArrayList<>();
        for (T atribut : atributs) {
            results.add(f.apply(atribut));
        }
        return results;
    }
}
