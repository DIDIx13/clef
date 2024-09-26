/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://mylos.cifom.ch/gitlab/projet_prog-bdd/prog_carnet_adresse/-/tree/dev to go to the project
 *  @author ToblerC <cyril.tobler@icloud.com>
 */
package ch.esne.domain;

import java.sql.Timestamp;
import java.util.UUID;
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
public class RegistreTest {

    public RegistreTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getUuid method, of class Registre.
     */
    @Test
    public void testGetUuid() {
        System.out.println("getUuid");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        UUID expResult = instance.getUuid();
        UUID result = instance.getUuid();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDate_debut method, of class Registre.
     */
    @Test
    public void testGetDate_debut() {
        System.out.println("getDate_debut");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        Timestamp expResult = new Timestamp(0);
        Timestamp result = instance.getDateDebut();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDate_debut method, of class Registre.
     */
    @Test
    public void testSetDate_debut() {
        System.out.println("setDate_debut");
        Timestamp date_debut = null;
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        instance.setDateDebut(date_debut);

    }

    /**
     * Test of getDate_fin method, of class Registre.
     */
    @Test
    public void testGetDate_fin() {
        System.out.println("getDate_fin");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        Timestamp expResult = null;
        Timestamp result = instance.getDateFin();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDate_fin method, of class Registre.
     */
    @Test
    public void testSetDate_fin() {
        System.out.println("setDate_fin");
        Timestamp date_fin = null;
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        instance.setDateFin(date_fin);

    }

    /**
     * Test of getPersonne method, of class Registre.
     */
    @Test
    public void testGetPersonne() {
        System.out.println("getPersonne");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        Personne expResult = instancep;
        Personne result = instance.getPersonne();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPersonne method, of class Registre.
     */
    @Test
    public void testSetPersonne() {
        System.out.println("setPersonne");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);;
        instance.setPersonne(instancep);

    }

    /**
     * Test of toString method, of class Registre.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Personne instancep = new Personne(new UUID(0,0),"nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Registre.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Registre.
     */
    @Test
    public void testEqualsNull() {
        System.out.println("equals");
        Object obj = null;
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Registre.
     */
    @Test
    public void testEqualsWrongClass() {
        System.out.println("equals");
        Object obj = new Lieu("Nom");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Registre.
     */
    @Test
    public void testEqualsWrongUUID() {
        System.out.println("equals");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        Object obj = new Registre(new UUID(0, 1), new Timestamp(0), instancep);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    
    /**
     * Test of equals method, of class Registre.
     */
    @Test
    public void testEqualsWrongDateDebut() {
        System.out.println("equals");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0), instancep);
        Object obj = new Registre(new UUID(0, 0), new Timestamp(1), instancep);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
        /**
     * Test of equals method, of class Registre.
     */
    @Test
    public void testEqualsWrongDateFin() {
        System.out.println("equals");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0),new Timestamp(0), instancep);
        Object obj = new Registre(new UUID(0, 0), new Timestamp(0),new Timestamp(1), instancep);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of equals method, of class Registre.
     */
    @Test
    public void testEqualsTrue() {
        System.out.println("equals");
        Personne instancep = new Personne("nom", "prenom", "email");
        Registre instance = new Registre(new UUID(0, 0), new Timestamp(0),new Timestamp(0), instancep);
        Object obj = new Registre(new UUID(0, 0), new Timestamp(0),new Timestamp(0), instancep);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
}
