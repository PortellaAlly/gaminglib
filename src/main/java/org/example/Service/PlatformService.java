package org.example.Service;

import org.example.ConnectionFactory;
import org.example.Domain.Game.GameRecord;
import org.example.Domain.Platform.Platform;
import org.example.Domain.Platform.PlatformDAO;

import java.sql.Connection;

public class PlatformService {
    private ConnectionFactory connection;

    public PlatformService(){
        this.connection = new ConnectionFactory();
    }

    public void addPlatform(String platformName){
        Connection conn = connection.recuperarConexao();
        new PlatformDAO(conn).addPlatform(platformName);
    }

    public void addPlatformGameRelation(Integer gameId, Integer platformId){
        Connection conn = connection.recuperarConexao();
        new PlatformDAO(conn).addPlatformRelation(gameId, platformId);
    }

    public Platform verifyPlatform(String name){
        Connection conn = connection.recuperarConexao();
        return new PlatformDAO(conn).verify(name);
    }
}
