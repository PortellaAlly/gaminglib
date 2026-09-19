package org.example.Service;

import org.example.ConnectionFactory;
import org.example.Domain.Game.Game;
import org.example.Domain.User.User;
import org.example.Domain.User.UserDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private ConnectionFactory connection;
    private Scanner scanner = new Scanner(System.in);

    public UserService(){
        this.connection = new ConnectionFactory();
    }

    public void register(String name, String email){
        Connection conn = connection.recuperarConexao();
        new UserDAO(conn).register(name, email);
    }

    public User listUser(String name){
        Connection conn = connection.recuperarConexao();
        return new UserDAO(conn).listUser(name);
    }

    public void changeStatus(Integer gameId, String user_name){
        var userInfo = listUser(user_name);
        String status = "";
        Connection conn = connection.recuperarConexao();

        System.out.println("1. NUNCA JOGUEI | 2. JOGANDO | 3. ZEREI");
        var c = scanner.nextInt();
        switch (c){
            case 1:
                status = "NUNCA JOGUEI";
                break;
            case 2:
                status = "JOGANDO";
                break;
            case 3:
                status = "ZEREI";
                break;
            default:
                System.out.println("bleh");
        }

        new UserDAO(conn).changeStatus(gameId, userInfo.getId(), status);
    }

    public Integer openLib(String user_name){
        int i = 0;
        Connection conn = connection.recuperarConexao();
        List<Game> gamesList = new UserDAO(conn).openLib(user_name);

        for (Game gameList : gamesList){
            String name = gameList.getName();
            String status = gameList.getStatus();

            i++;
            System.out.println(i+" | "+name+" | "+status);
        }
        System.out.println("Selecione um jogo: ");
        int gameChosen = scanner.nextInt();
        int index = gameChosen - 1;

        Integer gameId = gamesList.get(index).getId();

        return gameId;
    }
}
