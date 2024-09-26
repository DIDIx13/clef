/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://mylos.cifom.ch/gitlab/projet_prog-bdd/prog_carnet_adresse/-/tree/dev to go to the project
 *  @author ToblerC <cyril.tobler@icloud.com>
 */
package ch.esne.domain;

import ch.esne.console.Controller;
import ch.esne.util.DemoData;
import ch.esne.util.InstanceDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class PersonneTest {
    static InstanceDB instanceDB;
    static DemoData insert;
    static Controller controller;
    
    @BeforeClass
    public static void setUpClass() throws SQLException, ClassNotFoundException, IOException {
        try {
            instanceDB = new InstanceDB();
        } catch (IOException ex) {
            Logger.getLogger(GroupeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instanceDB.clear();
        instanceDB.init();
        insert = instanceDB.getInsert();
        controller = new Controller(Controller.State.INIT);
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
     * Test of getIdentifiant method, of class Personne.
     */
    @Test
    public void testGetIdentifiant() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        UUID expResult = expInstance.getUuid();
        UUID result = instance.getUuid();
        assertEquals(result, expResult);
        controller.setApplicationState(Controller.State.INIT);
    }


    /**
     * Test of getNom method, of class Personne.
     */
    @Test
    public void testGetNom() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        String expResult = expInstance.getNom();
        String result = instance.getNom();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of setNom method, of class Personne.
     */
    @Test
    public void testSetNom() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        expInstance.setNom("Toblerone");
        controller.updatePersonne(expInstance);
        Personne instance = controller.getPersonne(expInstance);
        assertEquals(instance.getNom(), expInstance.getNom());
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of getPrenom method, of class Personne.
     */
    @Test
    public void testGetPrenom() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        String expResult = expInstance.getPrenom();
        String result = instance.getPrenom();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of setPrenom method, of class Personne.
     */
    @Test
    public void testSetPrenom() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        expInstance.setPrenom("Cédric");
        controller.updatePersonne(expInstance);
        Personne instance = controller.getPersonne(expInstance);
        assertEquals(instance.getPrenom(), expInstance.getPrenom());
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of getEmail method, of class Personne.
     */
    @Test
    public void testGetEmail() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        String expResult = expInstance.getEmail();
        String result = instance.getEmail();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of setEmail method, of class Personne.
     */
    @Test
    public void testSetEmail() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        expInstance.setEmail("cedric.toblerone@rpn.ch");
        controller.updatePersonne(expInstance);
        Personne instance = controller.getPersonne(expInstance);
        assertEquals(instance.getEmail(), expInstance.getEmail());
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of getGroupe method, of class Personne.
     */
    @Test
    public void testGetGroupe() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        Groupe instance = controller.getGroupe(expInstance);
        String expResult = expInstance.getNom();
        String result = instance.getNom();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of setGroupe method, of class Personne.
     */
    @Test
    public void testSetGroupe() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe expInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        expInstance.setNom(insert.getPersonnes().get(new UUID(3, 3)).getEmail());
        controller.updateGroupe(expInstance);
        Groupe instance = controller.getGroupe(expInstance);
        String expResult = expInstance.getNom();
        String result = instance.getNom();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
        
    }

    /**
     * Test of toString method, of class Personne.
     */
    @Test
    public void testToString() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        instance.setGroupe(controller.getGroupe(expInstance.getGroupe()));
        String expResult = expInstance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of hashCode method, of class Personne.
     * Non fonctionnel car le hashcode groupe est un boucle(hashcode parent => hashcode enfant => hashcode parent ...)
     */
    @Test
    public void testHashCode() throws IOException, ClassNotFoundException, SQLException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        int expResult = expInstance.hashCode();
        int result = instance.hashCode();
        assertEquals(result, result);
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of equals method, of class Personne.
     */
    @Test
    public void testEquals() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupeInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        instance.setGroupe(groupeInstance);
        assertTrue(expInstance.equals(instance));
        controller.setApplicationState(Controller.State.INIT);
    }
    
    /**
     * Test of equals method, of class Personne.
     */
    @Test
    public void testEqualsFalse() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne expInstanceFalse = insert.getPersonnes().get(new UUID(2, 2));
        Personne instanceFalse = controller.getPersonne(expInstanceFalse);
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupeInstance = insert.getPersonnes().get(new UUID(2, 2)).getGroupe();
        instanceFalse.setGroupe(groupeInstance);
        assertFalse(expInstance.equals(instanceFalse));
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of equals method, of class Personne.
     */
    @Test
    public void testEqualsSame() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupeInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        instance.setGroupe(groupeInstance);
        assertTrue(instance.equals(instance));
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of equals method, of class Personne.
     */
    @Test
    public void testEqualsNull() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupeInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        instance.setGroupe(groupeInstance);
        Personne instanceNull = null;
        assertFalse(instance.equals(instanceNull));
        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of equals method, of class Personne.
     */
    @Test
    public void testEqualsNotSameValues() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        Personne instance = controller.getPersonne(expInstance);
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupeInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        instance.setGroupe(groupeInstance);

        Personne instanceNotSamePrenom = new Personne(instance.getUuid(), instance.getNom(), instance.getPrenom(),
                instance.getEmail(), instance.getGroupe());
        instanceNotSamePrenom.setPrenom("Pierre");
        assertFalse(expInstance.equals(instanceNotSamePrenom));

        Personne instanceNotSameNom = new Personne(instance.getUuid(), instance.getNom(), instance.getPrenom(),
                instance.getEmail(), instance.getGroupe());
        instanceNotSameNom.setNom("Pont");
        assertFalse(expInstance.equals(instanceNotSameNom));

        Personne instanceNotSameEmail = new Personne(instance.getUuid(), instance.getNom(), instance.getPrenom(),
                instance.getEmail(), instance.getGroupe());
        instanceNotSameEmail.setEmail("pierre.pont@rpn.ch");
        assertFalse(expInstance.equals(instanceNotSameEmail));

        Personne instanceNotSameUuid = new Personne(new UUID(6,6),instance.getNom(),
                instance.getPrenom(),instance.getEmail(),instance.getGroupe());
        assertFalse(expInstance.equals(instanceNotSameUuid));

        Personne instanceNotSameGroupe = new Personne(instance.getUuid(), instance.getNom(), instance.getPrenom(),
                instance.getEmail(), instance.getGroupe());
        instanceNotSameGroupe.setGroupe(insert.getPersonnes().get(new UUID(1, 1)).getGroupe());
        assertFalse(expInstance.equals(instanceNotSameGroupe));

        controller.setApplicationState(Controller.State.INIT);
    }

    /**
     * Test of equals method, of class Personne.
     */
    @Test
    public void testEqualsNotSameClass() throws SQLException, IOException, ClassNotFoundException {
        controller.setApplicationState(Controller.State.GESTION_PERSONNE);
        Personne expInstance = insert.getPersonnes().get(new UUID(3, 3));
        controller.setApplicationState(Controller.State.GESTION_GROUPE);
        Groupe groupeInstance = insert.getPersonnes().get(new UUID(3, 3)).getGroupe();
        assertFalse(expInstance.equals(groupeInstance));
        controller.setApplicationState(Controller.State.INIT);
    }
}
