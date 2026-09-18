package org.example.Service;

import org.example.ConnectionFactory;
import org.example.Domain.Game.Game;
import org.example.Domain.User.User;
import org.example.Domain.User.UserDAO;

import java.sql.Connection;
import java.util.List;

public class UserService {

    private ConnectionFactory connection;

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

    public List<Game> openLib(String user_name){
        Connection conn = connection.recuperarConexao();
        return new UserDAO(conn).openLib(user_name);
    }
}
