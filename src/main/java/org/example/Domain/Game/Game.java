package org.example.Domain.Game;

public class Game {
    private Integer id;
    private String name;
    private String status;
    private String genre;
    private Integer rawg_id;

    public Game(Integer id){
        this.id = id;
    }

    public Game(String name, String status, Integer id){
        this.name = name;
        this.status = status;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }
}
