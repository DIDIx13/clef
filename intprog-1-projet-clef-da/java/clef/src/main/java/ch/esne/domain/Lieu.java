/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.esne.domain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * La classe a pour but d'établir des lieux pour Acces et Serrure
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Lieu {

    /* Déclaration des variables privées */
    private UUID uuid;
    private String nom;
    private Lieu lieuParent;
    private ArrayList<Serrure> serrures;
    private int tailleListeSerrure = 0; // Evite les magic number

    /**
     * Constructeur spécifique d'un Lieu avec le nom sans parent
     * @param nom Nom du Lieu à créer
     */
    public Lieu(final String nom) {
        uuid = UUID.randomUUID(); // Construit un nouvel uuid
        this.nom = nom;
        this.lieuParent = null;
        this.serrures = new ArrayList<>(tailleListeSerrure);
    }

    /**
     * Constructeur spécifique d'un Lieu avec le nom et son parent
     * @param nom Nom du Lieu à créer
     * @param lieuParent Lieu parent du Lieu (Etage 3 -> CIFOM)
     */
    public Lieu(final String nom, final Lieu lieuParent) {
        uuid = UUID.randomUUID(); // Construit un nouvel uuid
        this.nom = nom;
        this.lieuParent = lieuParent;
        this.serrures = new ArrayList<>(tailleListeSerrure);
    }

    /**
     * Constructeur spécifique d'un Lieu avec le nom et son parent en renseignant un UUID connu
     * @param uuid Identifiant unique du Lieu
     * @param nom Nom du Lieu
     */
    public Lieu(final UUID uuid, final String nom) {
        this.uuid = uuid;
        this.nom = nom;
        this.lieuParent = null;
        this.serrures = new ArrayList<>(tailleListeSerrure);
    }

    /**
     * Constructeur spécifique d'un Lieu avec le nom et son parent en renseignant un UUID connu
     * @param uuid Identifiant unique du Lieu
     * @param nom Nom du Lieu
     * @param lieuParent Parent du Lieu (Etage 3 -> CIFOM)
     */
    public Lieu(final UUID uuid, final String nom, final Lieu lieuParent) {
        this.uuid = uuid;
        this.nom = nom;
        this.lieuParent = lieuParent;
        this.serrures = new ArrayList<>(tailleListeSerrure);
    }

    /**
     * Constructeur spécifique d'un Lieu avec le nom et son parent et la liste des serrures
     * @param nom Nom du Lieu
     * @param lieuParent Parent du Lieu (Etage 3 -> CIFOM)
     * @param serrures Liste des serrures du Lieu
     */
    public Lieu(final String nom, final Lieu lieuParent, final ArrayList<Serrure> serrures) {
        this.nom = nom;
        this.lieuParent = lieuParent;
        this.serrures = serrures;
    }

    /**
     * Constructeur spécifique d'un lieu avec le nom et son parent en renseignant un UUID connu et la liste des serrures
     * @param uuid Identifiant unique du Lieu
     * @param nom Nom du Lieu
     * @param lieuParent Parent du Lieu (Etage 3 -> CIFOM)
     * @param serrures Liste des serrures du Lieu
     */
    public Lieu(final UUID uuid, final String nom, final Lieu lieuParent, final ArrayList<Serrure> serrures) {
        this.uuid = uuid;
        this.nom = nom;
        this.lieuParent = lieuParent;
        this.serrures = serrures;
    }

    /**
     * Renvoie l'uuid du Lieu
     * @return uuid du Lieu
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Renvoie le nom du Lieu
     * @return nom du Lieu
     */
    public String getNom() {
        return nom;
    }

    /**
     * Defini le nom du Lieu
     * @param nom Nom du Lieu
     */
    public void setNom(final String nom) {
        /* Exception pour gérer l'unit test : testParentEnfantNonEgal */
        if (nom == null || nom == this.lieuParent.getNom()) {
            throw new IllegalArgumentException("On ne peut pas mettre le même nom que notre lieu parent !");
        }
        this.nom = nom;
    }

    /**
     * Renvoie le lieu parent du Lieu
     * @return parent du Lieu
     */
    public Lieu getLieuParent() {
        return lieuParent;
    }

    /**
     * Défini le Lieu parent
     * @param lieuParent Lieu parent
     * @exception RuntimeException Dans le cas où le lieu parent
     */
    public void setLieuParent(final Lieu lieuParent) {
        if (lieuParent == this.getLieuParent()) {
            return;
        } else if (this.nom == lieuParent.getNom()) {
            throw new IllegalArgumentException("Le nom du parent ne peut pas être le même du lieu !");
        }

        this.lieuParent = lieuParent;
    }

    /**
     * Permet d'ajouter une serrure dans la liste de serrure existante
     * @param serrure La serrure à ajouter
     * @exception RuntimeException Dans le cas où l'identifiant de la serrure existe déjà
     */
    public void addSerrure(final Serrure serrure) {
        if (!isEmpty()) {
            for (int i = 0; i < serrures.size(); i++) {
                if (serrures.get(i).getUuid() == serrure.getUuid()) {
                    throw new RuntimeException("L'identifiant de la serrure est déjà présent !");
                }
            }
        }
        serrures.add(serrure);
    }

    /**
     * Recupère la serrure par rapport à un index choisi
     * @param index Position de la serrure dans la liste
     * @return la serrure choisie par rapport à l'index
     * @exception RuntimeException Dans le cas où la liste de serrures est vide
     */
    public Serrure getSerrure(final int index) {
        if (isEmpty()) {
            throw new RuntimeException("Il n'y a pas de serrure dans cette liste !");
        }
        return serrures.get(index);
    }

    /**
     * Retourne la première serrure de la liste
     * @return La première serrure de la liste de serrure du lieu
     * @exception RuntimeException Dans le cas où il n'y a pas de serrure dans la liste
     */
    public Serrure getFirstSerrure() {
        if (isEmpty()) {
            throw new RuntimeException("Il n'y a pas de serrure dans cette liste !");
        }
        return serrures.get(0);
    }

    /**
     * Retourne uniquement la dernière serrure de la liste
     * @return La dernière serrure de la liste de serrure du lieu
     * @exception RuntimeException Dans le cas où il n'y a pas de serrure dans la liste
     */
    public Serrure getLastSerrure() {
        if (isEmpty()) {
            throw new RuntimeException("Il n'y a pas de serrure dans cette liste !");
        }
        tailleListeSerrure = serrures.size();

        if (tailleListeSerrure == 1) {
            return serrures.get(0);
        } else {
            return serrures.get(tailleListeSerrure - 1);
        }
    }

    /**
     * Retourne toutes les serrures du lieu
     * @return la liste des serrures du lieu
     */
    public ArrayList<Serrure> getAllSerrures() {
        return serrures;
    }

    /**
     * Vérifie que la liste de serrures est vide ou non
     * @return si la liste est vide ou non
     */
    public boolean isEmpty() {
        boolean isEmpty = false;
        if (serrures.size() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Comparaison d'objets
     * @param o Objet à comparer
     * @return Booléen de la comparaison
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lieu lieu = (Lieu) o;
        return tailleListeSerrure == lieu.tailleListeSerrure && Objects.equals(getUuid(), lieu.getUuid())
                && getNom().equals(lieu.getNom()) && Objects.equals(getLieuParent(), lieu.getLieuParent())
                && Objects.equals(serrures, lieu.serrures);
        //Coverage ne détecte que la première ligne...
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getNom(), getLieuParent(), serrures, tailleListeSerrure);
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Lieu{"
                + "uuid=" + uuid
                + ", nom='" + nom + '\''
                + ", lieuParent=" + lieuParent
                + ", nombre de serrures=" + serrures.size()
                + '}';
    }
}
