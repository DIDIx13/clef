package ch.esne.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class HistoriqueUtilisationTest {

    private Personne personne1;
    private Personne personne2;
    private Clef clef1;
    private Clef clef2;
    private Registre registre1;
    private Registre registre2;
    String dateDebut;
    Timestamp timestampDebut;
    String dateFin;
    Timestamp timestampFin;
    private HistoriqueUtilisation historiqueUtilisation1;
    private HistoriqueUtilisation historiqueUtilisation2;
    private Timestamp dateUtilisation;


    @Before
    public void setUp() throws Exception {
        personne1 = new Personne("Ameli","Dariwn","darwin.ameli@rpn.ch");
        personne2 = new Personne("Costa","Diogo","diogo.paivacosta@rpn.ch");
        dateDebut = "2021-12-31 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-08-07 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        registre1 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne2);
        dateDebut = "2020-7-31 03:45:00";
        timestampDebut = Timestamp.valueOf(dateDebut);
        dateFin = "2022-11-08 07:45:00";
        timestampFin = Timestamp.valueOf(dateFin);
        registre1 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne1);
        clef1 = new Clef("0000-0000-0000-0001",ClefStatus.ACTIVE);
        clef2 = new Clef("0000-0000-0000-0002",ClefStatus.ACTIVE);
        clef1.addRegistre(registre1);
        clef2.addRegistre(registre2);
        historiqueUtilisation1 = new HistoriqueUtilisation(clef1,true);
        historiqueUtilisation2 = new HistoriqueUtilisation(clef2,false);
    }

    @After
    public void tearDown() throws Exception {
        historiqueUtilisation1 = null;
        historiqueUtilisation2 = null;
    }

    /**
     * Test la récupération de la clef
     */
    @Test
    public void getClef() {
        assertEquals(clef1,historiqueUtilisation1.getClef());
    }

    /**
     * Test la récupération du status
     */
    @Test
    public void getStatus() {
        assertEquals(true,historiqueUtilisation1.getStatus());
    }

    /**
     * Test la récupération de la date d'utilisation
     */
    @Test
    public void getDateUtilisation() {
        Timestamp tmp = historiqueUtilisation1.getDateUtilisation();//impossible à vérifier autrement, car cela est fait en live
        assertEquals(tmp,historiqueUtilisation1.getDateUtilisation());
    }

    /**
     * Test equals si ce n'est pas le même historique
     */
    @Test
    public void testEqualsFalse() {
        HistoriqueUtilisation historiqueUtilisationFalse = new HistoriqueUtilisation(clef1,false);
        assertFalse(historiqueUtilisationFalse.equals(historiqueUtilisation1));
    }

    /**
     * Test equals avec le même historique
     */
    @Test
    public void testEquals() {
        assertTrue(historiqueUtilisation1.equals(historiqueUtilisation1));
    }

    /**
     * Test equals avec un objet d'un type différent
     */
    @Test
    public void testEqualsFalseInstance() {
        Integer intEqualsFalse = 12;
        assertFalse(historiqueUtilisation1.equals(intEqualsFalse));
    }

    /**
     * Test le hashcode
     */
    @Test
    public void testHashCode() {
        assertEquals(historiqueUtilisation1.hashCode(),historiqueUtilisation1.hashCode());
    }

    /**
     * Test le ToString
     */
    @Test
    public void testToString() {
        assertEquals("HistoriqueUtilisation{clef=0000-0000-0000-0001, status=true, dateUtilisation=" + historiqueUtilisation1.getDateUtilisation() + "}",historiqueUtilisation1.toString());
    }
}