package org.example.Service;

import com.google.gson.Gson;
import org.example.Client.ClientHttpConfiguration;
import org.example.ConnectionFactory;
import org.example.Domain.Game.Game;
import org.example.Domain.Game.GameDAO;
import org.example.Domain.Game.GameRecord;
import org.example.Domain.Platform.PlatformDAO;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameService {
    private PlatformService platformService;
    private ConnectionFactory connection;
    private ClientHttpConfiguration client;
    private Gson gson = new Gson();
    private Scanner scanner = new Scanner(System.in);

    public GameService(PlatformService platformService, ClientHttpConfiguration client){
        this.platformService = platformService;
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
            String genre;
            try {
                genre = gameRecord1.genres().getFirst().name();
            } catch (NoSuchElementException e) {
                genre = null;
            }
                    i++;
            System.out.println(i + " - " + name + " | " + genre);
        }

        System.out.print("Selecione seu jogo: ");
        int gamechosen = scanner.nextInt();
        int indexList = gamechosen - 1;

        i = 0;

        GameRecord.Results gameSelected = games.get(indexList);
        List<GameRecord.Results.Platforms> platform = gameSelected.platforms();

        Connection conn = connection.recuperarConexao();

        var verify = verifyGameExists(gameSelected.id());
        Integer game_id;
        if(verify == null){
            game_id = new GameDAO(conn).addGame(gameSelected);
        } else{
            game_id = verify.getId();
        }
        new GameDAO(conn).addGameRelation(user_id, game_id);

        for(GameRecord.Results.Platforms platformList : platform){
            String name = platformList.platform().name();
            Integer platform_id;
            var verifyPlatform = platformService.verifyPlatform(name);
            if(verifyPlatform == null){
                platform_id = new PlatformDAO(conn).addPlatform(name);
            } else {
                platform_id = verifyPlatform.getId();
            }
            new PlatformDAO(conn).addPlatformRelation(game_id, platform_id);
        }
    }

    public Game verifyGameExists(Integer rawgid){
        Connection conn = connection.recuperarConexao();
        return new GameDAO(conn).verify(rawgid);
    }
}
