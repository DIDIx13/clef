/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.esne.domain;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * La classe a pour but d'établir des serrures et un historique d'utilisation
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Registre {

    /* Déclaration des variables privées */
    private UUID uuid;
    private Timestamp dateDebut;
    private Timestamp dateFin;
    private Personne personne;

    /**
     * Constructeur spécifique avec l'uuid connu, la date de début, la date de fin, et la personne assignée
     * @param uuid uuid renseigné
     * @param dateDebut date de création du registre
     * @param dateFin date de suppression du registre
     * @param personne personne à qui la clef à été assignée
     */
    public Registre(final UUID uuid, final Timestamp dateDebut,
            final Timestamp dateFin, final Personne personne) {
        this.uuid = uuid;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.personne = personne;
    }

    /**
     * Constructeur spécifique avec l'uuid connu, la date de début et la personne assignée (pas d'éxpiration)
     * @param uuid uuid renseigné
     * @param dateDebut date de création du registre
     * @param personne personne à qui la clef à été assignée
     */
    public Registre(final UUID uuid, final Timestamp dateDebut,
            final Personne personne) {
        this.uuid = uuid;
        this.dateDebut = dateDebut;
        this.personne = personne;
    }

    /**
     * Renvoie l'uuid du Registre
     * @return l'uuid du Registre
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Renvoie la date de création du Registre
     * @return date de création du Registre
     */
    public Timestamp getDateDebut() {
        return dateDebut;
    }

    /**
     * Setter de la date de création du Registre
     * @param dateDebut date de création du Registre
     */
    public void setDateDebut(final Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Renvoie la date d'éxpiration du Registre
     * @return date d'éxpiration du Registre
     */
    public Timestamp getDateFin() {
        return dateFin;
    }

    /**
     * Setter de la date d'éxpiration du Registre
     * @param dateFin date d'éxpiration du Registre
     */
    public void setDateFin(final Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Renvoie la personne assignée au Registre
     * @return personne assignée au Registre
     */
    public Personne getPersonne() {
        return personne;
    }

    /**
     * Setter de la personne assignée au Registre
     * @param personne personne assignée au Registre
     */
    public void setPersonne(final Personne personne) {
        this.personne = personne;
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Registre{" + "Identifiant=" + uuid + ", date_debut="
                + dateDebut + ", date_fin="
                + dateFin + ", personne=" + personne + '}';
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
        hash = magicNumber43 * hash + Objects.hashCode(this.dateDebut);
        hash = magicNumber43 * hash + Objects.hashCode(this.dateFin);
        hash = magicNumber43 * hash + Objects.hashCode(this.personne);
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
        final Registre other = (Registre) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return Objects.equals(this.personne, other.personne);
    }

}
