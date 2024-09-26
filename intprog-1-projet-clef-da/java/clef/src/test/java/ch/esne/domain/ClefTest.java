
package ch.esne.domain;

import ch.esne.console.Controller;
import ch.esne.domain.Clef;
import static ch.esne.domain.GroupeTest.insert;
import static ch.esne.domain.GroupeTest.instanceDB;
import ch.esne.util.DemoData;
import ch.esne.util.InstanceDB;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */

public class ClefTest {

    /*Initialisation variables privées*/
    static Registre registre1;
    static Registre registre2;
    static Personne personne1;
    static String dateDebut;
    static Timestamp timestampDebut;
    static String dateFin;
    static Timestamp timestampFin;
    static Clef clef;
    static InstanceDB instanceDB;
    static DemoData insert;
    static Controller controller;



    @BeforeClass
    public static void SetUpClass() throws SQLException, ClassNotFoundException, ClassNotFoundException, IOException{
        try {
            instanceDB = new InstanceDB();
        } catch (IOException ex) {
            Logger.getLogger(GroupeTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        instanceDB.clear();
        instanceDB.init();
        insert = instanceDB.getInsert();
        controller = new Controller(Controller.State.INIT);

        personne1 = new Personne("Ameli","Personne","darwin.ameli@rpn.ch");
        dateDebut = "2021-12-31 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-02-06 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        registre1 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne1);
        dateDebut = "2022-01-23 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-02-08 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        registre2 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne1);
        clef = new Clef("0000-0000-0000-0002",ClefStatus.ACTIVE);
        clef.addRegistre(registre1);
        clef.addRegistre(registre2);

    }

    @AfterClass
    public static void tearDownClass() throws ClassNotFoundException, SQLException, IOException {
        //Retirer des commentaire après que tout fonctionne aide au
        //Debogage avec Pgadmin
        //Car les tuples restent dans la BD après les test
        //instanceDB.clear();
        //instanceDB = null;
        controller.controllerTearDown();
    }

    /**
     * Test constructeur de clef avec un status en string (utile pour la BDD)
     */
    @Test
    public void createClefWithStringStatus() {
        Clef clefStatusString = new Clef("0000-0000-0000-0056","ACTIVE");
        assertEquals(ClefStatus.ACTIVE,clefStatusString.getStatus());

        clefStatusString = new Clef("0000-0000-0000-0056","INACTIVE");
        assertEquals(ClefStatus.INACTIVE,clefStatusString.getStatus());

        clefStatusString = new Clef("0000-0000-0000-0056","DISFONCTIONNELLE");
        assertEquals(ClefStatus.DISFONCTIONNELLE,clefStatusString.getStatus());

        clefStatusString = new Clef("0000-0000-0000-0056","PERDUE");
        assertEquals(ClefStatus.PERDUE,clefStatusString.getStatus());
    }

    /**
     * Test la récupération du numéro de série
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    @Test
    public void getNumeroserie() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_CLEF);
        Clef expInstance = insert.getClefs().get("0000-0000-0000-0002"); //Dans notre cas, c'est étrange parce que
        // dans le hashmap on a mis le numéro de serie également dans la colonne pour rechercher une clef, mais si on
        //met de 1 - l'infini, on aurait eu insert.getClefs().get("2") pour avoir la deuxième clef
        Clef instance = controller.getClef(expInstance.getNumeroserie());
        String expResult = expInstance.getNumeroserie();
        String result = instance.getNumeroserie();
        assertEquals(result, expResult);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Teste la modification du numéro de série
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */

    @Test
    public void setNumeroserie() throws SQLException, IOException, ClassNotFoundException {
        clef.setNumeroserie("0000-0000-0000-0001");
        assertEquals("0000-0000-0000-0001",clef.getNumeroserie());
    }

    /**
     * Test la récupération du status
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    @Test
    public void getStatus() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_CLEF);
        Clef expInstance = insert.getClefs().get("0000-0000-0000-0002");
        Clef instance = controller.getClef(expInstance.getNumeroserie());
        ClefStatus expResult = expInstance.getStatus();
        ClefStatus result = instance.getStatus();
        assertEquals(result, expResult);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Teste la modification du status
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */

    @Test
    public void setStatus() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_CLEF);
        Clef expInstance = insert.getClefs().get("0000-0000-0000-0001");
        expInstance.setStatus(ClefStatus.PERDUE);
        controller.updateClef("0000-0000-0000-0001",expInstance);
        Clef instance = controller.getClef("0000-0000-0000-0001");
        assertEquals(instance.getStatus(), expInstance.getStatus());
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test l'ajout d'un registre
     */
    @Test
    public void addRegistre() {
        Clef clefRegistresVide = new Clef("0000-0000-0000-0002");
        dateDebut = "2021-12-31 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-02-06 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        Registre registre3;
        registre3 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne1);
        clefRegistresVide.addRegistre(registre3);
        assertEquals(registre3, clefRegistresVide.getLastRegistre());
    }

