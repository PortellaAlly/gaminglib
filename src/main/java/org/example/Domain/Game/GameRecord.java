package org.example.Domain.Game;

import java.util.List;

public record GameRecord(
        Integer count,
        List<Results> results
) {
    public record Results(
            String name,
            double rating,
            Integer id
    ) {
        public record Genre(
                String name
        ) {
        }
    }
}