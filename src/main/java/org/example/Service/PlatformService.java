package org.example.Service;

import org.example.ConnectionFactory;
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
}
