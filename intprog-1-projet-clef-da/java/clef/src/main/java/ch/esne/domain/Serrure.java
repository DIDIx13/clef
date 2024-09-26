package ch.esne.domain;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * La classe a pour but d'établir des serrures et un historique d'utilisation
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Serrure {

    /* Déclaration des variables privées */
    private UUID uuid;
    private Cardinalite cardinalite;
    private ArrayList<HistoriqueUtilisation> historiques;
    private int tailleListeHistorique = 0;

    /**
     * Constructeur d'une nouvelle Serrure
     * @param cardinalite Orientation de la serrure
     */
    public Serrure(final Cardinalite cardinalite) {
        uuid = UUID.randomUUID(); // Construit un nouvel uuid
        this.cardinalite = cardinalite;
        this.historiques = new ArrayList<>(tailleListeHistorique);
    }

    /**
     * Constructeur spécifique d'une Serrure en renseignant le lieu et la cardinalité de la serrure
     * @param cardinalite   Orientation de la serrure
     * @param historiques    Historique d'ouverture de la serrure
     */
    public Serrure(final Cardinalite cardinalite, final ArrayList<HistoriqueUtilisation> historiques) {
        uuid = UUID.randomUUID(); // Construit un nouvel uuid
        this.cardinalite = cardinalite;
        this.historiques = historiques;
    }

    /**
     * Constructeur spécifique d'une Serrure en renseignant un uuid connu
     * @param uuid identifiant unique de la serrure
     * @param cardinalite Orientation de la serrure
     */
    public Serrure(final UUID uuid, final Cardinalite cardinalite) {
        this.uuid = uuid;
        this.cardinalite = cardinalite;
        this.historiques = new ArrayList<>(tailleListeHistorique);
    }

    /**
     * Constructeur spécifique d'une Serrure en renseignant un uuid connu et l'historique
     * @param uuid          identifiant unique de la serrure
     * @param cardinalite   Orientation de la serrure
     * @param historiques    Historique d'ouverture de la serrure
     */
    public Serrure(final UUID uuid, final Cardinalite cardinalite,
                   final ArrayList<HistoriqueUtilisation> historiques) {
        this.uuid = uuid;
        this.cardinalite = cardinalite;
        this.historiques = historiques;
    }

    /**
     * Renvoie l'uuid de la serrure
     * @return uuid de la serrure
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Renvoie la cardinalité de la serrure
     * @return orientation de la serrure
     */
    public Cardinalite getCardinalite() {
        return cardinalite;
    }

    /**
     * Défini l'orientation de la serrure
     * @param cardinalite Cardinalite de la serrure
     */
    public void setCardinalite(final Cardinalite cardinalite) {
        this.cardinalite = cardinalite;
    }

    /**
     * Permet d'ajouter un historique d'utilisation dans la liste de d'historique existante
     * @param historiqueUtilisation L'historique à ajouter
     * @exception RuntimeException Dans le cas où l'historique existe déjà
     */
    public void addHistoriqueUtilisation(final HistoriqueUtilisation historiqueUtilisation) {
        if (!isEmpty()) {
            for (int i = 0; i < historiques.size(); i++) {
                if (historiques.get(i).getDateUtilisation() == historiqueUtilisation.getDateUtilisation()
                        && historiques.get(i).getClef() == historiqueUtilisation.getClef()) {
                    throw new RuntimeException("Vous ne pouvez pas ajouter deux fois le même historique");
                }
            }
        }
        historiques.add(historiqueUtilisation);
    }

    /**
     * Recupère l'historique par rapport à un index choisi
     * @param index Position de l'historique dans la liste
     * @return l'historique choisi par rapport à l'index
     * @exception RuntimeException Dans le cas où la liste d'historiques est vide
     */
    public HistoriqueUtilisation getHistoriqueUtilisation(final int index) {
        if (isEmpty()) {
            throw new RuntimeException("Il n'y a pas d'historique d'utilisation dans cette liste !");
        }
        return historiques.get(index);
    }

    /**
     * Retourne le premier historique de la liste
     * @return Le premier historique de la liste d'historique de la serrure
     * @exception RuntimeException Dans le cas où il n'y a pas de serrure dans la liste
     */
    public HistoriqueUtilisation getFirstHistoriqueUtilisation() {
        if (isEmpty()) {
            throw new RuntimeException("Il n'y a pas de serrure dans cette liste !");
        }
        return historiques.get(0);
    }

    /**
     * Retourne uniquement le dernier historique de la liste
     * @return Le dernier historique de la liste d'historique d'utilisation de la serrure
     * @exception RuntimeException Dans le cas où il n'y a pas d'historique d'utilisation dans la liste
     */
    public HistoriqueUtilisation getLastHistoriqueUtilisation() {
        if (isEmpty()) {
            throw new RuntimeException("Il n'y a pas d'historique d'utilisation dans cette liste !");
        }
        tailleListeHistorique = historiques.size();

        if (tailleListeHistorique == 1) {
            return historiques.get(0);
        } else {
            return historiques.get(tailleListeHistorique - 1);
        }
    }

    /**
     * Retourne tous les historiques d'utilisation de la serrure
     * @return la liste des historiques d'utilisation de la serrure
     */
    public ArrayList<HistoriqueUtilisation> getAllHistoriqueUtilisation() {
        return historiques;
    }

    /**
     * Vérifie que la liste des historiques d'utilisation est vide ou non
     * @return si la liste est vide ou non
     */
    public boolean isEmpty() {
        boolean isEmpty = false;
        if (historiques.size() == 0) {
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
        if (!(o instanceof Serrure)) {
            return false;
        }
        Serrure serrure = (Serrure) o;
        return getUuid().equals(serrure.getUuid()) && getCardinalite() == serrure.getCardinalite()
                && historiques.equals(serrure.historiques);
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getCardinalite(), historiques);
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Serrure{"
                + "uuid=" + uuid
                + ", cardinalite=" + cardinalite
                + ", nombre d'historiques=" + historiques.size()
                + '}';
    }
}
