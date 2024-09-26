package ch.esne.console;

import ch.esne.domain.Acces;
import ch.esne.domain.Clef;
import ch.esne.domain.Groupe;
import ch.esne.domain.Lieu;
import ch.esne.domain.Personne;
import ch.esne.domain.Serrure;

import ch.esne.util.DataSourceFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import javax.sql.DataSource;

public class Controller {

    private ArrayList<Clef> clefs;
    private State applicationState;
    private DataSource ds;
    private Connection connectionDB;
    private Statement statementDB;
    private Clef clefDB;
    private Groupe groupeDB;
    private Lieu lieuDB;

    /**
     * Les différents états de notre programme
     */
    public enum State {
        INIT, GESTION_ACCES, GESTION_CLEF, GESTION_GROUPE, GESTION_HISTORIQUE_UTILISATION, GESTION_LIEU,
        GESTION_PERSONNE, GESTION_REGISTRE, GESTION_SERRURE, EXITING, EXIT
    }

    /**
     * Constructeur par défaut du controller
     * @param state est l'état du programme lors de l'appel
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public Controller(final State state) throws SQLException, IOException, ClassNotFoundException {
        this.applicationState = state;
        this.ds = DataSourceFactory.getPostgreSQLDataSourceDev();
        this.connectionDB = ds.getConnection();
    }

    /**
     * Cette méthode permet de définir l'état du programme
     * @param state est l'état souhaité
     */
    public void setApplicationState(final State state) {
        this.applicationState = state;
    }

