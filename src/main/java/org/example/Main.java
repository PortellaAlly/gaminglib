package org.example;

import org.example.Client.ClientHttpConfiguration;
import org.example.Service.GameService;
import org.example.Service.PlatformService;
import org.example.Service.UserService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static ClientHttpConfiguration client = new ClientHttpConfiguration();
    private static Scanner scanner = new Scanner(System.in);
    private static PlatformService platformService = new PlatformService();
    private static UserService userService = new UserService();
    private static GameService gameService = new GameService(platformService, client);

    public static void main(String[] args) throws IOException, InterruptedException {
        int decisao = 0;

        System.out.println("""
                ** ========================
                || 1 - Cadastrar uma conta
                || 2 - Adicionar um jogo
                || 3 - Abrir a biblioteca
                ||
                ||
                ** ========================
                """);
        decisao = scanner.nextInt();

        switch (decisao){
            case 1:
                registerAccount();
                break;
            case 2:
                addGame();
                break;
            case 3:
                openLib();
            default:
                System.out.println("bleh");
        }
    }

    private static void registerAccount(){
        System.out.println("Digite o nome:");
        var name = scanner.next();

        System.out.println("Digite o email:");
        var email = scanner.next();

        userService.register(name, email);
    }

    private static void addGame() throws IOException, InterruptedException {
        System.out.println("Digite seu nome de usuario:");
        var user_name = scanner.next();
        var user = userService.listUser(user_name);

        System.out.println("Digite o nome do jogo:");
        var game_name = scanner.next();

        gameService.addGame(user.getId() ,game_name);
        System.out.println("jogo adicionado a biblioteca!");
    }

    private static void openLib(){
        System.out.println("Digite seu nome de usuario:");
        var user_name = scanner.next();
        var user = userService.listUser(user_name).getName();

        var gameid = userService.openLib(user);

        System.out.println("Deseja mudar o status de algum jogo?(Y/N)");
        var decision = scanner.next().toUpperCase();
        if(decision.equals("Y")){
            userService.changeStatus(gameid, user_name);
        }
    }
}