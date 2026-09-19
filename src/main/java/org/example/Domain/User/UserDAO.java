package org.example.Domain.User;

import org.example.Domain.Game.Game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public User listUser(String name){
        String sql = "SELECT * FROM user WHERE name = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                Integer user_id = resultSet.getInt(1);
                String user_name = resultSet.getString(2);
                String user_email = resultSet.getString(3);

                return new User(user_id, user_name, user_email);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Game> openLib(String user_name){
        String sql = " SELECT games.name, games_user.status, games.id AS game FROM games_user JOIN user on user.id = games_user.user_id JOIN games on games.id = games_user.game_id WHERE user.name = ?";
        List<Game> games = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, user_name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                String game = resultSet.getString(1);
                String status = resultSet.getString(2);
                Integer id = resultSet.getInt(3);

                games.add(new Game(game, status, id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return games;
    }

    public void changeStatus(Integer gameId, Integer userId, String status){
        String sql = "UPDATE games_user SET status = ? WHERE game_id = ? AND user_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setInt(3, userId);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
