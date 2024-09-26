package ch.esne.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * La classe a pour but de créer des personnes avec leurs informations utiles
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Personne {

    /* Déclaration des variables privées */
    private UUID uuid;
    private String nom;
    private String prenom;
    private String email;
    private Groupe groupe;

    /**
     * Constructeur spécifique de personne avec le nom, prénom et l'email
     * @param nom nom de la Personne
     * @param prenom prénom de la Personne
     * @param email email de la Personne
     */
    public Personne(final String nom, final String prenom, final String email) {
        uuid = UUID.randomUUID();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    /**
     * Constructeur spécifique de personne avec l'uuid connu, le nom, prénom et l'email
     * @param uuid uuid renseigné
     * @param nom nom de la Personne
     * @param prenom prénom de la Personne
     * @param email email de la Personne
     */
    public Personne(final UUID uuid, final String nom,
                    final String prenom, final String email) {
        this.uuid = uuid;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    /**
     * Constructeur spécifique de personne avec l'uuid connu, le nom, prénom, l'email et son groupe personnel
     * @param uuid uuid renseigné
     * @param nom nom de la Personne
     * @param prenom prénom de la Personne
     * @param email email de la Personne
     * @param groupe groupe personnel de la Personne
     */
    public Personne(final UUID uuid, final String nom,
                    final String prenom, final String email, final Groupe groupe) {
        this.uuid = uuid;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.groupe = groupe;
    }

    /**
     * Getter de l'uuid de la Personne
     * @return uuid de la Personne
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Getter du nom de la Personne
     * @return nom de la Personne
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter de la Personne
     * @param nom nouveau nom de la Personne
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter du prénom de la Personne
     * @return du prénom de la Personne
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter du prénom de la Personne
     * @param prenom nouveau prénom de la Personne
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter de l'email de la Personne
     * @return email de la Personne
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter de l'email de la Personne
     * @param email nouveau email de la Personne
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Getter du groupe de la Personne
     * @return groupe de la Personne
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * Setter du groupe de la Personne
     * @param groupe nouveau groupe de la Personne
     */
    public void setGroupe(final Groupe groupe) {
        this.groupe = groupe;
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Personne{" + "Identifiant=" + uuid + ", nom=" + nom
                + ", prenom=" + prenom + ", email="
                + email + ", groupe=" + groupe + '}';
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        final int magicNumber43 = 43;
        final int magicNumber5 = 5;
        int hash = magicNumber5;
        hash = magicNumber43 * hash + Objects.hashCode(this.uuid);
        hash = magicNumber43 * hash + Objects.hashCode(this.nom);
        hash = magicNumber43 * hash + Objects.hashCode(this.prenom);
        hash = magicNumber43 * hash + Objects.hashCode(this.email);
        hash = magicNumber43 * hash + Objects.hashCode(this.groupe);
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
        final Personne other = (Personne) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return Objects.equals(this.groupe, other.groupe);
    }

}
