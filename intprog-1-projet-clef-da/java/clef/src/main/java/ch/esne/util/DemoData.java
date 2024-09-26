package ch.esne.util;

// Peut être résumer avec *
// import ch.esne.domain.*
import ch.esne.domain.Acces;
import ch.esne.domain.Cardinalite;
import ch.esne.domain.Clef;
import ch.esne.domain.ClefStatus;
import ch.esne.domain.Groupe;
import ch.esne.domain.HistoriqueUtilisation;
import ch.esne.domain.Lieu;
import ch.esne.domain.Personne;
import ch.esne.domain.Registre;
import ch.esne.domain.Serrure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.UUID;
//CHECKSTYLE.OFF: MagicNumber
public class DemoData {
    private final HashMap<UUID, Acces> acces;
    private final HashMap<String, Clef> clefs;
    private final HashMap<UUID, Groupe> groupes;
    private final HashMap<UUID, Lieu> lieux;
    private final HashMap<UUID, Personne> personnes;
    private final HashMap<UUID, Registre> registres;
    private final HashMap<UUID, Serrure> serrures;
    private final HashMap<Clef, HistoriqueUtilisation> historiques;

    /* Création des objets */
    private Acces accesCplnEtudiants;
    private Acces accesCifomEtudiants;
    private Acces accesCplnEtudiantsCpln;
    private Acces accesCifomEtudiantsCifom;
    private Acces accesCplnEnseignants;
    private Acces accesCifomEnseignants;
    private Acces accesCplnEnseignantsprog;
    private Acces accesCifomEnseignantsprog;
    private Acces accesCplnDirection;
    private Acces accesCifomDirection;
    private Acces accesCplnDirectionCpln;
    private Acces accesCifomDirectionCifom;
    private Acces accesCplnConciergerie;
    private Acces accesCifomConciergerie;
    private Acces accesCplnConciergerieCpln;
    private Acces accesCifomConciergerieCifom;

    private Clef clef1;
    private Clef clef2;
    private Clef clef3;
    private Clef clef4;
    private Clef clef5;
    private Clef clef6;
    private Clef clef7;
    private Clef clef8;

    /* Groupes persos */
    private Groupe persoDarwinAmeli;
    private Groupe persoDiogoPaivaCosta;
    private Groupe persoCyrilTobler;
    private Groupe persoCedricCaillet;
    private Groupe persoDominiqueHuguenin;

    /* Groupes globlaux */
    private Groupe etudiants;
    private Groupe etudiantsCifom;
    private Groupe etudiantsCpln;

    private Groupe enseignants;
    private Groupe enseignantsProg;
    private Groupe direction;
    private Groupe directionCpln;
    private Groupe directionCifom;
    private Groupe conciergerie;
    private Groupe conciergerieCpln;
    private Groupe conciergerieCifom;

    private Lieu cifom;
    private Lieu cifomEtage1;
    private Lieu cifomEtage2;
    private Lieu cifomEtage3;
    private Lieu cifomEtage3Salle315;
    private Lieu cpln;
    private Lieu cplnEtage1;
    private Lieu cplnEtage1SalleB101;
    private Lieu cplnEtage1SalleB102;
    private Lieu cplnEtage2;
    private Lieu cplnEtage2SalleB205;
    private Lieu cplnEtage2SalleB206;
    private Lieu cplnEtage3;
    private Lieu cplnEtage3SalleB302;
    private Lieu cplnEtage3SalleB303;
    private Lieu cplnEtage4;
    private Lieu cplnEtage4SalleB401;

    private Personne darwinAmeli;
    private Personne diogoPaivaCosta;
    private Personne cyrilTobler;
    private Personne cedricCaillet;
    private Personne dominiqueHuguenin;

    private Registre registre1;
    private Registre registre2;
    private Registre registre3;
    private Registre registre4;
    private Registre registre5;
    private Registre registre6;

    private Serrure serrureCIFOM315NORD;
    private Serrure serrureCIFOM315SUD;
    private Serrure serrureCPLNB101EST;
    private Serrure serrureCPLNB102EST;
    private Serrure serrureCPLNB205NORD;
    private Serrure serrureCPLNB206SUD;
    private Serrure serrureCPLNB302EST;
    private Serrure serrureCPLNB303NORD;
    private Serrure serrureCPLNB401EST;

