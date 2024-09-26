/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ch.esne.domain;

import ch.esne.console.Controller;
import ch.esne.util.DataSourceFactory;
import ch.esne.util.DemoData;
import ch.esne.util.InstanceDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.*;

import static org.junit.Assert.*;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class GroupeTest {

    static ArrayList<Groupe> groupesParent;
    static ArrayList<Groupe> groupesEnfant;
    static ArrayList<Acces> arrayAcces;
    static ch.esne.domain.Lieu lieu;
    static Groupe groupeParent;
    static Groupe groupeEnfant;
    static Groupe groupeParent1;
    static Groupe groupeEnfant1;
    static Acces acces;
    static Acces acces1;
    static Groupe instance;
    static InstanceDB instanceDB;
    static DemoData insert;
    static Controller controller;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @BeforeClass
    public static void SetUpClass() throws SQLException, ClassNotFoundException, IOException{
        try {
            instanceDB = new InstanceDB();
        } catch (IOException ex) {
            Logger.getLogger(GroupeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instanceDB.clear();
        instanceDB.init();
        insert = instanceDB.getInsert();
        controller = new Controller(Controller.State.INIT);

                groupesParent = new ArrayList<>();
         groupesEnfant = new ArrayList<>();
        arrayAcces = new ArrayList<>();
        groupeParent = new Groupe(new UUID(0, 0), "Parent", "Description parent");
         groupeEnfant = new Groupe(new UUID(0, 0), "Enfant", "Description Enfant");
        groupeParent = new Groupe(new UUID(1, 0), "Parent1", "Description parent1");
        groupeEnfant = new Groupe(new UUID(1, 0), "Enfant1", "Description Enfant1");
        lieu = new Lieu("Test");
        acces = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, lieu);
        acces1 = new Acces(new UUID(1, 0), LocalDateTime.MIN, LocalDateTime.MAX, lieu);
        arrayAcces.add(acces);
        groupesParent.add(groupeParent);
        groupesEnfant.add(groupeEnfant);
        instance = new Groupe(new UUID(0, 0), "nom", "description", groupesParent, groupesEnfant, arrayAcces);
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

    /*
     * Test of constructor method, of class Groupe.
     */
    @Test
    public void createGroupeWithNameDescriptionAccesList() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Acces> listAcces = new ArrayList<>();
        listAcces.add(acces1);
        listAcces.add(acces);
        Groupe groupeTest = new Groupe("Groupe Test","Groupe personnel de test", listAcces);
        assertEquals(acces,groupeTest.getIndexAcces(1));
    }

    /*
     * Test of constructor method, of class Groupe.
     */
    @Test
    public void createGroupeWithUuidNameDescriptionAccesList() throws SQLException, IOException, ClassNotFoundException {
        ArrayList<Acces> listAcces = new ArrayList<>();
        listAcces.add(acces1);
        listAcces.add(acces);
        Groupe groupeTest = new Groupe(new UUID(2,2),"Groupe Test","Groupe personnel de test", listAcces);
        assertEquals(acces,groupeTest.getIndexAcces(1));
    }

        /*
     * Test of getUuid method, of class Groupe.
     */
    @Test
    public void testGetUuid() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(2, 2));
        Groupe instance = controller.getGroupe(expInstance);
        UUID expResult = expInstance.getUuid();
        UUID result = instance.getUuid();
        assertEquals(result, expResult);
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of getNom method, of class Groupe.
     */
    @Test
    public void testGetNom() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(3, 3));
        Groupe instance = controller.getGroupe(expInstance);
        String expResult = expInstance.getNom();
        String result = instance.getNom();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of setNom method, of class Groupe.
    N'utilise pas la BD car update pas encore en fonction
     */
    @Test
    public void testSetNom() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(2, 2));
        expInstance.setNom("diogo.toblerone@rpn.ch");
        controller.updateGroupe(expInstance);
        Groupe instance = controller.getGroupe(expInstance);
        assertEquals(instance.getNom(), expInstance.getNom());
        controller.setApplicationState(Controller.State.INIT);

    }

    /*
     * Test of getDescription method, of class Groupe.
     */
    @Test
    public void testGetDescription() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(2, 2));
        Groupe instance = controller.getGroupe(expInstance);
        String expResult = expInstance.getDescription();
        String result = instance.getDescription();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
        System.out.println("getDescription");
    }

    /*
     * Test of setDescription method, of class Groupe.
     */
    @Test
    public void testSetDescription() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(2, 2));
        expInstance.setDescription("Groupe personnel de Diogo Toblerone");
        controller.updateGroupe(expInstance);
        Groupe instance = controller.getGroupe(expInstance);
        assertEquals(instance.getDescription(), expInstance.getDescription());
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of getAllGroupesParent method, of class Groupe.
     * Les parent ne sont pas encore inseré
     */
    @Test
    public void testGetAllGroupesParent() throws SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupe = insert.getGroupes().get(new UUID(2, 2));
        ArrayList<Groupe> expInstance = groupe.getAllGroupesParent();
        ArrayList<Groupe> result = controller.getGroupeParent(groupe);
        assertEquals(expInstance.toString(), result.toString());
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of getIndexGroupeParent method, of class Groupe.
     */
    @Test
    public void testGetIndexGroupeParent() throws SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupe = insert.getGroupes().get(new UUID(2, 2));
        Groupe expInstance = groupe.getIndexGroupeParent(0);
        ArrayList<Groupe> result = controller.getGroupeParent(groupe);
        assertEquals(expInstance.getNom(), result.get(0).getNom());
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of getLastGroupeParent method, of class Groupe.
     */
    @Test
    public void testGetLastGroupeParent() throws SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupe = insert.getGroupes().get(new UUID(2, 2));
        Groupe expInstance = groupe.getLastGroupeParent();
        ArrayList<Groupe> result = controller.getGroupeParent(groupe);
        assertEquals(expInstance.getNom(), result.get(result.size()-1).getNom());
        controller.setApplicationState(Controller.State.INIT);

    }

    /*
     * Test of addGroupeParent method, of class Groupe.  //ERREUR
     */
    @Test
    public void testAddGroupeParent() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupe = new Groupe(new UUID(20,20),"GroupeTest", "Groupe add parent");
        Groupe groupeParent = insert.getGroupes().get(new UUID(10, 10));
        groupe.addGroupeParent(groupeParent);
        controller.addGroupe(groupe);
        controller.addGroupeGroupe(groupe.getLastGroupeParent(),groupe);
        ArrayList<Groupe> result = controller.getGroupeParent(groupe);
        int taille = controller.getGroupeParent(groupe).size();
        assertEquals(groupeParent.getNom(), result.get(taille-1).getNom());
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of getAllGroupesEnfant method, of class Groupe.
     */
    @Test
    public void testGetAllGroupesEnfant() throws SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupe = insert.getGroupes().get(new UUID(2, 2));
        ArrayList<Groupe> expInstance = groupe.getAllGroupesEnfant();
        ArrayList<Groupe> result = controller.getGroupeEnfant(groupe);
        assertEquals(expInstance.toString(), result.toString());
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of getIndexGroupeEnfant method, of class Groupe.
     */
    @Test
    public void testGetIndexGroupeEnfant() throws SQLException {
        System.out.println("getIndexGroupeEnfant");
        Integer index = 0;
        Groupe expResult = groupesEnfant.get(0);
        Groupe result = instance.getIndexGroupeEnfant(index);
        assertEquals(expResult, result);
    }

    /*
     * Test of getLastGroupeEnfant method, of class Groupe.
     */
    @Test
    public void testGetLastGroupeEnfant() {
        System.out.println("getLastGroupeEnfant");
        Groupe expResult = groupesEnfant.get(groupesEnfant.size() - 1);
        Groupe result = instance.getLastGroupeEnfant();
        assertEquals(expResult, result);

    }

    /*
     * Test of addGroupeEnfant method, of class Groupe.
     */
    @Test
    public void testAddGroupeEnfant() {
        System.out.println("addGroupeEnfant");
        instance.addGroupeEnfant(groupeEnfant1);

    }

    /*
     * Test of getAllAcces method, of class Groupe.
     */
    @Test
    public void testGetAllAcces() throws IOException, ClassNotFoundException {
        System.out.println("getAllAcces");
        ArrayList<Acces> expResult = arrayAcces;
        ArrayList<Acces> result = instance.getAllAcces();
        assertEquals(expResult, result);
    }

    /*
     * Test of getIndexAcces method, of class Groupe.
     */
    @Test
    public void testGetIndexAcces() {
        System.out.println("getIndexAcces");
        Integer index = 0;
        Acces expResult = arrayAcces.get(0);
        Acces result = instance.getIndexAcces(index);
        assertEquals(expResult, result);
    }

    /*
     * Test of getLastAcces method, of class Groupe.
     */
    @Test
    public void testGetLastAcces() {
        System.out.println("getLastAcces");
        Acces expResult = arrayAcces.get(arrayAcces.size() - 1);
        Acces result = instance.getLastAcces();
        assertEquals(expResult, result);
    }

    /*
     * Test of addAcces method, of class Groupe.
     */
    @Test
    public void testAddAcces() {
        System.out.println("addAcces");
        instance.addAcces(acces1);
    }

    /*
     * Test of hashCode method, of class Groupe.
     */
    @Test
    public void testHashCode() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(2, 2));
        Groupe instance = controller.getGroupe(expInstance);
        int expResult = expInstance.hashCode();
        int result = instance.hashCode();
        assertEquals(result, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsObjNull() {
      /*  System.out.println("equals");
        Object obj = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);*/
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongClass() {
        System.out.println("equals");
        Object obj = new Personne("nom", "prenom", "email");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongName() {
        System.out.println("equals");
        Groupe obj = new Groupe(new UUID(0, 0), "nom", "description", groupesParent, groupesEnfant, arrayAcces);;
        obj.setNom("WrongName");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongDescription() {
        System.out.println("equals");
        Groupe obj = new Groupe(new UUID(0, 0), "nom", "description", groupesParent, groupesEnfant, arrayAcces);;
        obj.setDescription("WrongDescription");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongUUID() {
        System.out.println("equals");
        Groupe obj = new Groupe(new UUID(0, 5), "nom", "description", groupesParent, groupesEnfant, arrayAcces);;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongParent() {
        System.out.println("equals");
        ArrayList<Groupe> groupesParent1 = new ArrayList<>();
        Groupe obj = new Groupe(new UUID(0, 0), "nom", "description", groupesParent1, groupesEnfant, arrayAcces);
        obj.addGroupeParent(groupeParent1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongEnfant() {
        System.out.println("equals");
        ArrayList<Groupe> groupesEnfant1 = new ArrayList<>();;
        Groupe obj = new Groupe(new UUID(0, 0), "nom", "description", groupesParent, groupesEnfant1, arrayAcces);
        obj.addGroupeEnfant(groupeEnfant1);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsWrongAcces() {
        System.out.println("equals");
        Acces acces2 = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, lieu);
        ArrayList<Acces> arrayAcces2 = new ArrayList<>();
        Groupe obj = new Groupe(new UUID(0, 0), "nom", "description", groupesParent, groupesEnfant, arrayAcces2);
        obj.addAcces(acces1);
        obj.addAcces(acces2);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of equals method, of class Groupe.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Groupe obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /*
     * Test of toString method, of class Groupe.
     */
    @Test
    public void testToString() throws SQLException, IOException, ClassNotFoundException {

        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getGroupes().get(new UUID(2, 2));
        Groupe instance = controller.getGroupe(expInstance);
        String expResult = expInstance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }
}
