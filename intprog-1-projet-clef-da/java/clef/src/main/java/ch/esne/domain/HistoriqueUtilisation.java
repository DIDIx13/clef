package ch.esne.domain;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * Cette classe définit les attributs d'un historique d'utilisation ainsi que les serrures qui y sont liées
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */

public class HistoriqueUtilisation {

    /* Déclaration des variables privées */
    private UUID uuid;
    private Clef clef;
    private Boolean status;
    private Timestamp dateUtilisation;

    /**
     * Création d'un Historique d'utilisation avec une Clef et son status
     * @param clef
     * @param status
     */
    public HistoriqueUtilisation(final Clef clef, final Boolean status) {
        this.clef = clef;
        this.status = status;
        this.dateUtilisation = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Création d'un Historique d'utilisation avec un UUID défini pour le DemoData
     * @param uuid
     * @param clef
     * @param status
     */
    public HistoriqueUtilisation(final UUID uuid, final Clef clef, final Boolean status) {
        this.uuid = uuid;
        this.clef = clef;
        this.status = status;
        this.dateUtilisation = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Getter de l'uuid de l'historique d'utilisation
     * @return L'uuid de cette historique d'utilisation
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Getter de la Clef de l'historique d'utilisation
     * @return l'objet Clef de cette historique d'utilisation
     */
    public Clef getClef() {
        return clef;
    }

    /**
     * Getter du Status de l'historique d'utilisation
     * @return le statut de cette historique d'utilisation
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Getter de la date d'utilisation de l'historique
     * @return la date d'utilisation de cette historique d'utilisation
     */
    public Timestamp getDateUtilisation() {
        return dateUtilisation;
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getClef(), getStatus(), getDateUtilisation());
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "HistoriqueUtilisation{"
                + "clef=" + this.clef.getNumeroserie()
                + ", status=" + this.status
                + ", dateUtilisation=" + this.dateUtilisation + '}';
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
        HistoriqueUtilisation that = (HistoriqueUtilisation) o;
        return Objects.equals(getUuid(), that.getUuid())
                && getClef().equals(that.getClef())
                && getStatus().equals(that.getStatus())
                && getDateUtilisation().equals(that.getDateUtilisation());
    }
}