    private HistoriqueUtilisation histoSerrureCIFOM315NORD;
    private HistoriqueUtilisation histoSerrureCIFOM315SUD;
    private HistoriqueUtilisation histoSerrureCPLNB101EST;
    private HistoriqueUtilisation histoSerrureCPLNB102EST;
    private HistoriqueUtilisation histoSerrureCPLNB205NORD;
    private HistoriqueUtilisation histoSerrureCPLNB206SUD;
    private HistoriqueUtilisation histoSerrureCPLNB302EST;
    private HistoriqueUtilisation histoSerrureCPLNB303NORD;
    private HistoriqueUtilisation histoSerrureCPLNB401EST;

    public DemoData() {
        this.acces = new HashMap<>();
        this.clefs = new HashMap<>();
        this.groupes = new HashMap<>();
        this.lieux = new HashMap<>();
        this.personnes = new HashMap<>();
        this.registres = new HashMap<>();
        this.serrures = new HashMap<>();
        this.historiques = new HashMap<>();
    }

    public static DemoData getDemoData() {
        DemoData stockage = new DemoData();
        stockage.initialisation();
        return stockage;
    }

    public void initialisation() {
        this.acces.clear();
        this.registres.clear();
        this.clefs.clear();
        this.groupes.clear();
        this.lieux.clear();
        this.personnes.clear();
        this.serrures.clear();
        this.historiques.clear();

        initLieux();
        initSerrures();
        initAcces();
        initClefs();
        initGroupes();
        initPersonnes();
        initHistoriques();
        initRegistres();
        addAccestoGroupe();
        addGroupetoGroupe();
        addHistoriquetoSerrure();
        addRegistretoClef();
        addSerruretoLieu();
    }

    private void initAcces() {
        accesCplnEtudiants = new Acces(new UUID(1, 1),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnEtudiants.getUuid(), accesCplnEtudiants);

        accesCifomEtudiants = new Acces(new UUID(2, 2),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCplnEtudiants.getUuid(), accesCplnEtudiants);

        accesCplnEtudiantsCpln = new Acces(new UUID(3, 3),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnEtudiantsCpln.getUuid(), accesCplnEtudiantsCpln);

        accesCifomEtudiantsCifom = new Acces(new UUID(4, 4),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomEtudiantsCifom.getUuid(), accesCifomEtudiantsCifom);

        accesCplnEnseignants = new Acces(new UUID(5, 5),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnEnseignants.getUuid(), accesCplnEnseignants);

        accesCifomEnseignants = new Acces(new UUID(6, 6),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomEnseignants.getUuid(), accesCifomEnseignants);

        accesCifomEnseignantsprog = new Acces(new UUID(7, 7),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomEnseignantsprog.getUuid(), accesCifomEnseignantsprog);

        accesCplnEnseignantsprog = new Acces(new UUID(7, 7),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnEnseignantsprog.getUuid(), accesCplnEnseignantsprog);

        accesCplnDirection = new Acces(new UUID(8, 8),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnDirection.getUuid(), accesCplnDirection);

        accesCifomDirection = new Acces(new UUID(9, 9),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomDirection.getUuid(), accesCifomDirection);

        accesCplnDirectionCpln = new Acces(new UUID(10, 10),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnDirectionCpln.getUuid(), accesCplnDirectionCpln);

        accesCifomDirectionCifom = new Acces(new UUID(11, 11),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomDirectionCifom.getUuid(), accesCifomDirectionCifom);

        accesCplnConciergerie = new Acces(new UUID(12, 12),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnConciergerie.getUuid(), accesCplnConciergerie);

        accesCifomConciergerie = new Acces(new UUID(13, 13),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomConciergerie.getUuid(), accesCifomConciergerie);

        accesCplnConciergerieCpln = new Acces(new UUID(14, 14),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cpln);
        acces.put(accesCplnConciergerieCpln.getUuid(), accesCplnConciergerieCpln);

        accesCifomConciergerieCifom = new Acces(new UUID(15, 15),
                LocalDateTime.of(2020, Month.MARCH, 1, 12, 30), LocalDateTime.of(2025, Month.MARCH, 1, 12, 30), cifom);
        acces.put(accesCifomConciergerieCifom.getUuid(), accesCifomConciergerieCifom);
    }