    /**
     * Test l'ajout de deux mêmes registres
     */
    @Test(expected = RuntimeException.class)
    public void addTwoSameRegistre() {
        dateDebut = "2021-12-31 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-02-06 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        Registre registre3;
        registre3 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne1);
        clef.addRegistre(registre3);
        clef.addRegistre(registre3);
        assertEquals(registre3, clef.getLastRegistre());
    }

    /**
     * Test d'ajout d'un registre si la liste de registres est vide
     */
    @Test
    public void addRegistreIfRegistresVide() {
        dateDebut = "2021-12-31 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-02-06 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        Registre registre3;
        registre3 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne1);
        clef.addRegistre(registre3);
        assertEquals(registre3, clef.getLastRegistre());
    }

    /**
     * Test la récupération d'un registre via son index
     */
    @Test
    public void getRegistre() {
        assertEquals(registre2,clef.getRegistre(1));
    }

    /**
     * Test la récupération d'un registre si la liste de registres est vide
     */
    @Test(expected = RuntimeException.class)
    public void getRegistreIfRegistresVide() {
        Clef clefRegistresVide = new Clef("0000-0000-0000-0002");
        assertEquals(registre2,clefRegistresVide.getRegistre(1));
    }

    /**
     * Test la récupération du premier registre
     */
    @Test
    public void getFirstRegistre() {
        assertEquals(registre1,clef.getFirstRegistre());
    }

    /**
     * Test la récupération du premier registre si la liste de registres est vide
     */
    @Test(expected = RuntimeException.class)
    public void getFirstIfRegistresVide() {
        Clef clefRegistresVide = new Clef("0000-0000-0000-0002");
        assertEquals(registre1,clefRegistresVide.getFirstRegistre()); //registre1 n'a jamais été ajouté
    }

    /**
     * Test la récupération du dernier registre
     */
    @Test
    public void getLastRegistre() {
        assertEquals(registre2,clef.getLastRegistre());
    }

    /**
     * Test la récupération du dernier registre si la liste de registres est vide
     */
    @Test(expected = RuntimeException.class)
    public void getLastRegistreIfRegistresVide() {
        Clef clefRegistresVide = new Clef("0000-0000-0000-0002");
        assertEquals(registre2,clefRegistresVide.getLastRegistre());
    }

    /**
     * Test de récupération de tous les registres
     */
    @Test
    public void getAllRegistres() {
        ArrayList<Registre> arrayList = new ArrayList<>(2);
        arrayList.add(registre1);
        arrayList.add(registre2);
        assertEquals(arrayList,clef.getAllRegistres());
    }

    /**
     * Test si la liste de registres est vide
     */
    @Test
    public void isEmptyRegistres() {
        Clef clefRegistresVide = new Clef("0000-0000-0000-0002");
        assertTrue(clefRegistresVide.isEmpty());
    }

    /**
     * Test equals avec deux clefs différentes
     * @throws SQLException fournit des informations sur une erreur d'accès à la base de données ou d'autres erreurs.
     * @throws IOException signale qu'une exception d'E/S quelconque s'est produite
     * @throws ClassNotFoundException se produit lorsque vous essayez de charger une classe non-trouvée
     */
    @Test
    public void testEqualsFalse() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_CLEF);
        Clef expInstance = insert.getClefs().get("0000-0000-0000-0003");
        Clef expInstanceFalse = insert.getClefs().get("0000-0000-0000-0004"); //Mauvaise clef
        Clef instanceFalse = controller.getClef(expInstanceFalse.getNumeroserie()); //On récupère la mauvaise clef
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        assertFalse(expInstance.equals(instanceFalse));
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test equals avec le même objet
     */
    @Test
    public void testEquals() {
        Clef clefEquals1 = new Clef("0000-0000-0000-0005");
        assertTrue(clefEquals1.equals(clefEquals1));
    }

    /**
     * Test equals avec un autre type d'objet
     */
    @Test
    public void testEqualsFalseInstance() {
        controller.setApplicationState(Controller.State.GESTION_CLEF);
        Clef expInstance = insert.getClefs().get("0000-0000-0000-0003");
        Integer instanceEqualsFalse = 12;
        assertFalse(expInstance.equals(instanceEqualsFalse));
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test le hashcode
     */
    @Test
    public void testHashCode() {
        Clef clefHash1 = new Clef("0000-0000-0000-0005");
        Clef clefHash2 = new Clef("0000-0000-0000-0005"); //MANQUE REGISTRE
        assertEquals(clefHash1.hashCode(),clefHash2.hashCode());
    }

    /**
     * Test le ToString
     */
    @Test
    public void testToString() {
        Clef clefToString = new Clef("0000-0000-0000-0005");
        assertEquals("Clef{numeroserie=0000-0000-0000-0005, registres=0, status=ACTIVE}", clefToString.toString()); //MANQUE REGISTRE
    }
}
