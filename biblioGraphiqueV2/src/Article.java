

import java.io.Serializable;
import java.util.Observable;

/**
 * Classe de gestion d'un exemplaire d'un ouvrage
 * @author Timothé BOURNAY
 * @version 0.1
 *
 */
public class Article extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _titre;
	private String _auteur;
	private Parution _parution;
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************
	/**
	 * Constructeur. Crée un exemplaire d'ouvrage .
	 * 
	 * @param numero		numéro de l'exemplaire dans l'ouvrage.
	 * @param dateReception	date de réception de cet exemplaire.
	 * @param statut  		statut de l'exemplaire (en consultation, empruntable)
	 * @param ouvrage		ouvrage dont cet exemplaire est un représentant.
	 */
	public Article(String titre, Parution parution) {
		this.setTitre(titre);
		this.setParution(parution);
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * @param id l'id à affecter
	 */
	private void setTitre(String titre) {
		_titre = titre;
	}
	
	/**
	 * @param id l'id à affecter
	 */
	private void setAuteur(String auteur) {
		_auteur = auteur;
	}

	/**
	 * @param periodique le periodique lié à la parution
	 */
	private void setParution(Parution parution) {
		_parution = parution;
	} // Fin setOuvrage

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * @return l'ouvrage lié à l'exemplaire
	 */
	public Parution getParution() {
		return _parution;
	}

	/**
	 * @return le numéro de l'exemplaire
	 */
	public String getTitre() {
		return _titre;
	}
	
	/**
	 * @return le numéro de l'exemplaire
	 */
	public String getAuteur() {
		return _auteur;
	}

} // Fin Classe Exemplaire
