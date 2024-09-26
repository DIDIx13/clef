/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ch.esne.domain;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Cette classe définit les attributs d'une clef ainsi que les registres qui y sont liés
 * @author Costa Diogo, Ameli Darwin, Tobler Cyril
 */
public class Clef {

    /* Déclaration des variables privées */
    private String numeroserie;
    private ClefStatus status;
    private ArrayList<Registre> registres; // Liste des registres
    private int number;

    /**
     * Constructeur spécifique avec le numéro de série et la statut (le reste est définit par défaut)
     * @param numeroserie Numéro de série de la Clef
     * @param status Status de la Clef
     */
    public Clef(final String numeroserie, final ClefStatus status) {
        this.numeroserie = numeroserie;
        this.status = status;
        this.registres = new ArrayList<>(0);
        this.number = 0;
    }

    /**
     * Constructeur spécifique avec le numéro de série uniquement (le reste est définit par défaut)
     * @param numeroserie Numéro de série de la Clef
     */
    public Clef(final String numeroserie) {
        this.numeroserie = numeroserie;
        this.status = ClefStatus.ACTIVE;
        this.registres = new ArrayList<>(0);
        this.number = 0;
    }

    /**
     * Constructeur spécifique avec le numéro de série uniquement (le reste est définit par défaut)
     * @param numeroserie Numéro de série de la Clef
     * @param status Assigne un status parmi ACTIVE/INACTIVE/PERDUE/DISFONCTIONNELLE
     * @exception RuntimeException Dans le cas où le status ne fait pas parti des status acceptés
     */
    public Clef(final String numeroserie, final String status) {
        this.numeroserie = numeroserie;
        switch (ClefStatus.valueOf(status)) {
            case ACTIVE:
                this.status = ClefStatus.ACTIVE;
                break;
            case INACTIVE:
                this.status = ClefStatus.INACTIVE;
                break;
            case PERDUE:
                this.status = ClefStatus.PERDUE;
                break;
            case DISFONCTIONNELLE:
                this.status = ClefStatus.DISFONCTIONNELLE;
                break;
            default: //On ne passe jamais ici étant donné que ClefStatus.valueOf(status) détecte directement que ce
                // n'est pas un des ENUM existants
                throw new RuntimeException("Il faut donner un status entre : ACTIVE, INACTIVE, PERDUE, "
                        + "DISFONCTIONNELLE");
        }
        this.registres = new ArrayList<>(0);
        this.number = 0;
    }

    /**
     * Getter du numéro de série de la Clef
     * @return le numéro de série de la Clef
     */
    public String getNumeroserie() {
        return numeroserie;
    }

    /**
     * Permet de modifier le numéro de série de la Clef
     * @param numeroserie est le nouveau numéro de série de la Clef
     */
    public void setNumeroserie(final String numeroserie) {
        this.numeroserie = numeroserie;
    }

    /**
     * Getter du status de la Clef
     * @return le statut de la Clef
     */
    public ClefStatus getStatus() {
        return status;
    }

    /**
     * Permet de modifier le statut de la Clef
     * @param status est le nouveau statut de la Clef
     */
    public void setStatus(final ClefStatus status) {
        this.status = status;
    }

    /**
     * Permet d'ajouter un registre dans la liste de registre existante
     * @param registre est le registre à ajouter
     * @exception RuntimeException Dans le cas où l'index est déjà présent
     */
    public void addRegistre(final Registre registre) {
        if (isEmpty()) {
            registres.add(registre);
        } else {
            for (int i = 0; i < registres.size(); i++) {
                if (registres.get(i).getUuid() == registre.getUuid()) {
                    throw new RuntimeException("L'identifiant du registre est déjà présent !");
                }
            }
            registres.add(registre);
        }
        this.number++;
    }

    /**
     * Getter du registre de la Clef
     * @param index est l'index de la position du registre dans la liste
     * @return le registre par rapport à l'index
     * @exception RuntimeException Dans le cas où la liste de registres est vide
     */
    public Registre getRegistre(final int index) {
        if (isEmpty()) {
            throw new RuntimeException("La queue est vide");
        }
        return registres.get(index);
    }

    /**
     * Recupère le premier registre de la liste
     * @return le premier registre de la liste de la liste de registre de la Clef
     * @exception RuntimeException Dans le cas où la liste de registres est vide
     */
    public Registre getFirstRegistre() {
        if (isEmpty()) {
            throw new RuntimeException("La queue est vide");
        }
        return registres.get(0);
    }

    /**
     * Recupère le premier registre de la liste
     * @return le dernier registre de la liste de registre de la Clef
     * @exception RuntimeException Dans le cas où la liste de registres est vide
     */
    public Registre getLastRegistre() {
        if (isEmpty()) {
            throw new RuntimeException("La queue est vide");
        }
        return registres.get(number - 1);
    }

    /**
     * Renvoie tous les registres de la liste
     * @return la liste des registres de la clef
     */
    public ArrayList<Registre> getAllRegistres() {
        return registres;
    }

    /**
     * Vérifie si la liste est vide
     * @return si la liste est vide ou non
     */
    public boolean isEmpty() {
        boolean reponse = false;
        if (registres.size() == 0) {
            reponse = true;
        }
        return reponse;
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
        if (!(o instanceof Clef)) {
            return false;
        }
        Clef clef = (Clef) o;
        return number == clef.number && getNumeroserie().equals(clef.getNumeroserie())
                && getStatus() == clef.getStatus() && Objects.equals(registres, clef.registres);
        //détecte que le coverage d'une seule ligne, mais nous sommes obligés de faire un saut de ligne
        // à cause du checkstyle
    }

    /**
     * Hachage des données stockées
     * @return hash des données
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNumeroserie(), getStatus(), registres, number);
    }

    /**
     * Réprésentation de l'objet en String
     * @return String des données l'objet
     */
    @Override
    public String toString() {
        return "Clef{"
                + "numeroserie=" + this.numeroserie
                + ", registres=" + this.number
                + ", status=" + this.status + '}';
    }
}
