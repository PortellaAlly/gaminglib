package org.example.Service;

import com.google.gson.Gson;
import org.example.Client.ClientHttpConfiguration;
import org.example.ConnectionFactory;

import java.io.IOException;
import java.net.http.HttpResponse;

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
        String uri = "https://api.rawg.io/api/games?key=BBBB&search=" + name;
        HttpResponse<String> response = client.dispararRequisicaoGet(uri);
        String responseBody = response.body();

        System.out.println(responseBody);
        /*
        GameRecord game = gson.fromJson(responseBody, GameRecord.class);
        System.out.println(game.name() + game.genre() + game.rawg_id());
        */
    }
}
