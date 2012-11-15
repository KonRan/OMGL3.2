import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
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
public class VueMenuBiblioOld  extends Vue{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************
	public VueMenuBiblioOld(Controleur controleur) {
		super (controleur);
		this.initialize();
	} // Fin Constructeur
	
	public JFrame getFrame() {
		return frame;	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestion de bibliothèque");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		frame.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
				getControleur().fermerVue(VueMenuBiblioOld.this);
			}
		});
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(44, 24, 188, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnApplication = new JMenu("Application");
		mnApplication.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnApplication);
		
		JMenuItem menuItemQuitter = new JMenuItem("Quitter");
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueMenuBiblioOld.this);
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
		frame.setVisible(true);
	}
	
	/**
	 * méthode exécutée lorsque la croix de fermeture de la fenêtre a été cliquée
	 */
	public void windowClosing (WindowEvent e) {
		getControleur().fermerVue(VueMenuBiblioOld.this);
	}

}
