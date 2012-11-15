
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueSaisieOuvrage extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn ;
	private JTextField textFieldTitre;
	private JTextField textFieldDate;
	private JTextField textFieldEditeur;
	private JTextField textFieldAuteur;
	private JButton buttonEnreg;
	private JButton buttonAnnuler;
	
	/**
	 * Create the frame.
	 */
	public VueSaisieOuvrage(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'un nouvel ouvrage");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueSaisieOuvrage.this);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(99, 32, 61, 15);
		contentPane.add(lblNewLabel);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(170, 32, 141, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(99, 66, 61, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(170, 65, 225, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = textFieldIsbn.getText();
				String titre = textFieldTitre.getText();
				String auteur = textFieldAuteur.getText();
				String editeur = textFieldEditeur.getText();
				String dateEdition = textFieldDate.getText();
			
				getControleur().nouvOuvrage(isbn, titre, auteur, editeur, dateEdition);
				}
		});
		buttonEnreg.setBounds(302, 198, 107, 25);
		contentPane.add(buttonEnreg);
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueSaisieOuvrage.this);}
		});
		buttonAnnuler.setBounds(302, 235, 107, 25);
		contentPane.add(buttonAnnuler);
		
		JLabel lblNewLabel_2 = new JLabel("Date édition (MM/AAAA)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(12, 161, 154, 33);
		contentPane.add(lblNewLabel_2);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(170, 168, 114, 19);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuteur.setBounds(99, 101, 61, 15);
		contentPane.add(lblAuteur);
		
		JLabel lblEditeur = new JLabel("Editeur");
		lblEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditeur.setBounds(99, 133, 61, 15);
		contentPane.add(lblEditeur);
		
		textFieldEditeur = new JTextField();
		textFieldEditeur.setColumns(10);
		textFieldEditeur.setBounds(170, 133, 218, 19);
		contentPane.add(textFieldEditeur);
		
		textFieldAuteur = new JTextField();
		textFieldAuteur.setBounds(170, 99, 225, 19);
		contentPane.add(textFieldAuteur);
		textFieldAuteur.setColumns(10);
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			buttonEnreg.setEnabled(true);
			buttonAnnuler.setEnabled(true);
			break;
			}
		}
	}

}
