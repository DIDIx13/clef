/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.esne.util;

import ch.esne.console.Controller;
import ch.esne.console.Controller.State;
import ch.esne.domain.Clef;
import ch.esne.domain.Groupe;
import ch.esne.domain.Lieu;
import ch.esne.domain.Personne;
import ch.esne.domain.Serrure;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * InstanceDB utilisant le controlleur
 *
 * @author toblerc
 */
public class InstanceDB {

    private DemoData insert;
    private Controller controllerdb;

    /**
     * *
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public InstanceDB() throws SQLException, ClassNotFoundException, IOException {
        //Initialisation des hashmap du DemoData pour avoir tous les objets crée
        insert = DemoData.getDemoData();
        this.controllerdb = new Controller(State.INIT);

    }

    /**
     * *
     * WARNING this function create all the tuples from DemoData in the DB so it
     * cannot be do twice without a clear
     */
    public void init() throws SQLException, IOException, ClassNotFoundException {
        this.insertGroupe();
        this.insertGroupeGroupe();
        this.insertLieu();
        this.insertLieuParent();
        this.insertAcces();
        this.insertClef();
        this.insertPersonne();
        this.insertSerrure();
        this.insertRegistre();
        this.insertHistorique();

    }

    /**
     * *
     *
     * @return the demoData full of data
     */
    public DemoData getInsert() {
        return insert;
    }

    /**
     * *
     * Insert the groupe from DemoData to the DB
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertGroupe() throws SQLException, IOException, ClassNotFoundException {
        //changement du status du controller
        this.controllerdb.setApplicationState(State.GESTION_GROUPE);

        HashMap<UUID, Groupe> selects = insert.getGroupes();

        for (Map.Entry<UUID, Groupe> entry : selects.entrySet()) {
            Groupe value = entry.getValue();
            this.controllerdb.addGroupe(value);
        }
    }

    /**
     * *
     * Inert the groupe groupe table
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertGroupeGroupe() throws SQLException, IOException, ClassNotFoundException {
        //changement du status du controller
        this.controllerdb.setApplicationState(State.GESTION_GROUPE);

        HashMap<UUID, Groupe> selects = insert.getGroupes();

        for (Map.Entry<UUID, Groupe> entry : selects.entrySet()) {
            Groupe value = entry.getValue();

            for (int i = 0; i < value.getAllGroupesEnfant().size(); i++) {
                this.controllerdb.addGroupeGroupe(value, value.getAllGroupesEnfant().get(i));
            }
            for (int i = 0; i < value.getAllGroupesParent().size(); i++) {
                this.controllerdb.addGroupeGroupe(value.getAllGroupesParent().get(i), value);
            }
        }
    }

    /**
     * *
     * Insert Acces
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertAcces() throws SQLException, IOException, ClassNotFoundException {
        //changement du status du controller
        this.controllerdb.setApplicationState(State.GESTION_ACCES);
        HashMap<UUID, Groupe> selects = insert.getGroupes();

        for (Map.Entry<UUID, Groupe> entry : selects.entrySet()) {
            Groupe value = entry.getValue();
            for (int i = 0; i < value.getAllAcces().size(); i++) {
                this.controllerdb.addAccesToGroupeExistant(value.getAllAcces().get(i), value);
            }
        }
    }

    /**
     * *
     * Insert Clef
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertClef() throws SQLException, IOException, ClassNotFoundException {
        //changement du status du controller
        this.controllerdb.setApplicationState(State.GESTION_CLEF);
        HashMap<String, Clef> selects = insert.getClefs();

        for (Map.Entry<String, Clef> entry : selects.entrySet()) {
            Clef value = entry.getValue();
            this.controllerdb.addClef(value);
        }
    }

    /**
     * *
     * insert historique
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertHistorique() throws SQLException, IOException, ClassNotFoundException {
        this.controllerdb.setApplicationState(State.GESTION_HISTORIQUE_UTILISATION);
        HashMap<UUID, Serrure> selects = insert.getSerrures();

        for (Map.Entry<UUID, Serrure> entry : selects.entrySet()) {
            Serrure value = entry.getValue();
            this.controllerdb.addHistoriqueUtilisation(value);
        }
    }

    /**
     * *
     * Insert Lieu
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertLieu() throws SQLException, IOException, ClassNotFoundException {
        HashMap<UUID, Lieu> selects = insert.getLieux();
        this.controllerdb.setApplicationState(State.GESTION_LIEU);
        for (Map.Entry<UUID, Lieu> entry : selects.entrySet()) {
            Lieu value = entry.getValue();
            this.controllerdb.addLieu(value);
        }
    }

    /**
     * *
     * Insert LieuParent
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertLieuParent() throws SQLException, IOException, ClassNotFoundException {
        HashMap<UUID, Lieu> selects = insert.getLieux();
        this.controllerdb.setApplicationState(State.GESTION_LIEU);
        for (Map.Entry<UUID, Lieu> entry : selects.entrySet()) {
            if (entry.getValue().getLieuParent() != null) {
                Lieu value = entry.getValue();
                this.controllerdb.addLieuParent(value);
            }
        }
    }

    /**
     * *
     * Insert personne
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertPersonne() throws SQLException, IOException, ClassNotFoundException {
        //changement du status du controller
        this.controllerdb.setApplicationState(State.GESTION_PERSONNE);
        HashMap<UUID, Personne> selects = insert.getPersonnes();

        for (Map.Entry<UUID, Personne> entry : selects.entrySet()) {
            Personne value = entry.getValue();
            this.controllerdb.addPersonne(value);
        }
    }

    /**
     * *
     * Insert Registre
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertRegistre() throws SQLException, IOException, ClassNotFoundException {
        this.controllerdb.setApplicationState(State.GESTION_REGISTRE);
        HashMap<String, Clef> selects = insert.getClefs();
        for (Map.Entry<String, Clef> entry : selects.entrySet()) {
            Clef value = entry.getValue();
            this.controllerdb.addRegistre(value);
        }
    }

    /**
     * *
     * Insert Serrure
     *
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void insertSerrure() throws SQLException, IOException, ClassNotFoundException {
        HashMap<UUID, Serrure> selects = insert.getSerrures();
        controllerdb.setApplicationState(State.GESTION_SERRURE);
        for (Map.Entry<UUID, Serrure> entry : selects.entrySet()) {
            Serrure value = entry.getValue();
            this.controllerdb.addSerrure(value);
        }
        HashMap<UUID, Lieu> lieu = insert.getLieux();
        for (Map.Entry<UUID, Lieu> entry : lieu.entrySet()) {
            Lieu value = entry.getValue();
            this.controllerdb.updateSerrureLieu(value);
        }
    }

    /**
     * *
     * WARNING this function clear completely the tuples from the DataBase
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public void clear() throws ClassNotFoundException, SQLException, IOException {
        //Get connection
        Connection source = DataSourceFactory.getPostgreSQLDataSourceDev().getConnection();
        //Create statement
        Statement statement = source.createStatement();

        //Prepare SQL for delete
        String sql = "DELETE FROM acces ;"
                + "DELETE FROM clefs;"
                + "DELETE FROM clefs_personnes;"
                + "DELETE FROM groupes;"
                + "DELETE FROM groupes_groupes;"
                + "DELETE FROM historique_utilisation;"
                + "DELETE FROM lieux;"
                + "DELETE FROM personnes;"
                + "DELETE FROM serrures;";
        //Delete
        statement.executeUpdate(sql);
        source.close();
    }

}
