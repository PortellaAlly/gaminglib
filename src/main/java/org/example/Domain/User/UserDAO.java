package org.example.Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
