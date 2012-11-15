

import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Observable;

/**
 * Classe de gestion d'ouvrage.
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 */
public class Ouvrage extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _isbn;
	private String _titre;
	private String _auteur;
	private String _editeur;
	private GregorianCalendar _dateEdition;
	private int _derNumExemplaire;

	// Attributs d'Association
	private HashMap<Integer, Exemplaire> _exemplaires;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 * @param isbn			numero ISBN de l'ouvrage
	 * @param titre			titre de l'ouvrage
	 * @param auteur		auteur de l'ouvrage
	 * @param editeur		editeur de l'ouvrage
	 * @param dateEdition	date d'edition
	 */
	public Ouvrage(String isbn, String titre, String auteur, String editeur, GregorianCalendar dateEdition) {
		
		this.setIsbn(isbn);
		this.setTitre(titre);
		this.setAuteur(auteur);
		this.setEditeur(editeur);
		this.setDateEdition(dateEdition);
		this.setExemplaires(new HashMap<Integer, Exemplaire>());
		this.setDerNumExemplaire(0);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * setter de l'attribut auteur
	 * @param auteur valeur à affecter à l'auteur de l'ouvrage
	 */
	public void setAuteur(String auteur){
		_auteur = auteur;
	}
	
	/**
	 * setter de l'attribut dateEdition
	 * @param date valeur à affecter à la date d'édition de l'ouvrage
	 */
	public void setDateEdition(GregorianCalendar date){
		_dateEdition = date;
	}
	
	/**
	 * setter de l'attribut editeur
	 * @param editeur valeur à affecter à l'éditeur de l'ouvrage
	 */
	public void setEditeur(String editeur){
		_editeur = editeur;
	}

	/**
	 * setter de l'attrribut exemplaires (ensemble des exemplaires de l'ouvrage)
	 * @param exemplaires hashmap d'exemplaires
	 */
	public void setExemplaires(HashMap<Integer, Exemplaire> exemplaires) {
		_exemplaires = exemplaires;
	}
	
	/**
	 * setter de l'attribut num d'ISBN.
	 * @param isbn valeur à affecter à l'ISBN de l'ouvrage
	 */
	public void setIsbn(String isbn) {
		this._isbn = isbn;
	}

	/**
	 * setter de l'attribut titre.
	 * @param titre valeur à affecter au titre de l'ouvrage
	 */
	public void setTitre(String titre){
		_titre = titre;
	}
	
	/**
	 * setter du num du dernier exemplaire.
	 * @param derNumExemplaire nombre à affecter au dernier numéro d'exemplaire
	 */
	private void setDerNumExemplaire(int derNumExemplaire) {
		_derNumExemplaire  = derNumExemplaire ;
	}
	
	/**
	 * getter du dernier numéro d'exemplaire
	 * @return un entier représentant le dernier numéro d'exemplaire de l'ouvrage
	 */
	private int getDerNumExemplaire() {
		return _derNumExemplaire;
	}
	
	public void notifierObservateurs() {
		this.setChanged();
		this.notifyObservers();	
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Lie un exemplaire à l'ouvrage. Insère un exemplaire dans l'ensemble des exemplaires de l'ouvrage, avec son numero.
	 * @param numero numero de l'exemplaire à insérer
	 * @param exemplaire exemplaire à insérer
	 */
	private void setExemplaire(int numero, Exemplaire exemplaire) {
		_exemplaires.put(numero, exemplaire);	
	} // Fin setExemplaire

	
	/**
	 * Génère un numéro d'exemplaire.
	 * @return un entier représentant le prochain numéro disponible pour un exemplaire de l'ouvrage 
	 */
	private int genererNumeroExemplaire() {
		this.setDerNumExemplaire(this.getDerNumExemplaire() + 1);
		return this.getDerNumExemplaire();
	} // Fin genererNumeroExemplaire


	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * Getter de l'ISBN.
	 * @return ISBN de l'ouvrage
	 */
	public String getIsbn() {
		return _isbn;
	}

	/**
	 * Getter du titre.
	 * @return titre de l'ouvrage
	 */
	public String getTitre() {
		return _titre;
	}

	/**
	 * getter de l'auteur.
	 * @return auteur de l'ouvrage
	 */
	public String getAuteur() {
		return _auteur;
	}

	/**
	 * getter de l'editeur.
	 * @return editeur de l'ouvrage
	 */
	public String getEditeur() {
		return _editeur;
	}

	/**
	 * getter de dateEdition.
	 * @return date d'édition de l'ouvrage
	 */
	public GregorianCalendar getDateEdition() {
		return _dateEdition;
	}

	/**
	 * getter d'un exemplaire de l'ouvrage.
	 * @param numero numero de l'exemplaire retourné
	 * @return l'exemplaire dont le numéro a été passé en paramètre, s'il existe
	 */
	public Exemplaire getExemplaire(int numero) {
		return (Exemplaire) _exemplaires.get(numero);
	} // Fin unExemplaire

	/**
	 * getter de l'ensemble des exemplaires.
	 * @return une collection d'exemplaires
	 */
	public Collection<Exemplaire> getExemplaires() {
		return _exemplaires.values();
	}

	/**
	 * getter du nombre d'exemplaires de l'ouvrage.
	 * @return un entier représentant le nombre d'exemplaires
	 */
	public int getNbExemplaires() {
		return _exemplaires.size();
	}

	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Ajout d'un exemplaire à l'ouvrage. Le numéro de cet exemplaire est généré automatiquement pour l'ouvrage.
	 * @param dateReception date à laquelle l'exemplaire a été reçu.
	 * @return l'exemplaire ou null si la date de reception est antérieure à la date d'édition de l'ouvrage 
	 */
	public Exemplaire ajouterExemplaire(GregorianCalendar dateReception, int stat) {
		// Generation du numero chronologique de l'exemplaire
		int numero = this.genererNumeroExemplaire();
		if (this.verifDate(dateReception)){
		// Creation de l'exemplaire
			Exemplaire exemplaire = new Exemplaire(numero, dateReception, stat, this);
		// liaison de l'ouvrage a l'exemplaire
			this.setExemplaire(numero, exemplaire);
		// notifie ses observateurs du changement
			this.notifierObservateurs();
			return exemplaire;
		}
		else {
			return null;
		}
	} // Fin ajouterExemplaire

	/**
	 * @param dateRecep
	 * @return vrai si date réception de l'exemplaire est postérieure à la date édition de l'ouvrage
	 */
	private boolean verifDate(GregorianCalendar dateRecep) {
		return (dateRecep.after(_dateEdition));
	}
	
	public int getNbExemplairesEnConsultation(){
		int nb = 0;
		for (Exemplaire ex : this.getExemplaires() ){
			if (ex.estEnConsultation()) {
				nb = nb + 1;
			}
		}
		return nb;
	}
	
	public int getNbExemplairesEmpruntable(){
		int nb = 0;
		for (Exemplaire ex : this.getExemplaires() ){
			if (ex.estEmpruntable()) {
				nb = nb + 1;
			}
		}
		return nb;
	}
	
} // Fin Classe Ouvrage
