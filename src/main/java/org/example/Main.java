package org.example;

import org.example.Domain.User.User;
import org.example.Service.UserService;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        int decisao = 0;

        System.out.println("""
                **
                || 1 - Cadastrar uma conta
                ||
                ||
                ||
                ||
                **
                """);
        decisao = scanner.nextInt();

        switch (decisao){
            case 1:
                cadastrarConta();
                break;
            default:
                System.out.println("bleh");
        }
    }

    private static void cadastrarConta(){
        System.out.println("Digite o nome:");
        var name = scanner.next();

        System.out.println("Digite o email:");
        var email = scanner.next();

        userService.cadastrar(name, email);
    }
}