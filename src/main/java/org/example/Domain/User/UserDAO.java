package org.example.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection connection) {
        this.conn = connection;
    }

    public void register(String name, String email) {
        String sql = "INSERT INTO user (name, email) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
