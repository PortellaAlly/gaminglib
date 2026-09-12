package org.example.Service;

import com.google.gson.Gson;
import org.example.Client.ClientHttpConfiguration;
import org.example.ConnectionFactory;
import org.example.Domain.Game.Game;
import org.example.Domain.Game.GameRecord;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class GameService {
    private ConnectionFactory connection;
    private ClientHttpConfiguration client;
    private Gson gson = new Gson();

    // GameRecord gameRecord = new GameRecord(name, genre, rawg_id); - jaja isso vai ser usado pra salvar um jogo no DB

    public GameService(ClientHttpConfiguration client){
        this.connection = new ConnectionFactory();
        this.client = client;
    }

    public void searchGame(String name) throws IOException, InterruptedException {
        String uri = "https://api.rawg.io/api/games?key=54dbdcc93ad64664a1003144b6ee135a&search=" + name;
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        String responseBody = response.body();

        GameRecord gameRecord = new Gson().fromJson(responseBody, GameRecord.class);

        for(GameRecord.Results games : gameRecord.results()){
            String nameResult = games.name();

            System.out.println(nameResult);
        }
    }
}
