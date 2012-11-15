
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
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
/**
 * Fenêtre de consultation d'un ouvrage
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */

public class VueConsultOuvrage extends Vue {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldIsbn;
	private JTextField textFieldTitre;
	private JTextField textFieldAuteur;
	private JTextField textFieldEditeur;
	private JTextField textFieldDateEdition;
	private JTextArea textAreaInfosExemplaires;
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonAnnuler;
	private JButton buttonFermer;
	
	
	/**
	 * Create the frame.
	 */
	public VueConsultOuvrage(Controleur controleur) {
		super(controleur);
		setTitle("Informations sur un ouvrage");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 452, 515);
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueConsultOuvrage.this);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(17, 66, 61, 15);
		contentPane.add(lblNewLabel);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(112, 64, 114, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(17, 147, 61, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setBounds(112, 145, 214, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueConsultOuvrage.this);}
		});
		buttonAnnuler.setBounds(255, 98, 107, 25);
		contentPane.add(buttonAnnuler);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().rechOuvrage(textFieldIsbn.getText());
				}
		});
		buttonRech.setBounds(255, 61, 107, 25);
		contentPane.add(buttonRech);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.setEnabled(false);
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueConsultOuvrage.this);}
		});
		buttonFermer.setBounds(183, 438, 107, 25);
		contentPane.add(buttonFermer);
		
		textFieldAuteur = new JTextField();
		textFieldAuteur.setEditable(false);
		textFieldAuteur.setColumns(10);
		textFieldAuteur.setBounds(112, 176, 214, 19);
		contentPane.add(textFieldAuteur);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAuteur.setBounds(17, 178, 61, 15);
		contentPane.add(lblAuteur);
		
		textFieldEditeur = new JTextField();
		textFieldEditeur.setEditable(false);
		textFieldEditeur.setColumns(10);
		textFieldEditeur.setBounds(112, 207, 214, 19);
		contentPane.add(textFieldEditeur);
		
		JLabel lblEditeur = new JLabel("Editeur");
		lblEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditeur.setBounds(17, 209, 61, 15);
		contentPane.add(lblEditeur);
		
		textFieldDateEdition = new JTextField();
		textFieldDateEdition.setEditable(false);
		textFieldDateEdition.setColumns(10);
		textFieldDateEdition.setBounds(112, 238, 114, 19);
		contentPane.add(textFieldDateEdition);
		
		JLabel lblDatedition = new JLabel("Date édition");
		lblDatedition.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDatedition.setBounds(3, 240, 82, 15);
		contentPane.add(lblDatedition);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(112, 284, 250, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("Exemplaires");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(197, 298, 107, 15);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 330, 399, 96);
		contentPane.add(scrollPane);
		
		textAreaInfosExemplaires = new JTextArea();
		textAreaInfosExemplaires.setEditable(false);
		scrollPane.setColumnHeaderView(textAreaInfosExemplaires);
		}
	
	/**
	 * alimentation des composants d'affichage en consultant directement l'ouvrage
	 */
	public void alimente(Ouvrage ouv) {
			textFieldTitre.setText(ouv.getTitre());
			textFieldAuteur.setText(ouv.getAuteur());
			textFieldEditeur.setText(ouv.getEditeur());
			textFieldDateEdition.setText (ESDate.ecrireDate (ouv.getDateEdition()));
			int nbConsult = ouv.getNbExemplairesEnConsultation();
			int nbEmpr = ouv.getNbExemplairesEmpruntable();
			textAreaInfosExemplaires.setText("");
			for (Exemplaire exemplaire : ouv.getExemplaires()) {
				  textAreaInfosExemplaires.append( "numéro " + String.valueOf(exemplaire.getNumero()) 
				 + " : " + exemplaire.libStatut() + "\n");
				}
			if ((nbConsult+ nbEmpr)== 0 ){
				Message dialog = new Message("Aucun exemplaire n'est encore disponible");
			}
	}
	/**
	 * définition des états de la fenêtre, cad des régions actives selon l'état
	 */
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			buttonRech.setEnabled(true);
			buttonAnnuler.setEnabled(true);
			buttonFermer.setEnabled(false);
			break;
			}
		case finale: {
			buttonRech.setEnabled(false);
			buttonAnnuler.setEnabled(false);
			buttonFermer.setEnabled(true);
			break;
			}
		}
	}
}
