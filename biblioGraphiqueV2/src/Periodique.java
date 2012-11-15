

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

/**
 * Classe de gestion de périodique.
 * @author Timothé BOURNAY
 * @version 0.1
 */
public class Periodique extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _issn;
	private String _nom;

	// Attributs d'Association
	private HashMap<String, Parution> _parution;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur. 
	 * @param issn			numéro ISSN du périodique
	 * @param nom			nom du périodique
	 * @param id			identifiant du périodique
	public Periodique(String issn, String nom, String id) {
		
		this.setissn(issn);
		this.setnom(nom);
		this.setParution(new HashMap<String, Parution>());
		this.setDerNumParution(0);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * setter de l'attribut id
	 * @param id valeur à affecter à l'id de l'Periodique
	 */

	/**
	 * setter de l'attrribut parutions (ensemble des parutions de l'Periodique)
	 * @param parutions hashmap d'parutions
	 */
	public void setParution(HashMap<String, Parution> parution) {
		_parution = parution;
	}
	
	/**
	 * setter de l'attribut num d'issn.
	 * @param issn valeur à affecter à l'issn de l'Periodique
	 */
	public void setissn(String issn) {
		this._issn = issn;
	}

	/**
	 * setter de l'attribut nom.
	 * @param nom valeur à affecter au nom de l'Periodique
	 */
	public void setNom(String nom){
		_nom = nom;
	}
	
	public void notifierObservateurs() {
		this.setChanged();
		this.notifyObservers();	
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Lie un parution au Periodique. Insère un parution dans l'ensemble des parutions du Periodique, avec son id.
	 * @param id id de l'parution à insérer
	 * @param parution parution à insérer
	 */
	private void setParution(String id, Parution parution) {
		_parution.put(id, parution);
	} // Fin setparution

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * Getter de l'issn.
	 * @return issn de l'Periodique
	 */
	public String getIssn() {
		return _issn;
	}

	/**
	 * Getter du nom.
	 * @return nom de l'Periodique
	 */
	public String getnom() {
		return _nom;
	}

	/**
	 * getter d'un parution du Periodique.
	 * @param id id de l'parution retourné
	 * @return l'parution dont le numéro a été passé en paramètre, s'il existe
	 */
	public Parution getParution(String id) {
		return (Parution) _parution.get(id);
	} // Fin unparution

	/**
	 * getter de l'ensemble des parutions.
	 * @return une collection d'parutions
	 */
	public Collection<Parution> getParutions() {
		return _parution.values();
	}

	/**
	 * getter du nombre d'parutions de l'Periodique.
	 * @return un entier représentant le nombre d'parutions
	 */
	public int getNbParutions() {
		return _parution.size();
	}

	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Ajout d'un parution à l'Periodique. Le numéro de cet parution est généré automatiquement pour l'Periodique.
	 * @param dateReception date à laquelle l'parution a été reçu.
	 * @return l'parution ou null si la date de reception est antérieure à la date d'édition de l'Periodique 
	 */
	public Parution ajouterParution(String id) {
		// Generation du id chronologique de l'parution
		
		// Creation de l'parution
			Parution parution = new Parution(id, this);
		// liaison de l'Periodique a l'parution
			this.setParution(id, parution);
		// notifie ses observateurs du changement
			this.notifierObservateurs();
			return parution;			
		}// Fin ajouterparution
		
	} // Fin Classe Periodique
	
 
