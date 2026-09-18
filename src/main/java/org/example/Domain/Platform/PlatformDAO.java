package org.example.Domain.Platform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlatformDAO {
    private Connection conn;

    public PlatformDAO(Connection connection){
        this.conn = connection;
    }


    public void addPlatform(String name) {
        String sql = "INSERT INTO platform (name) VALUES (?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, name);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlatformRelation(Integer gameId, Integer platformId){
        String sql = "INSERT INTO platform_games (game_id, platform_id) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, platformId);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
