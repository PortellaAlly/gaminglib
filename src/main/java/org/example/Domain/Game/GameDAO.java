package org.example.Domain.Game;

import org.example.Domain.User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameDAO {
    private Connection conn;

    public GameDAO(Connection connection){
        this.conn = connection;
    }

    public void addGame(Integer user_id, Integer game_id){
        String sql = "INSERT INTO games_user (user_id, game_id)" + "VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, game_id);

            preparedStatement.execute();
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