    private void initRegistres() {
        String dateDebut = "2021-12-31 03:45:00";
        Timestamp timestampDebut = Timestamp.valueOf(dateDebut);
        registre1 = new Registre(new UUID(1, 1), timestampDebut, cedricCaillet);
        registres.put(registre1.getUuid(), registre1);

        registre2 = new Registre(new UUID(2, 2), timestampDebut, cyrilTobler);
        registres.put(registre2.getUuid(), registre2);

        registre3 = new Registre(new UUID(3, 3), timestampDebut, diogoPaivaCosta);
        registres.put(registre3.getUuid(), registre3);

        registre4 = new Registre(new UUID(4, 4), timestampDebut, dominiqueHuguenin);
        registres.put(registre4.getUuid(), registre4);

        registre5 = new Registre(new UUID(5, 5), timestampDebut, darwinAmeli);
        registres.put(registre5.getUuid(), registre5);

        registre6 = new Registre(new UUID(6, 6), timestampDebut, null);
        registres.put(registre6.getUuid(), registre6);
    }

    private void initClefs() {
        clef1 = new Clef("0000-0000-0000-0001", ClefStatus.ACTIVE);
        clefs.put(clef1.getNumeroserie(), clef1);

        clef2 = new Clef("0000-0000-0000-0002", ClefStatus.ACTIVE);
        clefs.put(clef2.getNumeroserie(), clef2);

        clef3 = new Clef("0000-0000-0000-0003", ClefStatus.ACTIVE);
        clefs.put(clef3.getNumeroserie(), clef3);

        clef4 = new Clef("0000-0000-0000-0004", ClefStatus.ACTIVE);
        clefs.put(clef4.getNumeroserie(), clef4);

        clef5 = new Clef("0000-0000-0000-0005", ClefStatus.ACTIVE);
        clefs.put(clef5.getNumeroserie(), clef5);

        clef6 = new Clef("0000-0000-0000-0006", ClefStatus.DISFONCTIONNELLE);
        clefs.put(clef6.getNumeroserie(), clef6);

        clef7 = new Clef("0000-0000-0000-0007", ClefStatus.PERDUE);
        clefs.put(clef7.getNumeroserie(), clef7);

        clef8 = new Clef("0000-0000-0000-0008", ClefStatus.INACTIVE);
        clefs.put(clef8.getNumeroserie(), clef8);
    }

