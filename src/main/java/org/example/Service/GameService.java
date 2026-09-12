package org.example.Service;

import com.google.gson.Gson;
import org.example.Client.ClientHttpConfiguration;
import org.example.ConnectionFactory;
import org.example.Domain.Game.GameDAO;
import org.example.Domain.Game.GameRecord;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Connection;

public class GameService {
    private ConnectionFactory connection;
    private ClientHttpConfiguration client;
    private Gson gson = new Gson();

    // GameRecord gameRecord = new GameRecord(name, genre, rawg_id); - jaja isso vai ser usado pra salvar um jogo no DB

    public GameService(ClientHttpConfiguration client){
        this.connection = new ConnectionFactory();
        this.client = client;
    }

    public void addGame(Integer user_id, String game_name) throws IOException, InterruptedException {
        String uri = "https://api.rawg.io/api/games?key=54dbdcc93ad64664a1003144b6ee135a&search=" + game_name;
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        String responseBody = response.body();

        GameRecord gameRecord = new Gson().fromJson(responseBody, GameRecord.class);
        var game_id = gameRecord.results().getFirst().id();

        Connection conn = connection.recuperarConexao();
        new GameDAO(conn).addGame(user_id, game_id);
        //gameRecord.results().getFirst().id()

        /*
        for(GameRecord.Results games : gameRecord.results()){
            String nameResult = games.name();
            double ratingResult = games.rating();

            System.out.println(nameResult + " - " + ratingResult);
        }*/
    }
}
