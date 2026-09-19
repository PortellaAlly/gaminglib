package org.example.Domain.Game;

import org.example.Domain.User.User;

import java.sql.*;
import java.util.NoSuchElementException;

public class GameDAO {
    private Connection conn;

    public GameDAO(Connection connection){
        this.conn = connection;
    }

    public void addGameRelation(Integer user_id, Integer game_id){
        String sql = "INSERT INTO games_user (user_id, game_id)" + "VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, game_id);

            preparedStatement.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addGame(GameRecord.Results gameRecord){
        String sql = "INSERT INTO games (name, genre, rawg_id) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, gameRecord.name());
            try {
                preparedStatement.setString(2, gameRecord.genres().getFirst().name());
            } catch (NoSuchElementException e){
                preparedStatement.setString(2, null);
            }
            preparedStatement.setInt(3, gameRecord.id());

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

    public Game verify(Integer rawg_id) {
        String sql = "SELECT id FROM games WHERE rawg_id = ?";
         try {
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             preparedStatement.setInt(1, rawg_id);

             ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                Integer id = resultSet.getInt(1);

                return new Game(id);
            }
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

         return null;
    }
}
