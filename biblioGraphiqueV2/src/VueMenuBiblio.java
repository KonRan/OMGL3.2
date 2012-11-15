import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
/**
 * Fenêtre principale de l'application Bibliothèque avec le menu 
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueMenuBiblio  extends Vue{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************
	public VueMenuBiblio(Controleur controleur) {
		super (controleur);
		setTitle("Gestion de bibliothèque");
		setBounds(100, 100, 450, 300);
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueMenuBiblio.this);
			}
		});
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(44, 24, 188, 21);
		getContentPane().add(menuBar);
		
		JMenu mnApplication = new JMenu("Application");
		mnApplication.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnApplication);
		
		JMenuItem menuItemQuitter = new JMenuItem("Quitter");
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueMenuBiblio.this);
				}		
		});
		mnApplication.add(menuItemQuitter);
		
		JMenu mnOuvrage = new JMenu("Ouvrage");
		menuBar.add(mnOuvrage);
		
		JMenuItem MenuItemOuv = new JMenuItem("Nouvel ouvrage");
		
		MenuItemOuv.addActionListener(new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent event) {
				//  affichage de la fenetre de saisie d'un ouvrage
				getControleur(). saisirOuvrage() ;}
		});
		
		mnOuvrage.add(MenuItemOuv);

		JMenuItem MenuItemExemp = new JMenuItem("Nouvel exemplaire");
		MenuItemExemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//  affichage de la fenetre de saisie d'un exemplaire
				getControleur(). saisirExemplaire() ;}
		});
		
		mnOuvrage.add(MenuItemExemp);
		
		JMenuItem menuItemConsult = new JMenuItem("Consulter ouvrage");
		menuItemConsult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// affichage de la fenetre de consultation d'un ouvrage
				getControleur(). consulterOuvrage() ;}
		});
		
		mnOuvrage.add(menuItemConsult);
	
	}// Fin Constructeur
	
	/**
	 * méthode exécutée lorsque la croix de fermeture de la fenêtre a été cliquée
	 */
	public void windowClosing (WindowEvent e) {
		getControleur().fermerVue(VueMenuBiblio.this);
	}

}