    private void initGroupes() {
        persoDarwinAmeli = new Groupe(new UUID(1, 1), "darwin.ameli@rpn.ch",
                "Groupe personnel");
        groupes.put(persoDarwinAmeli.getUuid(), persoDarwinAmeli);

        persoDiogoPaivaCosta = new Groupe(new UUID(2, 2), "diogo.paivacosta@rpn.ch",
                "Groupe personnel");
        groupes.put(persoDiogoPaivaCosta.getUuid(), persoDiogoPaivaCosta);

        persoCyrilTobler = new Groupe(new UUID(3, 3), "cyril.tobler@rpn.ch",
                "Groupe personnel");
        groupes.put(persoCyrilTobler.getUuid(), persoCyrilTobler);

        persoCedricCaillet = new Groupe(new UUID(4, 4), "cedric.caillet@rpn.ch",
                "Groupe personnel");
        groupes.put(persoCedricCaillet.getUuid(), persoCedricCaillet);

        persoDominiqueHuguenin = new Groupe(new UUID(5, 5), "dominique.huguenin@rpn.ch",
                "Groupe personnel");
        groupes.put(persoDominiqueHuguenin.getUuid(), persoDominiqueHuguenin);

        etudiants = new Groupe(new UUID(6, 6), "Etudiants",
        "Groupe contenant les etudiants des deux sites");
        groupes.put(etudiants.getUuid(), etudiants);

        etudiantsCpln = new Groupe(new UUID(7, 7), "Etudiants CPLN",
        "Groupe contenant les etudiants du CPLN");
        groupes.put(etudiantsCpln.getUuid(), etudiantsCpln);

        etudiantsCifom = new Groupe(new UUID(8, 8), "Etudiants CIFOM",
        "Groupe contenant les etudiants du CIFOM");
        groupes.put(etudiantsCifom.getUuid(), etudiantsCifom);

        enseignants = new Groupe(new UUID(9, 9), "Enseignants",
        "Groupe contenant tous les enseignants des deux sites");
        groupes.put(enseignants.getUuid(), enseignants);

        enseignantsProg = new Groupe(new UUID(10, 10), "Enseignants de prog",
        "Groupe contenant tous les enseignants de prog des deux sites");
        groupes.put(enseignantsProg.getUuid(), enseignantsProg);

        direction = new Groupe(new UUID(11, 11), "Direction",
        "Groupe contenant les membres de direction des deux sites");
        groupes.put(direction.getUuid(), direction);

        directionCpln = new Groupe(new UUID(12, 12), "Direction CPLN",
        "Groupe contenant les membres de direction du CPLN");
        groupes.put(directionCpln.getUuid(), directionCpln);

        directionCifom = new Groupe(new UUID(13, 13), "Direction CIFOM",
        "Groupe contenant les membres de direction du CIFOM");
        groupes.put(directionCifom.getUuid(), directionCifom);

        conciergerie = new Groupe(new UUID(14, 14), "Conciergerie",
        "Groupe contenant tout le personnel de conciergerie des deux sites");
        groupes.put(conciergerie.getUuid(), conciergerie);

        conciergerieCpln = new Groupe(new UUID(15, 15), "Conciergerie CPLN",
        "Groupe contenant tout le personnel de conciergerie du CPLN");
        groupes.put(conciergerieCpln.getUuid(), conciergerieCpln);

        conciergerieCifom = new Groupe(new UUID(16, 16), "Conciergerie CIFOM",
        "Groupe contenant tout le personnel de conciergerie du CIFOM");
        groupes.put(conciergerieCifom.getUuid(), conciergerieCifom);
    }

    private void initLieux() {
        /* CIFOM */
        cifom = new Lieu(new UUID(1, 1),
                "CIFOM");
        lieux.put(cifom.getUuid(), cifom);

        cifomEtage1 = new Lieu(new UUID(2, 2),
                "Etage 1", cifom);
        lieux.put(cifomEtage1.getUuid(), cifomEtage1);

        cifomEtage2 = new Lieu(new UUID(3, 3),
                "Etage 2", cifom);
        lieux.put(cifomEtage2.getUuid(), cifomEtage2);

        cifomEtage3 = new Lieu(new UUID(4, 4),
                "Etage 3", cifom);
        lieux.put(cifomEtage3.getUuid(), cifomEtage3);

        cifomEtage3Salle315 = new Lieu(new UUID(5, 5),
                "315", cifomEtage3);
        lieux.put(cifomEtage3Salle315.getUuid(), cifomEtage3Salle315);

        /* CPLN */
        cpln = new Lieu(new UUID(6, 6),
                "CPLN");
        lieux.put(cpln.getUuid(), cpln);

        cplnEtage1 = new Lieu(new UUID(7, 7),
                "Etage 1", cpln);
        lieux.put(cplnEtage1.getUuid(), cplnEtage1);

        cplnEtage2 = new Lieu(new UUID(8, 8),
                "Etage 2", cpln);
        lieux.put(cplnEtage2.getUuid(), cplnEtage2);

        cplnEtage3 = new Lieu(new UUID(9, 9),
                "Etage 3", cpln);
        lieux.put(cplnEtage3.getUuid(), cplnEtage3);

        cplnEtage4 = new Lieu(new UUID(10, 10),
                "Etage 4", cpln);
        lieux.put(cplnEtage4.getUuid(), cplnEtage4);

        cplnEtage1SalleB101 = new Lieu(new UUID(11, 11),
                "B101", cplnEtage1);
        lieux.put(cplnEtage1SalleB101.getUuid(), cplnEtage1SalleB101);

        cplnEtage1SalleB102 = new Lieu(new UUID(12, 12),
                "B102", cplnEtage1);
        lieux.put(cplnEtage1SalleB102.getUuid(), cplnEtage1SalleB102);

        cplnEtage2SalleB205 = new Lieu(new UUID(13, 13),
                "B205", cplnEtage2);
        lieux.put(cplnEtage2SalleB205.getUuid(), cplnEtage2SalleB205);

        cplnEtage2SalleB206 = new Lieu(new UUID(14, 14),
                "B206", cplnEtage2);
        lieux.put(cplnEtage2SalleB206.getUuid(), cplnEtage2SalleB206);

        cplnEtage3SalleB302 = new Lieu(new UUID(15, 15),
                "B302", cplnEtage3);
        lieux.put(cplnEtage3SalleB302.getUuid(), cplnEtage3SalleB302);

        cplnEtage3SalleB303 = new Lieu(new UUID(16, 16),
                "B303", cplnEtage3);
        lieux.put(cplnEtage3SalleB303.getUuid(), cplnEtage3SalleB303);

        cplnEtage4SalleB401 = new Lieu(new UUID(17, 17),
                "B401", cplnEtage4);
        lieux.put(cplnEtage4SalleB401.getUuid(), cplnEtage4SalleB401);
    }

