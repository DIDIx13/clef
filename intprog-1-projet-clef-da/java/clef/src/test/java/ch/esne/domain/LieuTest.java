/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://mylos.cifom.ch/gitlab/projet_prog-bdd/prog_carnet_adresse/-/tree/dev to go to the project
 *  @author ToblerC <cyril.tobler@icloud.com>
 */
package ch.esne.domain;

import org.junit.*;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 *
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class LieuTest {

    private UUID uuid;
    private String nom;

    /* À récupérer depuis DemoData */
    private Lieu CIFOM;
    private Lieu CIFOM_Etage_3;
    private Lieu CIFOM_Etage_3_315;

    private Lieu CPLN;
    private Lieu CPLN_Etage_1;
    private Lieu CPLN_Etage_1_B101;
    private Lieu CPLN_Etage_1_B102;
    private Lieu CPLN_Etage_1_B104;

    private Serrure serrure315NORD;
    private Serrure serrureB101EST;
    private Serrure serrureB104EST;

    private ArrayList<Serrure> serrures;

    public LieuTest() {
    }

    @Before
    public void setUp() {

        serrure315NORD = new Serrure(Cardinalite.NORD);
        serrureB101EST = new Serrure(Cardinalite.EST);
        serrureB104EST = new Serrure(Cardinalite.EST);
        serrures = new ArrayList<>();
        serrures.add(serrureB104EST);

        CIFOM = new Lieu("CIFOM");
        CIFOM_Etage_3 = new Lieu("Etage 3", CIFOM);
        CIFOM_Etage_3_315 = new Lieu("315", CIFOM_Etage_3);
        uuid = UUID.randomUUID();
        CPLN = new Lieu(uuid, "CPLN");
        CPLN_Etage_1 = new Lieu("Etage 1", CPLN);
        CPLN_Etage_1_B101 = new Lieu("B101", CPLN_Etage_1);
        CPLN_Etage_1_B102 = new Lieu("B102", CPLN_Etage_1);
        CPLN_Etage_1_B104 = new Lieu("B104", CPLN_Etage_1, serrures);


        CPLN_Etage_1_B101.addSerrure(serrureB101EST);
    }

    @After
    public void tearDown() {
        CIFOM = null;
        CIFOM_Etage_3 = null;
        CIFOM_Etage_3_315 = null;
        CPLN = null;
        CPLN_Etage_1 = null;
        CPLN_Etage_1_B101 = null;
        CPLN_Etage_1_B102 = null;
    }

    /**
     * Test le constructeur avec un uuid et une arraylist de serrure
     */
    @Test
    public void createWithUuidAndArrayList() {
        UUID uuidCreate = UUID.randomUUID();
        Lieu CPLN_Etage_1_B110 = null;
        ArrayList<Serrure> serrures2 = new ArrayList<>();
        Serrure serrureB110EST = new Serrure(Cardinalite.EST);
        serrures2.add(serrureB110EST);
        CPLN_Etage_1_B110 = new Lieu(uuidCreate,"B110",CPLN_Etage_1,serrures2);
        assertEquals(uuidCreate, CPLN_Etage_1_B110.getUuid());
    }

    /**
     * Test la récupération de l'UUID
     */
    @Test
    public void getUuid() {
        uuid = UUID.randomUUID();
        Lieu CIFOM = new Lieu(uuid, "CIFOM", null);
        Assert.assertEquals(uuid, CIFOM.getUuid());
    }

    /**
     * Test la récupération du nom
     */
    @Test
    public void getNom() {
        nom = "315";
        Assert.assertEquals(nom, CIFOM_Etage_3_315.getNom());
    }

    /**
     * Test la modification du nom
     */
    @Test
    public void setNom() {
        nom = "301";
        CIFOM_Etage_3_315.setNom(nom);
        Assert.assertEquals(nom, CIFOM_Etage_3_315.getNom());
    }

    /**
     * Test la récupération du lieu parent
     */
    @Test
    public void getLieu_parent() {
        Lieu lieu_parent = CIFOM;
        Assert.assertEquals(lieu_parent, CIFOM_Etage_3.getLieuParent());
    }

    /**
     * Test la modification du lieu parent
     */
    @Test
    public void setLieu_parent() {
        CIFOM_Etage_3_315.setLieuParent(CPLN);
        Assert.assertEquals(CPLN, CIFOM_Etage_3_315.getLieuParent());
    }

    /**
     * Test la modification du lieu parent qui est déjà celui-ci
     */
    @Test(expected = AssertionError.class)
    public void setSameLieuParent() {
        CIFOM_Etage_3_315.setLieuParent(CIFOM_Etage_3);
        Assert.assertEquals(CPLN, CIFOM_Etage_3_315.getLieuParent());
    }

    /**
     * Test equals
     */
    @Test
    public void testEquals() {
        Assert.assertEquals(false, CPLN_Etage_1_B101.equals(CPLN_Etage_1_B102));

    }

    /**
     * Test le equals si c'est null
     */
    @Test
    public void testEqualsNull() {
        Lieu LieuEqualsFalse = null;
        assertFalse(CIFOM.equals(LieuEqualsFalse));
    }

    /**
     * Test le equals avec le même lieu
     */
    @Test
    public void testEqualsSameLieu() {
        Assert.assertEquals(true, CPLN_Etage_1_B101.equals(CPLN_Etage_1_B101));
    }

    /**
     * Test equals avec un objet d'un autre type
     */
    @Test
    public void testEqualsFalseInstance() {
        Integer intEqualsFalse = 12;
        assertFalse(CPLN_Etage_1.equals(intEqualsFalse));
    }

    /**
     * Test du hashcode
     */
    @Test
    public void testHashCode() {
        Lieu CPLN_Etage_3 = new Lieu(CIFOM_Etage_3.getUuid(), CIFOM_Etage_3.getNom(), CIFOM_Etage_3.getLieuParent());
        Assert.assertNotSame(CIFOM_Etage_3, CPLN_Etage_3);
        Assert.assertEquals(CIFOM_Etage_3.hashCode(), CPLN_Etage_3.hashCode());
    }

    /**
     * Test du ToString
     */
    @Test
    public void testToString() {
        String resultat = "Lieu{uuid=" + CIFOM_Etage_3_315.getUuid() + ", nom='" + CIFOM_Etage_3_315.getNom()
                + "', lieuParent=" + CIFOM_Etage_3_315.getLieuParent() + ", nombre de serrures="
                + CIFOM_Etage_3_315.getAllSerrures().size() + "}";
        Assert.assertEquals(resultat,CIFOM_Etage_3_315.toString());
    }

    /**
     * Test du constructeur avec nom uniquement
     */
    @Test
    public void testCreationLieuAvecNom() {
        Lieu CPLN_Etage_1 = new Lieu("Etage 1");
        Assert.assertEquals("Etage 1", CPLN_Etage_1.getNom());
    }

    /**
     * Test du constructeur avec nom et son lieu parent
     */
    @Test
    public void testCreationLieuAvecNomEtParent() {
        Lieu CPLN_Etage_1_B101 = new Lieu("B101", CPLN_Etage_1);
        Assert.assertEquals("B101", CPLN_Etage_1_B101.getNom());
        Assert.assertEquals(CPLN_Etage_1, CPLN_Etage_1_B101.getLieuParent());
    }

    /**
     * Test du constructeur avec UUID, nom et lieu parent
     */
    @Test
    public void testCreationLieuAvecNomEtParentEtUUID() {
        uuid = UUID.randomUUID();
        CPLN_Etage_1_B102 = new Lieu(uuid,"B102", CPLN_Etage_1);
        Assert.assertEquals("B102", CPLN_Etage_1_B102.getNom());
        Assert.assertEquals(CPLN_Etage_1, CPLN_Etage_1_B102.getLieuParent());
        Assert.assertEquals(uuid, CPLN_Etage_1_B102.getUuid());
    }

    /**
     * Test modification de lieu parent en mettant le lieu-même
     */
    @Test (expected = IllegalArgumentException.class)
    public void testParentEnfantNonEgal() {
        CIFOM_Etage_3.setLieuParent(CIFOM_Etage_3);
    }

    /**
     * Test si on peut mettre un lieu parent qui a le même nom que notre lieu
     */
    @Test (expected = IllegalArgumentException.class)
    public void testNomParentNonEgal() {
        CIFOM_Etage_3.setNom("CIFOM");
        CIFOM_Etage_3.setLieuParent(CIFOM);
    }

    /**
     * Test l'ajout d'une serrure
     */
    @Test
    public void addSerrure() {
        Serrure serrureB101SUD = new Serrure(Cardinalite.SUD);
        CPLN_Etage_1_B101.addSerrure(serrureB101SUD);
        assertEquals(serrureB101SUD, CPLN_Etage_1_B101.getLastSerrure());
    }

    /**
     * Test l'ajout de deux même serrures
     */
    @Test(expected = RuntimeException.class)
    public void addTwoSameSerrure() {
        Serrure serrureB102SUD = new Serrure(Cardinalite.SUD);
        CPLN_Etage_1_B102.addSerrure(serrureB102SUD);
        CPLN_Etage_1_B102.addSerrure(serrureB102SUD);
        assertEquals(serrureB102SUD, CPLN_Etage_1_B102.getLastSerrure());
    }

    /**
     * Test l'ajout d'une serrure si la liste de serrures est vide
     */
    @Test
    public void addSerrureIfSerruresVide() {
        Serrure serrureB102SUD = new Serrure(Cardinalite.SUD);
        CPLN_Etage_1_B102.addSerrure(serrureB102SUD);
        assertEquals(serrureB102SUD, CPLN_Etage_1_B102.getLastSerrure());
    }

    /**
     * Test la récupération d'une serrure
     */
    @Test
    public void getSerrure() {
        assertEquals(serrureB101EST,CPLN_Etage_1_B101.getSerrure(0));
    }

    /**
     * Test la récupération d'une serrure si la liste des serrures est vide
     */
    @Test(expected = RuntimeException.class)
    public void getSerrureSiSerruresVide() {
        Lieu CPLN_Etage_1_B103 = new Lieu("B103", CPLN_Etage_1);
        assertEquals(serrureB101EST,CPLN_Etage_1_B103.getSerrure(0));
    }

    /**
     * Test la récupération d'une serrure si l'index n'est pas correct
     */
    @Test(expected = RuntimeException.class)
    public void getSerrureSiIndexFaux() {
        Lieu CPLN_Etage_1_B103 = null;
        Serrure serrureB103EST = new Serrure(Cardinalite.EST);
        ArrayList<Serrure> serrures2 = new ArrayList<>(5);
        serrures2.add(serrureB103EST);
        CPLN_Etage_1_B103 = new Lieu("B103", CPLN_Etage_1,serrures2);
        assertEquals(serrureB103EST,CPLN_Etage_1_B103.getSerrure(4));
    }

    /**
     * Test la récupération de la première serrure
     */
    @Test
    public void getFirstSerrure() {
        Serrure serrureB101SUD = new Serrure(Cardinalite.SUD);
        CPLN_Etage_1_B101.addSerrure(serrureB101SUD);
        assertEquals(serrureB101EST, CPLN_Etage_1_B101.getFirstSerrure());
    }

    /**
     * Test la récupération de la première serrure si la liste de serrures est vide
     */
    @Test(expected = RuntimeException.class)
    public void getFirstSerrureSiSerruresVide() {
        Lieu CPLN_Etage_1_B103 = new Lieu("B103", CPLN_Etage_1);
        assertEquals(serrureB101EST,CPLN_Etage_1_B103.getFirstSerrure());
    }

    /**
     * Test la récupération de la dernière serrure
     */
    @Test
    public void getLastSerrure() {
        Serrure serrureB101SUD = new Serrure(Cardinalite.SUD);
        CPLN_Etage_1_B101.addSerrure(serrureB101SUD);
        assertEquals(serrureB101SUD, CPLN_Etage_1_B101.getLastSerrure());
    }

    /**
     * Test la récupération de la dernière serrure si la liste de serrures est vide
     */
    @Test(expected = RuntimeException.class)
    public void getLastSerrureSiSerruresVide() {
        Lieu CPLN_Etage_1_B103 = new Lieu("B103", CPLN_Etage_1);
        assertEquals(serrureB101EST,CPLN_Etage_1_B103.getLastSerrure());
    }

    /**
     * Test la récupération de toutes les serrures
     */
    @Test
    public void getAllSerrures() {
        ArrayList<Serrure> arrayList = new ArrayList<>(2);
        arrayList.add(serrureB101EST);
        assertEquals(arrayList,CPLN_Etage_1_B101.getAllSerrures());
    }

    /**
     * Test si la liste de serrures est vide
     */
    @Test
    public void isEmpty() {
        Lieu CPLN_Etage_1_B103 = new Lieu("B103", CPLN_Etage_1);
        assertTrue(CPLN_Etage_1_B103.isEmpty());
    }
}
