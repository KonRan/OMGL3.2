

import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

/**
 * Classe de gestion d'un exemplaire d'un ouvrage
 * @author Timothé BOURNAY
 * @version 0.1
 *
 */
public class Parution extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _id;
	private Periodique _periodique;
	
	// Attributs d'Association
	private HashMap<String, Article> _article;

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
	public Parution(String id, Periodique periodique) {
		this.setId(id);
		this.setPeriodique(periodique);
		this.setArticle(new HashMap<String, Article>());
		
	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs
	
	/**
	 * @param id l'id à affecter
	 */
	private void setId(String id) {
		_id = id;
	}
	
	public void setArticle(HashMap<String, Article> article) {
		_article = article;
	}

	/**
	 * @param periodique le periodique lié à la parution
	 */
	private void setPeriodique(Periodique periodique) {
		_periodique = periodique;
	} // Fin setOuvrage
	
	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Lie un parution au Periodique. Insère un parution dans l'ensemble des parutions du Periodique, avec son id.
	 * @param id id de l'parution à insérer
	 * @param parution parution à insérer
	 */
	private void setArticle(String titre, Article article) {
		_article.put(titre, article);
	} // Fin setparution

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************
	
	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs
	
	/**
	 * @return l'ouvrage lié à l'exemplaire
	 */
	public Periodique getPeriodique() {
		return _periodique;
	}

	/**
	 * @return le numéro de l'exemplaire
	 */
	public String getId() {
		return _id;
	}
	
	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Ajout d'un parution à l'Periodique. Le numéro de cet parution est généré automatiquement pour l'Periodique.
	 * @param dateReception date à laquelle l'parution a été reçu.
	 * @return l'parution ou null si la date de reception est antérieure à la date d'édition de l'Periodique 
	 */
	public Article ajouterArticle(String titre) {
		// Generation du id chronologique de l'parution
		
		// Creation de l'parution
			Article article = new Article(titre, this);
		// liaison de l'Periodique a l'parution
			this.setArticle(titre, article);
		// notifie ses observateurs du changement
			this.notifyObservers();
			return article;			
		}// Fin ajouterparution

} // Fin Classe Exemplaire