    private void initPersonnes() {
        darwinAmeli = new Personne(new UUID(1, 1),
                "Ameli",
                "Darwin",
                "darwin.ameli@rpn.ch",
                persoDarwinAmeli);
        personnes.put(darwinAmeli.getUuid(), darwinAmeli);

        diogoPaivaCosta = new Personne(new UUID(2, 2),
                "Paiva Costa",
                "Diogo",
                "diogo.paivacosta@rpn.ch",
                persoDiogoPaivaCosta);
        personnes.put(diogoPaivaCosta.getUuid(), diogoPaivaCosta);

        cyrilTobler = new Personne(new UUID(3, 3),
                "Tobler",
                "Cyril",
                "cyril.tobler@rpn.ch",
                persoCyrilTobler);
        personnes.put(cyrilTobler.getUuid(), cyrilTobler);

        cedricCaillet = new Personne(new UUID(4, 4),
                "Caillet",
                "Cédric",
                "cedric.caillet@rpn.ch",
                persoCedricCaillet);
        personnes.put(cedricCaillet.getUuid(), cedricCaillet);

        dominiqueHuguenin = new Personne(new UUID(5, 5),
                "Huguenin",
                "Dominique",
                "dominique.huguenin@rpn.ch",
                persoDominiqueHuguenin);
        personnes.put(dominiqueHuguenin.getUuid(), dominiqueHuguenin);
    }

    private void initSerrures() {
        serrureCIFOM315NORD = new Serrure(new UUID(1, 1), Cardinalite.NORD);
        serrures.put(serrureCIFOM315NORD.getUuid(), serrureCIFOM315NORD);

        serrureCIFOM315SUD = new Serrure(new UUID(2, 2), Cardinalite.SUD);
        serrures.put(serrureCIFOM315SUD.getUuid(), serrureCIFOM315SUD);

        serrureCPLNB101EST = new Serrure(new UUID(3, 3), Cardinalite.EST);
        serrures.put(serrureCIFOM315SUD.getUuid(), serrureCIFOM315SUD);

        serrureCPLNB102EST = new Serrure(new UUID(4, 4), Cardinalite.EST);
        serrures.put(serrureCPLNB102EST.getUuid(), serrureCPLNB102EST);

        serrureCPLNB205NORD = new Serrure(new UUID(5, 5), Cardinalite.NORD);
        serrures.put(serrureCPLNB205NORD.getUuid(), serrureCPLNB205NORD);

        serrureCPLNB206SUD = new Serrure(new UUID(6, 6), Cardinalite.SUD);
        serrures.put(serrureCPLNB206SUD.getUuid(), serrureCPLNB206SUD);

        serrureCPLNB302EST = new Serrure(new UUID(7, 7), Cardinalite.EST);
        serrures.put(serrureCPLNB302EST.getUuid(), serrureCPLNB302EST);

        serrureCPLNB303NORD = new Serrure(new UUID(8, 8), Cardinalite.NORD);
        serrures.put(serrureCPLNB303NORD.getUuid(), serrureCPLNB303NORD);

        serrureCPLNB401EST = new Serrure(new UUID(9, 9), Cardinalite.EST);
        serrures.put(serrureCPLNB401EST.getUuid(), serrureCPLNB401EST);
    }

