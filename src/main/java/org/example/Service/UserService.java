package org.example.Service;

import org.example.ConnectionFactory;
import org.example.Domain.User.UserDAO;

import java.sql.Connection;

public class UserService {

    private ConnectionFactory connection;

    public UserService(){
        this.connection = new ConnectionFactory();
    }

    public void register(String name, String email){
        Connection conn = connection.recuperarConexao();
        new UserDAO(conn).register(name, email);
    }
}
