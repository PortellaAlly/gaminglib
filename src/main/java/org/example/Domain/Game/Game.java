package org.example.Domain.Game;

public class Game {
    private Integer id;
    private String name;
    private String genre;
    private Integer rawg_id;

    public Game(Integer id){
        this.id = id;
    }

    public Game(Integer id, String name, String genre, Integer rawg_id){
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.rawg_id = rawg_id;
    }

    public Integer getId() {
        return id;
    }
}
