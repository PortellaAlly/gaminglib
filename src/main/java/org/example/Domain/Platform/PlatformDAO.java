package org.example.Domain.Platform;

import java.sql.*;

public class PlatformDAO {
    private Connection conn;

    public PlatformDAO(Connection connection){
        this.conn = connection;
    }


    public Integer addPlatform(String name) {
        String sql = "INSERT INTO platform (name) VALUES (?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);

            preparedStatement.execute();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void addPlatformRelation(Integer gameId, Integer platformId){
        String sql = "INSERT INTO platform_game (game_id, platform_id) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, platformId);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Platform verify(String name){
        String sql = "SELECT name FROM platform WHERE name = ?";
            try{
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, name);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    String platName = resultSet.getString(1);
                    return new Platform(platName);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return null;
    }
}
