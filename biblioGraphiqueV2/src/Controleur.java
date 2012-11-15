
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Classe controleur et application (système)
 * @author IUT,   A. Culet
 * @version 1.0 
 */


public class Controleur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * La classe Controleur est unique pour tous les cas d'utilisation
	 * Elle est également la classe "application" qui gère l'ensemble des objets de l'appli
	 */	
	// ************************************************************************************************************
		// Attributs
		// ************************************************************************************************************
	
		// Attributs d'Association
		// Ensemble des ouvrages de la bibliothèque
		private HashMap<String, Ouvrage> _ouvrages; 
		
		// la liste des vues. La 1ere est toujours la vue Menu Principal. La dernière est la vue active.
		private LinkedList<Vue> _vues;
		
		
		// ************************************************************************************************************
		// Constructeur
		// ************************************************************************************************************

		public Controleur() {
			this.setOuvrages(new HashMap<String, Ouvrage>());
			_vues = new LinkedList<Vue>();
		} // Fin Controleur

		// ************************************************************************************************************
		// Méthodes privées
		// ************************************************************************************************************

		// ------------------------------------------------------------------------------------------------------------
		// Affecteurs
		
		/**
		 * Ajoute un ouvrage à l'ensemble des ouvrages de la bibliothèque.
		 * @param ouvrage 	Ouvrage à ajouter
		 * @param isbn 	code ISBN de cet ouvrage
		 */
		private void setOuvrage(Ouvrage ouvrage, String isbn) {
			this.getOuvrages().put(isbn, ouvrage);
		} // Fin setOuvrage

		/**
		 * @param ouvrages hashtable d'ouvrages à affecter
		 */
		private void setOuvrages(HashMap<String, Ouvrage> ouvrages) {
			_ouvrages = ouvrages;
		}// Fin setOuvrages
		
		/**
		 * ajoute ou enlève la vue active courante de la liste des vues
		 * @param vue  la vue à affecter
		 */
		 private void setVue(Vue vue) {
				_vues.addLast(vue);
		 }
		 private void removeVue() {
				_vues.removeLast();
		 }
		
		/*
		// ------------------------------------------------------------------------------------------------------------
		// Accesseurs
		
		/**
		 * @return ensemble des ouvrages de la bibliothèque
		 */
		private HashMap<String, Ouvrage> getOuvrages() {
			return _ouvrages;
		}// Fin getOuvrages

		/**
		 * Accès à un ouvrage par son numéro ISBN
		 * @param isbn 	le code ISBN de l'ouvrage cherché
		 * @return l'ouvrage qui a l'ISBN indiqué
		 */
		private Ouvrage getOuvrage(String isbn) {
			return this.getOuvrages().get(isbn);
		} // Fin getOuvrage

		/**
		 * @return la vue active courante 
		 */
		private Vue getVue() {
			return (Vue)_vues.getLast() ;
		}
		/**
		 * @return la vue Menu Principal 
		 */
		private VueMenuBiblio getVueMenu() {
			return (VueMenuBiblio)_vues.getFirst() ;
		}
		
		/*
		// ************************************************************************************************************
		// Méthodes publiques de création et affichage des fenêtres de l'application et fermeture
		// ************************************************************************************************************
		/**
		 * Création et affichage de la fenêtre principale de l'application. 
		 * Elle propose le menu de l'appli
		 */
		
		public void menuBiblio() {
			try {this.setVue(new VueMenuBiblio(this));
				this.getVueMenu().setVisible(true); 	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Cas d'utilisation : consultation d'un ouvrage
		 * Création et affichage de la fenêtre de consultation d'un ouvrage
		 */
		
		public void consulterOuvrage() {
			try {this.setVue (new VueConsultOuvrage(this));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 	
			// la vue courante est VueConsultOuvrage
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * Cas d'utilisation : saisie d'un exemplaire d'ouvrage
		 * Création et affichage de la fenêtre de saisie d'un exemplaire d'ouvrage
		 */
		public void saisirExemplaire() {
			try {this.setVue(new VueSaisieExemplaire(this));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 
			// la vue courante est VueSaisieExemplaire
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * cas d'utilisation : saisie d'un ouvrage
		 * Création et affichage de la fenêtre de saisie d'un ouvrage
		 */
		public void saisirOuvrage() {
			try {this.setVue(new VueSaisieOuvrage(this));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 
			// la vue courante est VueSaisieOuvrage
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		/**
		 * fermeture de la fenêtre vue
		 * lors de la fermeture de la fenêtre principale de l'application sauvegarde des objets sérialisés 
		 */
		public void fermerVue (Vue vue) {
			//la vue est détruite et n'est plus la vue active courante	
			
			if (vue instanceof VueMenuBiblio ) {
			// Quitte l'aplication. Sauvegarde les objets du modèle
				vue.dispose();
				this.removeVue();this.sauve();
				System.exit(0);
				}
			else {
			// le Menu est rendu de nouveau visible
				vue.dispose();
				this.removeVue();
				this.getVueMenu().setVisible(true); 
			}
		}
		
		/*
		// ************************************************************************************************************
		// Opérations liées à la sérialisation des objets de l'application
		// ************************************************************************************************************
		/**
		 *  restauration des objets de l'application
		 */
		public Controleur restaure() {
			try {
				FileInputStream fichier = new FileInputStream("Fsauv.ser");
				ObjectInputStream in = new ObjectInputStream(fichier);
				return((Controleur) in.readObject());
			} catch (Exception e) {
				Message dialog = new Message("Pbs de Restauration ou fichier non encore créé");
				return this;
			} 
		}
		/**
		 *  sauvegarde des objets de l'application
		 */
		private void sauve() {
			try {
				FileOutputStream f = new FileOutputStream("Fsauv.ser");
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(this);
			} catch (Exception e) {
				Message dialog = new Message("Pb de Sauvegarde dans le fichier");
			}
		}
		// ************************************************************************************************************
		// Opérations liées à l'application en réponse à une action de l'utilisateur dans une vue
		// ************************************************************************************************************

		/**
		 * Accès à un ouvrage par son numéro ISBN
		 * Invoqué dans VueConsultOuvrage et VueSaisieExemplaire
		 * @param isbn 	le code ISBN de l'ouvrage cherché
		 * @return l'ouvrage qui a l'ISBN indiqué ou null
		 * affiche un message d'erreur si l'ouvrage n'est pas trouvé
		 */
		public Ouvrage rechOuvrage(String isbn) {
			Ouvrage ouv = this.getOuvrage(isbn);
			if (ouv == null) {
				Message dialog = new Message("Ouvrage inconnu");
			}
			else {
				// la vue courante est VueSaisieExemplaire
				if (this.getVue() instanceof VueSaisieExemplaire){
				// la vue est inscrite comme observatrice de l'ouvrage	
					ouv.addObserver(this.getVue());
				// le contrôleur modifie l'état de la vue
					this.getVue().setEtat(Vue.inter1);	
				// le controleur demande à la vue d'afficher les infos de l'ouvrage
					((VueSaisieExemplaire)this.getVue()).alimente(ouv);
					}
				// la vue courante est VueConsultOuvrage
				if (this.getVue() instanceof VueConsultOuvrage){
					// le contrôleur modifie l'état de la vue
					this.getVue().setEtat(Vue.finale);
					((VueConsultOuvrage)this.getVue()).alimente(ouv);
					}
			}
			return ouv;
		} // Fin rechOuvrage
		
		/**
		 * Création d'un exemplaire d'ouvrage 
		 * Invoqué dans VueSaisieExemplaire
		 * @param ouv l'ouvrage  dateRecep la date de réception de l'exemplaire	
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		public void nouvExemplaire(Ouvrage ouv, String dateReception, String statut) {
			// vérification de la présence de la date et de son format
			if (dateReception.length() == 0 ){
					Message dialog = new Message("La date de réception est obligatoire");
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateReception);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					}
				else {
					int statutEx;
					if (statut == "empruntable") {
						statutEx = Exemplaire.EMPRUNTABLE ; }
					else {
						statutEx = Exemplaire.EN_CONSULTATION ; }
			// demande d'ajout de l'exemplaire
					Exemplaire exemplaire = ouv.ajouterExemplaire(date, statutEx);
			// l'opération s'est bien passée
					if (exemplaire != null) {
			// le contrôleur modifie l'état de la vue
						this.getVue().setEtat(Vue.finale);
			// affichage d'un message de confirmation
						Message dialog = new Message("Exemplaire enregistré");
					}
					else {
						Message dialog = new Message("Date de Reception incorrecte / à la date d'Edition.");
					}
				}
			}
		} // Fin nouvExemplaire
		
		/**
		 * Création d'un  d'ouvrage 
		 * Invoqué dans VueSaisieOuvrage
		 * @param  dateEdition la date d'édition de l'ouvrage
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		public void nouvOuvrage(String isbn, String titre, String auteur, String editeur, String dateEdition) {
			// vérification de la présence des infos obligatoires et du format de la date
			if ((isbn.length() == 0) || (titre.length() == 0) || (auteur.length() == 0) 
					|| (editeur.length() == 0 )|| (dateEdition.length() == 0 )){
					Message dialog = new Message("Tous les champs sont obligatoires");
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateEdition);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					}
				else if (this.getOuvrage(isbn )== null) {
				// Instanciation de l'ouvrage
					Ouvrage ouvrage = new Ouvrage(isbn, titre, auteur, editeur, date);
				// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
					this.setOuvrage(ouvrage, isbn);
					
					Message dialog = new Message("Ouvrage enregistré");
					this.fermerVue (this.getVue());
					} 
					else {
						Message dialog = new Message("Ouvrage déjà présent");
					}
				}
			}
		} // Fin nouvOuvrage
		

