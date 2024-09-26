/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click https://mylos.cifom.ch/gitlab/projet_prog-bdd/prog_carnet_adresse/-/tree/dev to go to the project
 *  @author ToblerC <cyril.tobler@icloud.com>
 */
package ch.esne.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Cette classe définit les attributs d'un accès ainsi que le lieu qui est lié
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Acces {

    /* Déclaration des variables privées */
    private UUID uuid;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Lieu lieu;

    /**
     * Constructeur spécifique d'un Acces avec l'uuid connnu, la date de début et de fin ainsi que le lieu lié
     * @param uuid UUID connu
     * @param dateDebut LocalDateTime de début du droit d'Acces
     * @param dateFin LocalDateTime de la fin du droit d'Acces
     * @param lieu Lieu où l'Acces a été attribué
     */
    public Acces(final UUID uuid, final LocalDateTime dateDebut,
            final LocalDateTime dateFin, final Lieu lieu) {
        this.uuid = uuid;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
    }

    /**
     * Constructeur spécifique d'un Acces avec l'uuid connnu, la date de début et de fin ainsi que le lieu lié
     * @param dateDebut LocalDateTime de début du droit d'Acces
     * @param dateFin LocalDateTime de la fin du droit d'Acces
     * @param lieu Lieu où l'Acces a été attribué
     */
    public Acces(final LocalDateTime dateDebut, final LocalDateTime dateFin, final Lieu lieu) {
        this.uuid = UUID.randomUUID();
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = this.lieu;
    }

    /**
     * Getter de l'uuid de l'Acces
     * @return UUID de l'Acces
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Setter de l'uuid
     * @param uuid UUID de l'Acces
     */
    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Getter de la date de début d'acces
     * @return LocalDateTime du début de l'Acces
     */
    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    /**
     * Setter de la date de début d'acces
     * @param dateDebut LocalDateTime du début de l'Acces
     */
    public void setDateDebut(final LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter de la date de fin d'acces
     * @return LocalDateTime de fin de l'Acces
     */
    public LocalDateTime getDateFin() {
        return dateFin;
    }

    /**
     * Setter de la date de fin d'acces
     * @param dateFin LocalDateTime de fin de l'Acces
     */
    public void setDateFin(final LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Getter du Lieu d'acces
     * @return Lieu de l'Acces
     */
    public Lieu getLieu() {
        return lieu;
    }

    /**
     * Setter du Lieu d'acces
     * @param lieu Lieu de fin de l'Acces
     */
    public void setLieu(final Lieu lieu) {
        this.lieu = lieu;
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        final int hash67 = 67;
        final int hash3 = 3;
        int hash = hash3;
        hash = hash67 * hash + Objects.hashCode(this.uuid);
        hash = hash67 * hash + Objects.hashCode(this.dateDebut);
        hash = hash67 * hash + Objects.hashCode(this.dateFin);
        hash = hash67 * hash + Objects.hashCode(this.lieu);
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
        final Acces other = (Acces) obj;
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return Objects.equals(this.lieu, other.lieu);
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Acces{" + "uuid=" + uuid + ", dateDebut="
                + dateDebut + ", dateFin=" + dateFin + ", lieux=" + lieu + '}';
    }
}
