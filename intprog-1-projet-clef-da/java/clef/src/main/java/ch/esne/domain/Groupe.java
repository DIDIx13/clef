package ch.esne.domain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Cette classe définit les attributs d'un groupe avec ses possibles parents et enfants ainsi que sa liste d'acces
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Groupe {

    /* Déclaration des variables privées */
    private UUID uuid;
    private String nom;
    private String description;
    private ArrayList<Groupe> groupesParent;
    private ArrayList<Groupe> groupesEnfant;
    private ArrayList<Acces> acces;
    /**
     * Constructeur spécifique avec l'uuid connu, le nom du groupe ainsi que sa déscription
     * @param uuid UUID du groupe
     * @param nom Nom du groupe
     * @param description
     */
    public Groupe(final UUID uuid, final String nom, final String description) {
        this.uuid = uuid;
        this.nom = nom;
        this.description = description;
        this.acces = new ArrayList<>();
        this.groupesEnfant = new ArrayList<>();
        this.groupesParent = new ArrayList<>();
    }

    /**
     * Constructeur spécifique avec le nom du groupe, sa déscription ainsi que sa liste d'acces
     * @param nom Nom du Groupe
     * @param description Descriptif des membres du Groupe
     * @param acces Liste d'acces du Groupe
     */
    public Groupe(final String nom, final String description,
                  final ArrayList<Acces> acces) {
        this.uuid = UUID.randomUUID();
        this.nom = nom;
        this.description = description;
        this.acces = acces;
        this.groupesEnfant = new ArrayList<>();
        this.groupesParent = new ArrayList<>();
    }

    /**
     * Constructeur spécifique avec l'uuid connu, le nom du Groupe, la description du Groupe et sa liste d'acces
     * @param uuid UUID du Groupe
     * @param nom Nom du Groupe
     * @param description Descriptif des membres du Groupe
     * @param acces Liste d'acces du Groupe
     */
    public Groupe(final UUID uuid, final String nom, final String description,
                  final ArrayList<Acces> acces) {
        this.uuid = uuid;
        this.nom = nom;
        this.description = description;
        this.acces = acces;
        this.groupesEnfant = new ArrayList<>();
        this.groupesParent = new ArrayList<>();
    }

    /**
     * Constructeur spécifique avec l'uuid connu, le nom du Groupe, la description du Groupe,
     * sa liste de parent du Groupe, sa liste d'enfant du Groupe et sa liste d'acces
     * @param uuid UUID du Groupe
     * @param nom Nom du Groupe
     * @param description Descriptif des membres du Groupe
     * @param groupesParent Parent du Groupe (Etudiants -> Etudiants CIFOM)
     * @param groupesEnfant Enfant du Groupe (Etudiants CIFOM -> Etudiants)
     * @param acces Liste d'acces du Groupe
     */
    public Groupe(final UUID uuid, final String nom, final String description,
            final ArrayList<Groupe> groupesParent,
            final ArrayList<Groupe> groupesEnfant,
            final ArrayList<Acces> acces) {
        this.uuid = uuid;
        this.nom = nom;
        this.description = description;
        this.groupesParent = groupesParent;
        this.groupesEnfant = groupesEnfant;
        this.acces = acces;
    }

    /**
     * Getter de l'uuid du Groupe
     * @return l'uuid du Groupe
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Getter du nom du Groupe
     * @return le nom du Groupe
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de modifier le nom du Groupe
     * @param nom le nouveau nom du Groupe
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter de la description du Groupe
     * @return la description du Groupe
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de modifier la description du Groupe
     * @param description la nouvelle description du Groupe
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Renvoie la liste des parents du Groupe
     * @return les parents du groupe
     */
    public ArrayList<Groupe> getAllGroupesParent() {
        return this.groupesParent;
    }

    /**
     * Renvoie le parent à l'index donné
     * @param index Position du groupe parent souhaitée
     * @return le groupe parent à l'index
     */
    public Groupe getIndexGroupeParent(final Integer index) {
        return this.groupesParent.get(index);
    }

    /**
     * Renvoie le dernier groupe parent de la liste
     * @return dernier groupe parent
     */
    public Groupe getLastGroupeParent() {
        return this.groupesParent.get(this.groupesParent.size() - 1);
    }

    /**
     * Ajoute un nouveau groupe parent à la liste
     * @param newGroupe Groupe à ajouter à la liste de parent
     */
    public void addGroupeParent(final Groupe newGroupe) {
        this.groupesParent.add(newGroupe);
    }

    /**
     * Renvoie la liste des enfants du Groupe
     * @return les enfants du groupe
     */
    public ArrayList<Groupe> getAllGroupesEnfant() {
        return this.groupesEnfant;
    }

    /**
     * Renvoie l'enfant à l'index donné
     * @param index Position du groupe enfant souhaitée
     * @return le groupe enfant à l'index
     */
    public Groupe getIndexGroupeEnfant(final Integer index) {
        return this.groupesEnfant.get(index);
    }

    /**
     * Renvoie le dernier groupe enfant de la liste
     * @return dernier groupe enfant
     */
    public Groupe getLastGroupeEnfant() {
        return this.groupesEnfant.get(this.groupesEnfant.size() - 1);
    }

    /**
     * Ajoute un nouveau groupe enfant à la liste
     * @param newGroupe Groupe à ajouter à la liste d'enfant
     */
    public void addGroupeEnfant(final Groupe newGroupe) {
        this.groupesEnfant.add(newGroupe);
    }

    /**
     * Renvoie la liste des enfants du Groupe
     * @return les acces du Groupe
     */
    public ArrayList<Acces> getAllAcces() {
        return this.acces;
    }

    /**
     * Renvoie l'acces à l'index donné
     * @param index Position de l'acces souhaitée
     * @return l'acces à l'index
     */
    public Acces getIndexAcces(final Integer index) {
        return this.acces.get(index);
    }

    /**
     * Renvoie le dernier acces de la liste
     * @return dernier acces
     */
    public Acces getLastAcces() {
        return this.acces.get(this.acces.size() - 1);
    }

    /**
     * Ajoute un nouveau acces à la liste
     * @param newAcces Acces à ajouter à la liste d'acces
     */
    public void addAcces(final Acces newAcces) {
        this.acces.add(newAcces);
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        final int hash29 = 29;
        final int hash5 = 5;
        int hash = hash5;
        hash = hash29 * hash + Objects.hashCode(this.uuid);
        hash = hash29 * hash + Objects.hashCode(this.nom);
        hash = hash29 * hash + Objects.hashCode(this.description);
        return hash;
    }

    /**
     * Comparaison d'objets
     * @param obj Objet à comparer
     * @return Booléen de la comparaison
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Groupe other = (Groupe) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        if (!Objects.equals(this.groupesParent, other.groupesParent)) {
            return false;
        }
        if (!Objects.equals(this.groupesEnfant, other.groupesEnfant)) {
            return false;
        }
        return Objects.equals(this.acces, other.acces);
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Groupe{" + "nom=" + nom + ", description=" + description + '}';
    }
}