    private void initHistoriques() {
        histoSerrureCIFOM315NORD = new HistoriqueUtilisation(new UUID(1, 1), clef1, false);
        historiques.put(histoSerrureCIFOM315NORD.getClef(), histoSerrureCIFOM315NORD);

        histoSerrureCIFOM315SUD = new HistoriqueUtilisation(new UUID(2, 2), clef1, false);
        historiques.put(histoSerrureCIFOM315SUD.getClef(), histoSerrureCIFOM315SUD);

        histoSerrureCPLNB101EST = new HistoriqueUtilisation(new UUID(3, 3), clef2, false);
        historiques.put(histoSerrureCPLNB101EST.getClef(), histoSerrureCPLNB101EST);

        histoSerrureCPLNB102EST = new HistoriqueUtilisation(new UUID(4, 4), clef2, false);
        historiques.put(histoSerrureCPLNB101EST.getClef(), histoSerrureCPLNB101EST);

        histoSerrureCPLNB205NORD = new HistoriqueUtilisation(new UUID(5, 5), clef3, false);
        historiques.put(histoSerrureCPLNB205NORD.getClef(), histoSerrureCPLNB205NORD);

        histoSerrureCPLNB206SUD = new HistoriqueUtilisation(new UUID(6, 6), clef4, false);
        historiques.put(histoSerrureCPLNB206SUD.getClef(), histoSerrureCPLNB206SUD);

        histoSerrureCPLNB302EST = new HistoriqueUtilisation(new UUID(7, 7), clef4, false);
        historiques.put(histoSerrureCPLNB302EST.getClef(), histoSerrureCPLNB302EST);

        histoSerrureCPLNB303NORD = new HistoriqueUtilisation(new UUID(8, 8), clef5, false);
        historiques.put(histoSerrureCPLNB303NORD.getClef(), histoSerrureCPLNB303NORD);

        histoSerrureCPLNB401EST = new HistoriqueUtilisation(new UUID(9, 9), clef1, false);
        historiques.put(histoSerrureCPLNB401EST.getClef(), histoSerrureCPLNB401EST);
    }

    private void addGroupetoGroupe() {
        etudiants.addGroupeEnfant(etudiantsCpln);
        etudiants.addGroupeEnfant(etudiantsCifom);

        etudiantsCpln.addGroupeParent(etudiants);
        etudiantsCifom.addGroupeParent(etudiants);

        enseignants.addGroupeEnfant(enseignantsProg);
        enseignantsProg.addGroupeParent(enseignants);

        direction.addGroupeEnfant(directionCpln);
        direction.addGroupeEnfant(directionCifom);

        directionCpln.addGroupeParent(direction);
        directionCifom.addGroupeParent(direction);

        conciergerie.addGroupeEnfant(conciergerieCpln);
        conciergerie.addGroupeEnfant(conciergerieCifom);

        conciergerieCpln.addGroupeParent(conciergerie);
        conciergerieCifom.addGroupeParent(conciergerie);

        persoDarwinAmeli.addGroupeParent(etudiantsCpln);
        persoDiogoPaivaCosta.addGroupeParent(etudiantsCifom);
        persoCyrilTobler.addGroupeParent(etudiantsCifom);
        persoCedricCaillet.addGroupeParent(enseignantsProg);
        persoDominiqueHuguenin.addGroupeParent(enseignants);
    }

