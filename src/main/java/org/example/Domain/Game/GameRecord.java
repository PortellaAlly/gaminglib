package org.example.Domain.Game;

import org.example.Domain.Platform.Platform;

import java.util.List;

public record GameRecord(
        Integer count,
        List<Results> results
) {
    public record Results(
            String name,
            double rating,
            Integer id,
            List<Genres> genres,
            List<Platforms> platforms
    ) {
        public record Genres(
                String name
        ) {
        }
        public record Platforms(
                Platform platform
        ){
            public record Platform(
               String name
            ) {
            }
        }
    }
}