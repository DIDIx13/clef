/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ch.esne.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ch.esne.util.DemoData;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class AccesTest {

    DemoData demoData;

    public AccesTest() {
    }

    @Before
    public void setUp() {
        demoData.getDemoData();
    }

    @After
    public void tearDown() {
        demoData = null;
    }

    @Test
    public void createAccesWithoutUuid() {
        Acces acces = new Acces(LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        assertEquals(LocalDateTime.MAX, acces.getDateDebut());
    }

    /**
     * Test of getUuid method, of class Acces.
     */
    @Test
    public void testGetUuid() {
        System.out.println("getUuid");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        UUID expResult = new UUID(0, 0);
        UUID result = instance.getUuid();
        assertEquals(expResult, result);

    }

    /**
     * Test of setUuid method, of class Acces.
     */
    @Test
    public void testSetUuid() {
        System.out.println("setUuid");
        UUID uuid = new UUID(0, 1);
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        instance.setUuid(uuid);
        assertEquals(uuid, instance.getUuid());

    }

    /**
     * Test of getDateDebut method, of class Acces.
     */
    @Test
    public void testGetDateDebut() {
        System.out.println("getDateDebut");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        LocalDateTime expResult = LocalDateTime.MAX;
        LocalDateTime result = instance.getDateDebut();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDateDebut method, of class Acces.
     */
    @Test
    public void testSetDateDebut() {
        System.out.println("setDateDebut");
        LocalDateTime dateDebut;
        dateDebut = LocalDateTime.MIN;
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        instance.setDateDebut(dateDebut);
        assertEquals(instance.getDateDebut(), dateDebut);
    }

    /**
     * Test of getDateFin method, of class Acces.
     */
    @Test
    public void testGetDateFin() {
        System.out.println("getDateFin");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        LocalDateTime expResult = LocalDateTime.MIN;
        LocalDateTime result = instance.getDateFin();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateFin method, of class Acces.
     */
    @Test
    public void testSetDateFin() {
        System.out.println("setDateFin");
        LocalDateTime dateFin = LocalDateTime.MAX;
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        instance.setDateFin(dateFin);
        assertEquals(instance.getDateFin(), dateFin);
    }

    /**
     * Test of getLieux method, of class Acces.
     */
    @Test
    public void testGetLieux() {
        System.out.println("getLieux");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        String expResult = new Lieu("Test").getNom();
        String result = instance.getLieu().getNom();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLieux method, of class Acces.
     */
    @Test
    public void testSetLieux() {
        System.out.println("setLieux");
        Lieu lieux = new Lieu("Test1");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        instance.setLieu(lieux);
        assertEquals(lieux, instance.getLieu());
    }

    /**
     * Test of hashCode method, of class Acces.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        int expResult = instance.hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Acces.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        assertTrue(instance.equals(instance)); //Test same object

        Lieu l = new Lieu("Test");
        obj = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, l);
        instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN,l);
        expResult = true;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new UUID(0, 0);
        instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, l);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, l);
        instance = new Acces(new UUID(1, 0), LocalDateTime.MAX, LocalDateTime.MIN,l);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Acces(new UUID(0, 0), LocalDateTime.MIN, LocalDateTime.MIN, l);
        instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, l);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MAX, l);
        instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, l);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, l);
        instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu("Test"));
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
    

    /**
     * Test of toString method, of class Acces.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Acces instance = new Acces(new UUID(0, 0), LocalDateTime.MAX, LocalDateTime.MIN, new Lieu(new UUID(0, 0),"Test"));
        String expResult = "Acces{uuid=00000000-0000-0000-0000-000000000000, dateDebut=+999999999-12-31T23:59:59.999999999, dateFin=-999999999-01-01T00:00, lieux=Lieu{uuid=00000000-0000-0000-0000-000000000000, nom='Test', lieuParent=null, nombre de serrures=0}}";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