    private void addAccestoGroupe() {
        etudiants.addAcces(accesCplnEtudiants);
        etudiants.addAcces(accesCifomEtudiants);
        etudiants.addAcces(accesCplnEtudiantsCpln);

        etudiantsCifom.addAcces(accesCifomEtudiantsCifom);

        enseignants.addAcces(accesCifomEnseignants);
        enseignants.addAcces(accesCplnEnseignants);

        enseignantsProg.addAcces(accesCifomEnseignantsprog);
        enseignantsProg.addAcces(accesCplnEnseignantsprog);

        direction.addAcces(accesCplnDirection);
        direction.addAcces(accesCifomDirection);

        directionCpln.addAcces(accesCplnDirectionCpln);
        directionCifom.addAcces(accesCifomDirectionCifom);

        conciergerie.addAcces(accesCplnConciergerie);
        conciergerie.addAcces(accesCifomConciergerie);

        conciergerieCpln.addAcces(accesCplnConciergerieCpln);
        conciergerieCifom.addAcces(accesCifomConciergerieCifom);

        persoDarwinAmeli.addAcces(accesCplnEtudiants);
        persoDiogoPaivaCosta.addAcces(accesCifomEtudiants);
        persoCyrilTobler.addAcces(accesCifomEtudiants);
        persoCedricCaillet.addAcces(accesCifomEnseignantsprog);
        persoDominiqueHuguenin.addAcces(accesCifomEnseignants);
    }

    private void addRegistretoClef() {
        clef1.addRegistre(registre1);
        clef2.addRegistre(registre2);
        clef3.addRegistre(registre3);
        clef4.addRegistre(registre4);
        clef5.addRegistre(registre5);
        clef6.addRegistre(registre6);
    }

    private void addHistoriquetoSerrure() {
        serrureCIFOM315NORD.addHistoriqueUtilisation(histoSerrureCIFOM315NORD);
        serrureCIFOM315SUD.addHistoriqueUtilisation(histoSerrureCIFOM315SUD);

        serrureCPLNB101EST.addHistoriqueUtilisation(histoSerrureCPLNB101EST);
        serrureCPLNB102EST.addHistoriqueUtilisation(histoSerrureCPLNB102EST);
        serrureCPLNB205NORD.addHistoriqueUtilisation(histoSerrureCPLNB205NORD);
        serrureCPLNB206SUD.addHistoriqueUtilisation(histoSerrureCPLNB206SUD);
        serrureCPLNB302EST.addHistoriqueUtilisation(histoSerrureCPLNB302EST);
        serrureCPLNB303NORD.addHistoriqueUtilisation(histoSerrureCPLNB303NORD);
        serrureCPLNB401EST.addHistoriqueUtilisation(histoSerrureCPLNB401EST);
    }

    private void addSerruretoLieu() {
        cifomEtage3Salle315.addSerrure(serrureCIFOM315NORD);
        cifomEtage3Salle315.addSerrure(serrureCIFOM315SUD);
        cplnEtage1SalleB101.addSerrure(serrureCPLNB101EST);
        cplnEtage1SalleB102.addSerrure(serrureCPLNB102EST);
        cplnEtage2SalleB205.addSerrure(serrureCPLNB205NORD);
        cplnEtage2SalleB206.addSerrure(serrureCPLNB206SUD);
        cplnEtage3SalleB302.addSerrure(serrureCPLNB302EST);
        cplnEtage3SalleB303.addSerrure(serrureCPLNB303NORD);
        cplnEtage4SalleB401.addSerrure(serrureCPLNB401EST);
    }

    public HashMap<UUID, Acces> getAcces() {
        return acces;
    }

    public HashMap<String, Clef> getClefs() {
        return clefs;
    }

    public HashMap<UUID, Groupe> getGroupes() {
        return groupes;
    }

    public HashMap<UUID, Lieu> getLieux() {
        return lieux;
    }

    public HashMap<UUID, Personne> getPersonnes() {
        return personnes;
    }

    public HashMap<UUID, Registre> getRegistres() {
        return registres;
    }

    public HashMap<UUID, Serrure> getSerrures() {
        return serrures;
    }

    public HashMap<Clef, HistoriqueUtilisation> getHistoriques() {
        return historiques;
    }
//CHECKSTYLE.ON: MagicNumber
}