    /**
     * Méthode qui permet de réinitialiser le controller et fermer la connexion
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public void controllerTearDown() throws SQLException {
        this.applicationState = State.EXIT;
        this.ds = null;
        this.connectionDB.close();
    }

    /**
     * Cette méthode permet d'ajouter un groupe à la base de données Dev
     * @param groupe est l'objet groupe à insérer
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    /* GROUPE */
    public void addGroupe(final Groupe groupe) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_GROUPE) {

            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO groupes\n"
                    + "(uuid,\n"
                    + "nom,\n"
                    + "description)\n"
                    + "VALUES ('" + groupe.getUuid() + "',\n"
                    + "'" + groupe.getNom() + "',\n"
                    + "'" + groupe.getDescription() + "');";
            //Execute the SQL
            statementDB.executeUpdate(sql);

        }
    }

    /**
     * Cette méthode retourne la liste des groupes de la base de données Dev
     * @return un HashMap des groupes
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public HashMap<UUID, Groupe> listGroupe() throws SQLException {
        HashMap<UUID, Groupe> result = new HashMap<>();
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT * FROM groupes;";

            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                result.put(UUID.fromString(rs.getString("uuid")),
                        new Groupe(UUID.fromString(rs.getString("uuid")),
                                rs.getString("nom"), rs.getString("description")));
            }
        }
        return result;
    }

    /**
     * Cette méthode récupère un groupe dans la base de données Dev
     * @param findGroupe contient les différentes informations déjà connues telle que le nom du groupe
     * @return le groupe souhaité
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public Groupe getGroupe(final Groupe findGroupe) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT uuid, nom, description\n"
                    + "FROM groupes c\n"
                    + "WHERE uuid = '" + findGroupe.getUuid() + "';";
            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);
            while (rs.next()) {
                groupeDB = new Groupe((UUID) rs.getObject("uuid"),
                        rs.getString("nom"),
                        rs.getString("description"));
            }
        }
        return groupeDB;
    }

    /**
     * Cette méthode permet de mettre à jour un groupe dans la base de données Dev
     * @param newGroupe contient les nouvelles informations avec l'UUID du groupe à modifier
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void updateGroupe(final Groupe newGroupe) throws SQLException, IOException,
            ClassNotFoundException {
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "UPDATE groupes\n"
                    + "SET nom = '" + newGroupe.getNom() + "',\n"
                    + "description = '" + newGroupe.getDescription() + "'\n"
                    + "WHERE uuid = '" + newGroupe.getUuid() + "'\n"
                    + ";";
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de supprimer un groupe dans la base de données Dev
     * @param groupe est le groupe à supprimer
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void deleteGroupe(final Groupe groupe) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "DELETE FROM groupes WHERE uuid = '" + groupe.getUuid() + "' AND nom ILIKE '"
                    + groupe.getNom() + "' AND description ILIKE '"
                    + groupe.getDescription() + "';";
            //Delete
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet d'ajouter un lien de parenter entre deux groupes (parent/enfant) dans la base de données Dev
     * @param groupeParent est le groupe qui sera l'enfant
     * @param groupeEnfant est le groupe qui sera le parent
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    // Groupe Groupe
    public void addGroupeGroupe(final Groupe groupeParent, final Groupe groupeEnfant) throws SQLException {
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO groupes_groupes "
                    + "       (enfant_uuid, "
                    + "        parent_uuid) "
                    + "VALUES "
                    + "('" + groupeEnfant.getUuid() + "',"
                    + "'" + groupeParent.getUuid() + "') "
                    + "ON CONFLICT DO NOTHING;";
            //Fais en sorte que si il y a conflit le INSERT est ignoré similaire à IF EXISTS
            //Insert
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode retourne la liste de la liaison entre les groupes parents et enfants dans la base de données Dev
     * @return un HashMap des groupes parents avec leurs groupes enfants
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public HashMap<UUID, UUID> listGroupeGroupe() throws SQLException {
        HashMap<UUID, UUID> result = new HashMap<>();
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT * FROM groupes_groupes;";

            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                result.put(UUID.fromString(rs.getString("parent_uuid")), UUID.fromString(rs.getString("enfant_uuid")));
            }
        }
        return result;
    }

    /**
     * Cette méthode permet de retourner la liste de groupes parent d'un groupe de la base de données Dev
     * @param groupe est le groupe avec lequel on va retrouver ces parents
     * @return une ArrayList de groupe
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public ArrayList<Groupe> getGroupeParent(final Groupe groupe) throws SQLException {
        ArrayList<Groupe> result = new ArrayList<>();
        statementDB = connectionDB.createStatement();
        String sql = "SELECT * FROM groupes WHERE uuid = (SELECT parent_uuid FROM groupes_groupes "
                + "WHERE enfant_uuid = '" + groupe.getUuid() + "');";

        // Execute SQL statement returns as a ResultSet object.
        ResultSet rs = statementDB.executeQuery(sql);

        // Fetch on the ResultSet
        // Move the cursor to the next record.
        while (rs.next()) {
            groupeDB = new Groupe(UUID.fromString(rs.getString("uuid")),
                    rs.getString("nom"),
                    rs.getString("description"));
            result.add(groupeDB);
        }
        return result;
    }

    /**
     * Cette méthode permet de retourner la liste de groupes enfants d'un groupe de la base de données Dev
     * @param groupe est le groupe avec lequel on va retrouver ces enfants
     * @return une ArrayList de groupe
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public ArrayList<Groupe> getGroupeEnfant(final Groupe groupe) throws SQLException {
        ArrayList<Groupe> result = new ArrayList<>();
        statementDB = connectionDB.createStatement();
        String sql = "SELECT * FROM groupes WHERE uuid = (SELECT parent_uuid FROM groupes_groupes "
                 + "WHERE parent_uuid = '" + groupe.getUuid() + "');";
        // Execute SQL statement returns as a ResultSet object.
        ResultSet rs = statementDB.executeQuery(sql);
        // Fetch on the ResultSet
        // Move the cursor to the next record.
        while (rs.next()) {
            result.add(this.listGroupe().get(UUID.fromString(rs.getString("parent_uuid"))));
        }
        return result;
    }

    /**
     * Cette méthode permet de delete les liens où le groupe passé en paramètre est enfant dans la base de données Dev
     * @param groupeToDelete est le groupe enfant
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public void deleteGroupeGroupeParent(final Groupe groupeToDelete) throws SQLException {
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "DELETE FROM groupes_groupes WHERE enfant_uuid = '" + groupeToDelete.getUuid() + "';";
            //Delete
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de delete les liens où le groupe passé en paramètre est parent dans la base de données Dev
     * @param groupeToDelete est le groupe enfant
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public void deleteGroupeGroupeEnfant(final Groupe groupeToDelete) throws SQLException {
        if (applicationState == State.GESTION_GROUPE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "DELETE FROM groupes_groupes WHERE parent_uuid = '" + groupeToDelete.getUuid() + "';";
            //Delete
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet d'ajouter un accès à un groupe dans la base de données Dev
     * @param acces est l'accès souhaité
     * @param groupe est le groupe souhaité
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    /* ACCES */
    public void addAccesToGroupeExistant(final Acces acces, final Groupe groupe) throws SQLException, IOException,
            ClassNotFoundException {
        boolean accesPresentInGroupe = false;
        for (int i = 0; i < groupe.getAllAcces().size(); i++) {
            if (groupe.getIndexAcces(i) == acces) {
                accesPresentInGroupe = true;
            }
        }
        if (!accesPresentInGroupe) {
            throw new RuntimeException("On ne peut pas ajouter un accès non-présent dans la liste des accès du groupe");
        }
        if (applicationState == State.GESTION_ACCES) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO acces\n"
                    + "(groupes_uuid,\n"
                    + "lieux_uuid,\n"
                    + "date_debut,\n"
                    + "date_fin)\n"
                    + "VALUES ('" + groupe.getUuid() + "',\n"
                    + "'" + acces.getLieu().getUuid() + "',\n"
                    + "'" + Timestamp.valueOf(acces.getDateDebut()) + "',\n"
                    + "'" + Timestamp.valueOf(acces.getDateFin()) + "')"
                    + "ON CONFLICT DO NOTHING;";
            //Execute the SQL
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de lister les différents accès de la base de données Dev
     * @return une ArrayList des accès
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public ArrayList<Acces> listAcces() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Acces> result = new ArrayList<>();
        if (applicationState == State.GESTION_ACCES) {
            //utilisation la base de données dev

            try {
                statementDB = connectionDB.createStatement();
                ResultSet rs = statementDB.executeQuery("SELECT *"
                        + " FROM acces;");
                while (rs.next()) {
                    result.add(new Acces(new Timestamp(rs.getLong("date_debut")).toLocalDateTime(),
                            new Timestamp(rs.getLong("date_debut")).toLocalDateTime(),
                            getLieu(listLieu().get(UUID.fromString(rs.getString("lieux_uuid"))))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /**
     * Cette méthode permet de récupérer un accès via son groupe dans la base de données de Dev
     * @param groupe est le groupe où l'on va retrouver l'accès
     * @return l'accès en question
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public Acces getAcces(final Groupe groupe) throws IOException, IOException, ClassNotFoundException {
        Acces result = new Acces(LocalDateTime.MAX, LocalDateTime.MIN, null);
        if (applicationState == State.GESTION_ACCES) {
            try {
                statementDB = connectionDB.createStatement();
                ResultSet rs = statementDB.executeQuery("SELECT *"
                        + " FROM acces"
                        + " WHERE groupes_uuid = '" + groupe.getUuid() + "';");
                while (rs.next()) {
                    result = (new Acces(new Timestamp(rs.getLong("date_debut")).toLocalDateTime(),
                            new Timestamp(rs.getLong("date_debut")).toLocalDateTime(),
                            getLieu(listLieu().get(UUID.fromString(rs.getString("lieux_uuid"))))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Cette méthode ajoute une clef à la base de données Dev
     * @param clef est la clef à ajouter
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    /* CLEF */
    public void addClef(final Clef clef) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_CLEF) {
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO clefs\n"
                    + "(numero_serie,\n"
                    + "status)\n"
                    + "VALUES ('" + clef.getNumeroserie() + "',\n"
                    + "'" + clef.getStatus().toString() + "');";
            //Execute the SQL
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de lister toutes les clefs de la base de données de Dev
     * @return une ArrayList des clefs
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public ArrayList<Clef> listClef() throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_CLEF) {
            clefs = new ArrayList<>();
            try {
                statementDB = connectionDB.createStatement();
                ResultSet resultSet = statementDB.executeQuery("SELECT numero_serie, status"
                        + " FROM clefs;");
                while (resultSet.next()) {
                    Clef clefList = new Clef(resultSet.getString("numero_serie"),
                            resultSet.getString("status"));
                    clefs.add(clefList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clefs;
    }

    /**
     * Cette méthode permet de récupérer une clef grâce à son numéro de série dans la base de données Dev
     * @param numeroSerie est le numéro de série de la clef
     * @return un objet Clef
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public Clef getClef(final String numeroSerie) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_CLEF) {
            statementDB = connectionDB.createStatement();
            String sql = "SELECT numero_serie, status\n"
                    + "FROM clefs \n"
                    + "WHERE numero_serie ILIKE '" + numeroSerie + "';";
            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);
            while (rs.next()) {
                clefDB = new Clef(rs.getString("numero_serie"),
                        rs.getString("status"));
            }
        }
        return clefDB;
    }

    /**
     * Cette méthode permet de mettre à jour les informations d'une clef dans la base de données de Dev
     * @param numeroSerie est le numéro de série de la Clef
     * @param clef est l'objet avec les nouvelles informations
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void updateClef(final String numeroSerie, final Clef clef) throws SQLException, IOException,
            ClassNotFoundException {
        if (applicationState == State.GESTION_CLEF) {
            statementDB = connectionDB.createStatement();
            String sql = "UPDATE clefs\n"
                    + "SET status = '" + clef.getStatus() + "',\n"
                    + "numero_serie = '" + clef.getNumeroserie() + "'\n"
                    + "WHERE numero_serie = '" + numeroSerie + "'\n"
                    + ";";
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de supprimer une clef dans la base de données Dev
     * @param clef est la clef à supprimer
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void deleteClef(final Clef clef) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_CLEF) {
            statementDB = connectionDB.createStatement();
            String sql = "DELETE FROM clefs WHERE numero_serie ILIKE '" + clef.getNumeroserie() + "' AND status ILIKE '"
                    + clef.getStatus() + "';";
            //Delete
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet d'ajouter un historique d'utilisation à la base de données Dev
     * @param serrure est la serrure liée à cette historique
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    /* HISTORIQUE UTILISATION*/
    public void addHistoriqueUtilisation(final Serrure serrure) throws SQLException {
         if (applicationState == State.GESTION_HISTORIQUE_UTILISATION) {
            statementDB = connectionDB.createStatement();
             for (int i = 0; i < serrure.getAllHistoriqueUtilisation().size(); i++) {
             String sql = "INSERT INTO historique_utilisation\n"
                    + "(clefs_numero_serie,\n"
                    + "serrures_uuid,\n"
                    + "date_utilisation,"
                    + "status)\n"
                    + "VALUES ('" + serrure.getHistoriqueUtilisation(i).getClef().getNumeroserie() + "',\n"
                    + "'" + serrure.getUuid() + "',\n"
                    + "'" + serrure.getHistoriqueUtilisation(i).getDateUtilisation() + "',\n"
                    + "'" + serrure.getHistoriqueUtilisation(i).getStatus().toString() + "');";
            //Execute the SQL
            statementDB.executeUpdate(sql);
             }
        }
    }

    /**
     * Cette méthode permet de lister les historiques d'utilisation de la base de données Dev
     */
    public void listHistoriqueUtilisation() {

    }

    /**
     * Cette méthode permet de récupérer un historique d'utilisation dans la base de données Dev
     */
    public void getHistoriqueUtilisation() {

    }

    /**
     * Cette méthode permet de modifier un historique d'utilisation dans la base de données Dev
     */
    public void updateHistoriqueUtilisation() {

    }

    /**
     * Cette méthode permet de supprimer un historique d'utilisation dans la base de données Dev
     */
    public void deleteHistoriqueUtilisation() {

    }

    /**
     * Cette méthode permet d'ajouter un lieu dans la base de données Dev
     * @param lieu est le lieu à ajouter
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    /* LIEU */
    public void addLieu(final Lieu lieu) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_LIEU) {
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO lieux\n"
                    + "(uuid,\n"
                    + "nom)\n"
                    + "VALUES ('" + lieu.getUuid() + "',\n"
                    + "'" + lieu.getNom() + "');";
            //Execute the SQL
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet d'ajouter le lieu parent d'un lieu dans la base de données Dev
     * @param lieu est le lieu qui possède un parent (avant le lieu parent était null et déjà présent dans la BDD)
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void addLieuParent(final Lieu lieu) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_LIEU) {
            statementDB = connectionDB.createStatement();
            String sql = "UPDATE lieux "
                    + "SET lieu_parent_uuid = '" + lieu.getLieuParent().getUuid() + "' "
                    + "WHERE uuid = '" + lieu.getUuid() + "';";
            //Execute the SQL
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de lister tous les lieux de la base de données Dev
     * @return un HashMap avec les différents lieux
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public HashMap<UUID, Lieu> listLieu() throws SQLException {
        HashMap<UUID, Lieu> result = new HashMap<>();
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT * FROM Lieu;";

            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                result.put(UUID.fromString(rs.getString("uuid")),
                        new Lieu(UUID.fromString(rs.getString("uuid")), rs.getString("nom")));
            }
        }
        return result;
    }

    /**
     * Cette méthode récupère un lieu dans la base de données Dev
     * @param findLieu contient les différents paramètres qui permettent d'identifier le lieu à récupérer
     * @return le lieu souhaité
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public Lieu getLieu(final Lieu findLieu) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_LIEU) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT *\n"
                    + "FROM lieux \n"
                    + "WHERE uuid = '" + findLieu.getUuid() + "';";
            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);
            while (rs.next()) {
                lieuDB = new Lieu((UUID) rs.getObject("uuid"),
                        rs.getString("nom"));
            }
        }
        return lieuDB;
    }

    /**
     * Cette méthode permet de modifier un lieu de la base de données Dev
     * @param uuidLieu est l'identifiant de lieu à modifier
     * @param newLieu contient les nouvelles informations
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void updateLieu(final UUID uuidLieu, final Lieu newLieu) throws SQLException, IOException,
            ClassNotFoundException {
        if (applicationState == State.GESTION_LIEU) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "UPDATE lieux\n"
                    + "SET nom = '" + newLieu.getNom() + "',\n"
                    + "lieu_parent_uuid = '" + newLieu.getLieuParent().getUuid() + "'\n"
                    + "WHERE uuid = '" + uuidLieu + "'\n"
                    + ";";
            statementDB.executeUpdate(sql);
        }

    }

    /**
     * Cette méthode permet de supprimer un lieu de la base de données Dev
     * @param lieu est le lieu à supprimer
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void deleteLieu(final Lieu lieu) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_LIEU) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "DELETE FROM lieux WHERE uuid = '" + lieu.getUuid() + "' AND nom ILIKE '"
                    + lieu.getNom() + "' AND lieu_parent_uuid IS NULL;";
            //Delete
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet d'ajouter une personne dans la base de données Dev
     * @param personne est la personne à ajouter
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    /* PERSONNE */
    public void addPersonne(final Personne personne) throws SQLException, IOException, ClassNotFoundException {
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO personnes\n"
                    + "(uuid,\n"
                    + "nom,\n"
                    + "prenom,"
                    + "email,"
                    + "groupes_uuid)\n"
                    + "VALUES ('" + personne.getUuid() + "',\n"
                    + "'" + personne.getNom() + "',\n"
                    + "'" + personne.getPrenom() + "',\n"
                    + "'" + personne.getEmail() + "',\n"
                    + "'" + personne.getGroupe().getUuid() + "');";
            //Execute the SQL
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de lister les personnes de la base de données Dev
     * @return un HashMap des personnes
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public HashMap<UUID, Personne> listPersonne() throws SQLException, IOException, ClassNotFoundException {
        HashMap<UUID, Personne> result = new HashMap<>();
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT * FROM personnes;";

            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                result.put(UUID.fromString(rs.getString("uuid")),
                        new Personne(UUID.fromString(rs.getString("uuid")),
                                rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
            }
        }
        return result;
    }

    /**
     *
     * Cette méthode permet de récupérer une personne de la base de données Dev
     * @param findPersonne Personne à chercher dans la BD (Ne cherche que par email)
     * @return la personne recherché ou une personne ayant que de "null" si
     * aucun résultat
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public Personne getPersonne(final Personne findPersonne) throws IOException, ClassNotFoundException, SQLException {
        Personne result = new Personne("null", "null", "null");
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "SELECT * FROM personnes "
                    + "WHERE email ILIKE '" + findPersonne.getEmail() + "';";

            // Execute SQL statement returns as a ResultSet object.
            ResultSet rs = statementDB.executeQuery(sql);

            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                result = new Personne(UUID.fromString(rs.getString("uuid")),
                        rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
            }
        }
        return result;
    }

    /**
     * Cette méthode permet de modifier une personne de la base de données Dev
     *
     * WARNING the UUID from the personne cannot be modified it is the
     * identifier WARNING the group is never modified from here
     *
     * @param newPersonne the new personne with the old identifier
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void updatePersonne(final Personne newPersonne) throws IOException, ClassNotFoundException, SQLException {
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "UPDATE personnes "
                    + "SET nom = '" + newPersonne.getNom() + "', "
                    + "prenom = '" + newPersonne.getPrenom() + "', "
                    + "email = '" + newPersonne.getEmail() + "' "
                    + "WHERE uuid = '" + newPersonne.getUuid() + "'";

            //Execute the Update
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de supprimer une personne de la base de données Dev
     * @param deletePersonne est la personne à supprimer
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    public void deletePersonne(final Personne deletePersonne) throws IOException, ClassNotFoundException, SQLException {
        if (applicationState == State.GESTION_PERSONNE) {
            //utilisation la base de données dev
            statementDB = connectionDB.createStatement();
            String sql = "DELETE * "
                    + "FROM personnes"
                    + "WHERE uuid = '" + deletePersonne.getUuid() + "'";

            //Execute the Update
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet d'ajouter les registres à une clef dans la base de données Dev
     * @param clef est l'objet qui contient la liste des registres
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */

    /* REGISTRE */
    public void addRegistre(final Clef clef) throws SQLException {
        if (applicationState == State.GESTION_REGISTRE) {
            statementDB = connectionDB.createStatement();
            for (int i = 0; i < clef.getAllRegistres().size(); i++) {
                if (clef.getRegistre(i).getPersonne() != null) {
                    if (clef.getRegistre(i).getDateFin() == null) {
                    String sql = "INSERT INTO clefs_personnes\n"
                            + "(personnes_uuid,\n"
                            + "clefs_numero_serie,\n"
                            + "date_debut)\n"
                            + "VALUES ('" + clef.getRegistre(i).getPersonne().getUuid() + "',\n"
                            + "'" + clef.getNumeroserie() + "',\n"
                            + "'" + clef.getRegistre(i).getDateDebut() + "');";
                    //Execute the SQL
                    statementDB.executeUpdate(sql);
                    } else {
                    String sql = "INSERT INTO clefs_personnes\n"
                            + "(personnes_uuid,\n"
                            + "clefs_numero_serie,\n"
                            + "date_debut,"
                            + "date_fin)\n"
                            + "VALUES ('" + clef.getRegistre(i).getPersonne().getUuid() + "',\n"
                            + "'" + clef.getNumeroserie() + "',\n"
                            + "'" + clef.getRegistre(i).getDateDebut() + "',"
                            + "'" + clef.getRegistre(i).getDateFin() + ");";
                    //Execute the SQL
                    statementDB.executeUpdate(sql);
                    }
                }
            }
        }
    }

    /**
     * Cette méthode permet de lister les registres de la base de données Dev
     */
    public void listRegistre() {

    }

    /**
     * Cette méthode permet de récupérer un registre d'une clef dans la base de données Dev
     */
//    public Registre getRegistre(final Clef clef, final int index) throws SQLException {
//
//    }

    /**
     * Cette méthode permet de modifier un registre de la base de données Dev
     */
    public void updateRegistre() {

    }

    /**
     * Cette méthode permet de supprimer un registre de la base de données Dev
     */
    public void deleteRegistre() {

    }

    /**
     * Cette méthode permet d'ajouter une serrure à la base de données Dev
     * @param serrure est la serrure à ajouter
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    /* SERRURE */
    public void addSerrure(final Serrure serrure) throws SQLException {
        if (applicationState == State.GESTION_SERRURE) {
            statementDB = connectionDB.createStatement();
            String sql = "INSERT INTO serrures\n"
                    + "(uuid,\n"
                    + "cardinalite) \n"
                    + "VALUES ('" + serrure.getUuid() + "',\n"
                    + "'" + serrure.getCardinalite().toString() + "');";
            //Execute the SQL
            statementDB.executeUpdate(sql);
        }
    }

    /**
     * Cette méthode permet de lister les serrures de la base de données Dev
     */
    public void listSerrure() {

    }

    /**
     * Cette méthode permet de récupérer une serrure de la base de données Dev
     */
    public void getSerrure() {

    }

    /**
     * Cette méthode permet de modifier les informations des serrures par rapport à un lieu de la base de données Dev
     * Utile pour les instanciations
     * @param lieu contient les différentes serrures à mettre à jour
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     */
    public void updateSerrureLieu(final Lieu lieu) throws SQLException {
        if (applicationState == State.GESTION_SERRURE) {
            statementDB = connectionDB.createStatement();
            for (int i = 0; i < lieu.getAllSerrures().size(); i++) {
                String sql = "UPDATE serrures "
                        + "SET cardinalite = '" + lieu.getSerrure(i).getCardinalite().toString() + "', "
                        + "lieux_uuid = '" + lieu.getUuid() + "' "
                        + "WHERE uuid = '" + lieu.getSerrure(i).getUuid() + "'";
                //Execute the SQL
                statementDB.executeUpdate(sql);
            }
        }
    }

    /**
     * Cette méthode permet de supprimer une serrure de la base de données
     */
    public void deleteSerrure() {

    }

}
