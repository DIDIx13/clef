/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.esne.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author ToblerC
 * @Version 1.0
 */
@SuppressWarnings("checkstyle:hideutilityclassconstructor")
public class DataSourceFactory {

    /**
     * *
     *
     * @param serverName Nom du server
     * @param portNumber port du server
     * @param databaseName nom de la base de donnée
     * @param user utilisateur
     * @param password mot de passe Connection à la BD avec Passage de parametre
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static DataSource getPostgreSQLDataSource(final String serverName,
            final int portNumber,
            final String databaseName, final String user,
            final String password) throws ClassNotFoundException,
            SQLException {
        //final int maxConnection = 10;
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setServerName(serverName);
        ds.setPortNumber(portNumber);
        ds.setDatabaseName(databaseName);
        ds.setUser(user);
        ds.setPassword(password);
        //ds.setMaxConnections(maxConnection);

        return ds;
    }

    /**
     * *
     * Connection à la BD avec fichier de parametre DB.proporties
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static DataSource getPostgreSQLDataSource() throws IOException, ClassNotFoundException,
            SQLException {

        String serverName = null;
        int portNumber = 0;
        String dataBaseName = null;
        String user = null;
        String password = null;
        // Emplacement du fichier de config
        File configFile = new File("./DB.properties");
        try {
            try (
                     FileReader reader = new FileReader(configFile)
                    ) {
                Properties props = new Properties();
                props.load(reader);

                serverName = props.getProperty("serverName");
                portNumber = Integer.parseInt(props.getProperty("portNumber"));
                dataBaseName = props.getProperty("dataBaseName");
                user = props.getProperty("user");
                password = props.getProperty("password");
            }
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }

        //final int maxConnection = 10;
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setServerName(serverName);
        ds.setPortNumber(portNumber);
        ds.setDatabaseName(dataBaseName);
        ds.setUser(user);
        ds.setPassword(password);
        //ds.setMaxConnections(maxConnection);

        return ds;
    }
        /**
     * *
     * Connection à la BD de développement avec fichier de parametre DB.proporties
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static DataSource getPostgreSQLDataSourceDev() throws IOException, ClassNotFoundException,
            SQLException {

        String serverName = null;
        int portNumber = 0;
        String dataBaseName = null;
        String user = null;
        String password = null;
        // Emplacement du fichier de config
        File configFile = new File("./DBDev.properties");
        try {
            try (
                     FileReader reader = new FileReader(configFile)
                    ) {
                Properties props = new Properties();
                props.load(reader);

                serverName = props.getProperty("serverName");
                portNumber = Integer.parseInt(props.getProperty("portNumber"));
                dataBaseName = props.getProperty("dataBaseName");
                user = props.getProperty("user");
                password = props.getProperty("password");
            }
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }

        //final int maxConnection = 10;
        PGPoolingDataSource ds = new PGPoolingDataSource();
        ds.setServerName(serverName);
        ds.setPortNumber(portNumber);
        ds.setDatabaseName(dataBaseName);
        ds.setUser(user);
        ds.setPassword(password);
        //ds.setMaxConnections(maxConnection);

        return ds;
    }

}
