package ch.esne.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class SerrureTest {
    private UUID uuid;
    private Cardinalite cardinalite;
    private Lieu lieu;

    /* À récupérer depuis DemoData */
    private Serrure serrure315NORD;
    private Serrure serrure315SUD;
    private Serrure serrureB101EST;
    private Serrure serrureB102EST;

    private Lieu CIFOM;
    private Lieu CIFOM_Etage_3;
    private Lieu CIFOM_Etage_3_315;

    private Lieu CPLN;
    private Lieu CPLN_Etage_1;
    private Lieu CPLN_Etage_1_B101;
    private Lieu CPLN_Etage_1_B102;

    private Personne personne2;

    private Clef clef1;

    private Registre registre1;
    Timestamp timestampDebut;
    Timestamp timestampFin;

    private HistoriqueUtilisation historiqueUtilisation1;
    private HistoriqueUtilisation historiqueUtilisation2;
    private ArrayList<HistoriqueUtilisation> historiqueUtilisations;


    @Before
    public void setUp() throws Exception {
        personne2 = new Personne("Costa","Diogo","diogo.paivacosta@rpn.ch");

        timestampDebut = new Timestamp(2021, 12, 31,3, 45, 0, 0);
        timestampFin = new Timestamp(2022, 8, 6,7, 45, 0, 0);
        registre1 = new Registre(UUID.randomUUID(),timestampDebut,timestampFin,personne2);

        clef1 = new Clef("0000-0000-0000-0001",ClefStatus.ACTIVE);
        clef1.addRegistre(registre1);

        historiqueUtilisation1 = new HistoriqueUtilisation(clef1,true);
        historiqueUtilisation2 = new HistoriqueUtilisation(clef1,false);

        historiqueUtilisations = new ArrayList<>(1);
        historiqueUtilisations.add(historiqueUtilisation2);

        CIFOM = new Lieu("CIFOM");
        CIFOM_Etage_3 = new Lieu("Etage 3", CIFOM);
        CIFOM_Etage_3_315 = new Lieu("315", CIFOM_Etage_3);

        CPLN = new Lieu(uuid, "CPLN");
        CPLN_Etage_1 = new Lieu("Etage 1", CPLN);
        CPLN_Etage_1_B101 = new Lieu("B101", CPLN_Etage_1);
        CPLN_Etage_1_B102= new Lieu("B102", CPLN_Etage_1);

        serrure315NORD = new Serrure(Cardinalite.NORD);
        serrure315SUD = new Serrure(Cardinalite.SUD);
        serrureB101EST = new Serrure(Cardinalite.EST);
        serrureB102EST = new Serrure(Cardinalite.EST,historiqueUtilisations); //avec arraylist

        serrureB101EST.addHistoriqueUtilisation(historiqueUtilisation1);

    }

    @After
    public void tearDown() throws Exception {
        serrure315NORD = null;
        serrure315SUD = null;
        serrureB101EST = null;
        serrureB102EST = null;
    }

    /**
     * Test le constructeur avec un UUID ArrayList
     */
    @Test
    public void createWithUuidAndArrayList() {
        UUID uuidCreate = UUID.randomUUID();
        historiqueUtilisation1 = new HistoriqueUtilisation(clef1,true);
        historiqueUtilisation2 = new HistoriqueUtilisation(clef1,false);

        ArrayList <HistoriqueUtilisation> historiqueUtilisationsCreate = new ArrayList<>(2);
        historiqueUtilisationsCreate.add(historiqueUtilisation1);
        historiqueUtilisationsCreate.add(historiqueUtilisation2);

        Serrure serrureB102NORD = new Serrure(uuidCreate, Cardinalite.NORD,historiqueUtilisationsCreate);
        assertEquals(uuidCreate, serrureB102NORD.getUuid());
    }

    /**
     * Test la récupération d'un UUID
     */
    @Test
    public void getUuid() {
        uuid = UUID.randomUUID();
        Serrure Serrure1 = new Serrure(uuid, Cardinalite.NORD);
        Assert.assertEquals(uuid, Serrure1.getUuid());
    }

    /**
     * Test la récupération de la cardinalité
     */
    @Test
    public void getCardinalite() {
        cardinalite = Cardinalite.NORD;
        Assert.assertEquals(cardinalite, serrure315NORD.getCardinalite());
    }

    /**
     * Test la modification de la cardinalité
     */
    @Test
    public void setCardinalite() {
        cardinalite = Cardinalite.OUEST;
        serrure315SUD.setCardinalite(cardinalite);
        Assert.assertEquals(cardinalite, serrure315SUD.getCardinalite());
    }

    /**
     * Test l'ajout d'un historique
     */
    @Test
    public void addHistorique() {
        Clef clefAdd = new Clef("0000-0000-0000-0001",ClefStatus.ACTIVE);
        clefAdd.addRegistre(registre1);
        HistoriqueUtilisation historiqueUtilisationAdd = new HistoriqueUtilisation(clefAdd,true);
        serrure315SUD.addHistoriqueUtilisation(historiqueUtilisationAdd);
        assertEquals(historiqueUtilisationAdd, serrure315SUD.getLastHistoriqueUtilisation());
    }

    /**
     * Test l'ajout de deux même historiques
     */
    @Test(expected = RuntimeException.class)
    public void addTwoSameHistorique() {
        Clef clefAdd = new Clef("0000-0000-0000-0001",ClefStatus.ACTIVE);
        clefAdd.addRegistre(registre1);
        HistoriqueUtilisation historiqueUtilisationAdd = new HistoriqueUtilisation(clefAdd,true);
        serrure315SUD.addHistoriqueUtilisation(historiqueUtilisationAdd);
        serrure315SUD.addHistoriqueUtilisation(historiqueUtilisationAdd);
        assertEquals(historiqueUtilisationAdd, serrure315SUD.getLastHistoriqueUtilisation());
    }

    /**
     * Test l'ajout d'un historique si la liste d'historiques est vide
     */
    @Test
    public void addHistoriqueIfHistoriquesVide() {
        Serrure serrureB102SUD = new Serrure(Cardinalite.SUD);
        Clef clefAdd = new Clef("0000-0000-0000-0001",ClefStatus.ACTIVE);
        clefAdd.addRegistre(registre1);
        HistoriqueUtilisation historiqueUtilisationAdd = new HistoriqueUtilisation(clefAdd,true);
        serrureB102SUD.addHistoriqueUtilisation(historiqueUtilisationAdd);
        assertEquals(historiqueUtilisationAdd, serrureB102SUD.getLastHistoriqueUtilisation());
    }

    /**
     * Test la récupération d'un historique
     */
    @Test
    public void getHistorique() {
        assertEquals(historiqueUtilisation1,serrureB101EST.getHistoriqueUtilisation(0));
    }

    /**
     * Test la récupération d'un historique si la liste d'historiques est vide
     */
    @Test(expected = RuntimeException.class)
    public void getHistoriqueSiHistoriquesVide() {
        Lieu CPLN_Etage_1_B108 = new Lieu("B108", CPLN_Etage_1);
        Serrure serrureB108SUD = new Serrure(Cardinalite.SUD);
        assertEquals(historiqueUtilisation1,serrureB108SUD.getHistoriqueUtilisation(0));
    }

    /**
     * Test la récupération du premier historique
     */
    @Test
    public void getFirstHistorique() {
        assertEquals(historiqueUtilisation1, serrureB101EST.getFirstHistoriqueUtilisation());
    }

    /**
     * Test la récupération du premier historique si la liste d'historiques est vide
     */
    @Test(expected = RuntimeException.class)
    public void getFirstHistoriqueSiHistoriquesVide() {
        Lieu CPLN_Etage_1_B108 = new Lieu("B108", CPLN_Etage_1);
        Serrure serrureB108SUD = new Serrure(Cardinalite.SUD);
        assertEquals(historiqueUtilisation1,serrureB108SUD.getFirstHistoriqueUtilisation());
    }

    /**
     * Test la récupération du dernier historique
     */
    @Test
    public void getLastHistorique() {
        serrureB101EST.addHistoriqueUtilisation(historiqueUtilisation2);
        assertEquals(historiqueUtilisation2,serrureB101EST.getLastHistoriqueUtilisation());
    }

    /**
     * Test la récupération du dernier historique si la liste des historiques est vide
     */
    @Test(expected = RuntimeException.class)
    public void getLastHistoriqueSiHistoriquesVide() {
        Lieu CPLN_Etage_1_B108 = new Lieu("B108", CPLN_Etage_1);
        Serrure serrureB108SUD = new Serrure(Cardinalite.SUD);
        assertEquals(historiqueUtilisation1,serrureB108SUD.getLastHistoriqueUtilisation());
    }

    /**
     * Test la récupération de tous les historiques
     */
    @Test
    public void getAllHistoriques() {
        ArrayList<HistoriqueUtilisation> arrayList = new ArrayList<>(1);
        arrayList.add(historiqueUtilisation1);
        assertEquals(arrayList,serrureB101EST.getAllHistoriqueUtilisation());
    }

    /**
     * Test le equals
     */
    @Test
    public void testEquals() {
        Assert.assertEquals(false, serrure315NORD.equals(serrure315SUD));
    }

    /**
     * Test le hashcode
     */
    @Test
    public void testHashCode() {
        Serrure Serrure3 = new Serrure(serrure315SUD.getUuid(), serrure315SUD.getCardinalite());
        Assert.assertNotSame(serrure315SUD, Serrure3);
        Assert.assertEquals(serrure315SUD.hashCode(), Serrure3.hashCode());
    }

    /**
     * Test le ToString
     */
   @Test
    public void testToString() {
        String resultat = "Serrure{uuid=" + serrureB102EST.getUuid() + ", cardinalite=" + serrureB102EST.getCardinalite()
                + ", nombre d'historiques=" + serrureB102EST.getAllHistoriqueUtilisation().size() + "}";
        Assert.assertEquals(resultat, serrureB102EST.toString());
    }

    /**
     * Test si la liste des historiques est vide
     */
    @Test
    public void isEmpty() {
        Serrure serrureB102NORD = new Serrure(Cardinalite.NORD);
        assertTrue(serrureB102NORD.isEmpty());
    }
}