/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 *  @author ToblerC <cyril.tobler@icloud.com>
 */
package ch.esne.util;

import ch.esne.domain.Groupe;
import ch.esne.domain.Personne;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
@FixMethodOrder(MethodSorters.DEFAULT)
public class DataSourceFactoryTest {

    public DataSourceFactoryTest() {
    }

    Personne instancePersonne;
    Groupe instanceGroupe;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instancePersonne = new Personne("Euille", "Jak", "Jak.Euille@rpn.ch");
        instanceGroupe = new Groupe(new UUID(0, 0), "Jak.Euille@rpn.ch", "Groupe personnel");
        instancePersonne.setGroupe(instanceGroupe);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void selectExemple() throws ClassNotFoundException, SQLException, IOException {
        System.out.println("\n\n\n\nSELECT EXEMPLE\n");
        //Create statement
        try ( //Get connection
                Connection source = DataSourceFactory.getPostgreSQLDataSource().getConnection()) {
            //Create statement
            Statement statement = source.createStatement();
            
            String sql = "SELECT * FROM clefs";
            
            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);
            
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                // Mise en variable des donnée des chaps 1 et 2
                // du tuple en cours
                String numeroserie = rs.getString(1);
                String status = rs.getString(2);
                System.out.println("--------------------");
                //impression des variables
                System.out.println("numero de serie: " + numeroserie);
                System.out.println("Status: " + status);
                //Impression sans passer par les variables
                System.out.println("Instant Création: " + rs.getString(3));
                //Impression en appelant le nom de la colonne
                System.out.println("User Création: " + rs.getString("user_creation"));
                
            }
            source.close();
        }
        
    }

    @Test
    public void SelectAlternatif() throws SQLException {
        System.out.println("\n\n\n\nSELECT avec connection alternative");
        String url = "jdbc:postgresql://localhost/clefDB";
        Properties props = new Properties();
        props.setProperty("user", "clef");
        props.setProperty("password", "clefPASS");
        try (Connection conn = DriverManager.getConnection(url, props)) {
            String sql = "SELECT p.nom, p.prenom, gd.nom as \"groupes\"\n"
                    + "FROM personnes p\n"
                    + "JOIN groupes g on p.groupes_uuid = g.uuid\n"
                    + "JOIN groupes_groupes gg on g.uuid = gg.enfant_uuid\n"
                    + "JOIN groupes gd on gg.parent_uuid = gd.uuid\n"
                    + ";";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //
                ResultSetMetaData rsmd = rs.getMetaData();
                //
                System.out.println("--------------------");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
                }
                
            }
            conn.close();
        }

    }

    @Test
    public void ConnectionCompleteRAW() throws ClassNotFoundException, SQLException, IOException {
        System.out.println("\n\n\n\nConnection complète RAW\n");
        //Create statement
        try ( //Get connection
                Connection source = DataSourceFactory.getPostgreSQLDataSource().getConnection()) {
            //Create statement
            Statement statement = source.createStatement();
            String sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                    + "FROM personnes p\n"
                    + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                    + "WHERE p.nom ilike 'Ade'\n"
                    + ";";
            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Pre-insertion");
            System.out.println("=============");
            while (rs.next()) {
                //
                ResultSetMetaData rsmd = rs.getMetaData();
                //
                System.out.println("--------------------");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
                }
            }
            System.out.println("\n\n\nInsertion");
            System.out.println("=========");
            //Create groupe
            String sql1 = "INSERT INTO groupes\n"
                    + "(uuid,\n"
                    + "nom,\n"
                    + "description)\n"
                    + "VALUES(uuid_generate_v4(),\n"
                    + "'pier.ade@rpn.ch',\n"
                    + "'Groupe personnel');";
            //Create Personne
            sql = "INSERT INTO personnes\n"
                    + "(uuid,\n"
                    + "nom, \n"
                    + "prenom,\n"
                    + "email,\n"
                    + "groupes_uuid)\n"
                    + "VALUES (uuid_generate_v4(),\n"
                    + "'Ade',\n"
                    + "'Pier',\n"
                    + "'pier.ade@rpn.ch',\n"
                    + "(SELECT uuid FROM groupes\n"
                    + "WHERE nom LIKE 'pier.ade@rpn.ch'));";
            //Execute the SQL
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql);
            
            System.out.println("\n\n\nSelect après insertion");
            System.out.println("======================");
            sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                    + "FROM personnes p\n"
                    + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                    + "WHERE p.nom ilike 'Ade'\n"
                    + ";";
            // Execute SQL statement returns as a ResultSet object.
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                //
                ResultSetMetaData rsmd = rs.getMetaData();
                //
                System.out.println("--------------------");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
                }
            }
            System.out.println("\n\n\nUpdate");
            System.out.println("======");
            sql = "UPDATE personnes\n"
                    + "SET prenom = 'Pierre'\n"
                    + "WHERE uuid IN (\n"
                    + "    SELECT uuid \n"
                    + "    FROM personnes\n"
                    + "    WHERE nom ILIKE 'ade'\n"
                    + ")\n"
                    + ";";
            statement.executeUpdate(sql);
            System.out.println("\n\n\nPost-Update");
            System.out.println("===========");
            sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                    + "FROM personnes p\n"
                    + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                    + "WHERE p.nom ilike 'Ade'\n"
                    + ";";
            // Execute SQL statement returns as a ResultSet object.
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                //
                ResultSetMetaData rsmd = rs.getMetaData();
                //
                System.out.println("--------------------");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
                }
            }
            System.out.println("\n\n\nDelete");
            System.out.println("======");
            //Prepare SQL for delete
            sql = "DELETE FROM groupes WHERE nom ILIKE 'pier.ade@rpn.ch';";
            sql1 = "DELETE FROM personnes WHERE nom ILIKE 'ade'";
            //Delete
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql);
            System.out.println("\n\n\nPost-Delete");
            System.out.println("===========");
            sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                    + "FROM personnes p\n"
                    + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                    + "WHERE p.nom ilike 'Ade'\n"
                    + ";";
            // Execute SQL statement returns as a ResultSet object.
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                //
                ResultSetMetaData rsmd = rs.getMetaData();
                //
                System.out.println("--------------------");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
                }
            }
            //Fermetur de la connection à la BD
            source.close();
        }
        
    }

    @Test
    public void ConnectionCompleteOOP() throws ClassNotFoundException, SQLException, IOException {
        System.out.println("\n\n\n\nConnection complète\n");
        //Get connection
        Connection source = DataSourceFactory.getPostgreSQLDataSource().getConnection();
        //Create statement
        Statement statement = source.createStatement();
        System.out.println("Pre-insertion");
        System.out.println("=============");
        String sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                + "FROM personnes p\n"
                + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                + "WHERE p.nom ilike '" + instancePersonne.getNom() + "'\n"
                + ";";
        // Execute SQL statement returns as a ResultSet object.
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            //
            ResultSetMetaData rsmd = rs.getMetaData();
            //
            System.out.println("--------------------");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
            }
        }

        System.out.println("\n\n\nInsertion");
        System.out.println("=========");
        //Create groupe
        String sql1 = "INSERT INTO groupes\n"
                + "(uuid,\n"
                + "nom,\n"
                + "description)\n"
                + "VALUES(uuid_generate_v4(),\n"
                + "'" + instancePersonne.getGroupe().getNom() + "',\n"
                + "' " + instancePersonne.getGroupe().getDescription() + "');";
        //Create Personne
        sql = "INSERT INTO personnes\n"
                + "(uuid,\n"
                + "nom, \n"
                + "prenom,\n"
                + "email,\n"
                + "groupes_uuid)\n"
                + "VALUES (uuid_generate_v4(),\n"
                + "'" + instancePersonne.getNom() + "',\n"
                + "'" + instancePersonne.getPrenom() + "',\n"
                + "'" + instancePersonne.getEmail() + "',\n"
                + "(SELECT uuid FROM groupes\n"
                + "WHERE nom ILIKE '" + instancePersonne.getGroupe().getNom() + "'));";
        //Execute the SQL
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql);

        System.out.println("\n\n\nSelect après insertion");
        System.out.println("======================");
        sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                + "FROM personnes p\n"
                + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                + "WHERE p.nom ilike '" + instancePersonne.getNom() + "'\n"
                + ";";
        // Execute SQL statement returns as a ResultSet object.
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            //
            ResultSetMetaData rsmd = rs.getMetaData();
            //
            System.out.println("--------------------");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
            }
        }
        instancePersonne.setPrenom("Jaque");
        System.out.println("\n\n\nUpdate");
        System.out.println("======");
        sql = "UPDATE personnes\n"
                + "SET prenom = '" + instancePersonne.getPrenom() +"'\n"
                + "WHERE uuid IN (\n"
                + "    SELECT uuid \n"
                + "    FROM personnes\n"
                + "    WHERE nom ILIKE '" + instancePersonne.getNom() +"'\n"
                + ")\n"
                + ";";
        statement.executeUpdate(sql);
        System.out.println("\n\n\nSelect après update");
        System.out.println("===================");
        sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                + "FROM personnes p\n"
                + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                + "WHERE p.nom ilike '" + instancePersonne.getNom() + "'\n"
                + ";";
        // Execute SQL statement returns as a ResultSet object.
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            //
            ResultSetMetaData rsmd = rs.getMetaData();
            //
            System.out.println("--------------------");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
            }
        }
        System.out.println("\n\n\nDelete");
        System.out.println("======");
        //Prepare SQL for delete
        sql = "DELETE FROM groupes WHERE nom ILIKE '" + instancePersonne.getGroupe().getNom() + "';";
        sql1 = "DELETE FROM personnes WHERE nom ILIKE '" + instancePersonne.getNom() + "'";
        //Delete 
        statement.executeUpdate(sql1);
        statement.executeUpdate(sql);
        System.out.println("\n\n\nPost-Delete");
        System.out.println("===========");
        sql = "SELECT p.nom, p.prenom, g.nom as \"groupes\"\n"
                + "FROM personnes p\n"
                + "INNER JOIN groupes g ON p.groupes_uuid = g.uuid\n"
                + "WHERE p.nom ilike '" + instancePersonne.getNom() + "'\n"
                + ";";
        // Execute SQL statement returns as a ResultSet object.
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            //
            ResultSetMetaData rsmd = rs.getMetaData();
            //
            System.out.println("--------------------");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i));
            }
        }
        //Fermetur de la connection à la BD
        source.close();
    }

}
