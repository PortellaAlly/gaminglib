package org.example.Domain.Game;

public class Game {
    private Integer id;
    private String name;
    private String genre;
    private Integer rawg_id;

    public Game(Integer id){
        this.id = id;
    }

    public Game(String name){
        this.name = name;
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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Game: " + getName();
    }
}
