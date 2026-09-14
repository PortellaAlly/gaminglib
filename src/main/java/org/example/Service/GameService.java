package org.example.Service;

import com.google.gson.Gson;
import org.example.Client.ClientHttpConfiguration;
import org.example.ConnectionFactory;
import org.example.Domain.Game.Game;
import org.example.Domain.Game.GameDAO;
import org.example.Domain.Game.GameRecord;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameService {
    private ConnectionFactory connection;
    private ClientHttpConfiguration client;
    private Gson gson = new Gson();
    private Scanner scanner = new Scanner(System.in);

    public GameService(ClientHttpConfiguration client){
        this.connection = new ConnectionFactory();
        this.client = client;
    }

    public void addGame(Integer user_id, String game_name) throws IOException, InterruptedException {
        int i = 0;
        String uri = "https://api.rawg.io/api/games?key=54dbdcc93ad64664a1003144b6ee135a&search=" + game_name;
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        String responseBody = response.body();

        GameRecord gameRecord = new Gson().fromJson(responseBody, GameRecord.class);

        List<GameRecord.Results> games = gameRecord.results();

        System.out.println("Jogos disponiveis: ");
        for (GameRecord.Results gameRecord1 : games){
            String name = gameRecord1.name();

            i++;
            System.out.println(i + " - " + name);
        }

        System.out.print("Selecione seu jogo: ");
        int gamechosen = scanner.nextInt();
        int indexList = gamechosen - 1;

        GameRecord.Results gameSelected = games.get(indexList);

        Connection conn = connection.recuperarConexao();
        if(verifyExists(gameSelected.id()) == null){
            Integer game_id = new GameDAO(conn).addGame(gameSelected);
            new GameDAO(conn).addGameRelation(user_id, game_id);
        } else{
            Integer game_id = verifyExists(gameSelected.id()).getId();
            new GameDAO(conn).addGameRelation(user_id, game_id);
        }
    }

    public Game verifyExists(Integer rawgid){
        Connection conn = connection.recuperarConexao();
        return new GameDAO(conn).verify(rawgid);
    }
}
