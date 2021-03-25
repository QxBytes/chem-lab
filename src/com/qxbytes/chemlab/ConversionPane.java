package com.qxbytes.chemlab;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author QxBytes
 *
 */
public class ConversionPane {
	public ConversionPane(int atoms, double mass, String name) {
		JFrame frame = new JFrame(SelectionPane.getFrame().getTitle().substring(10).replaceAll("1", ""));
		//atoms, particles, moles, molecular mass, grams
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);

		JPanel root = new JPanel(new GridLayout(6,2));
		frame.setLayout(new GridLayout(2,1));
		
		JPanel database = new JPanel(new GridLayout(0,1));

		root.add(new JLabel("Atoms: "));
		JTextField atom = new JTextField("0");
		root.add(atom);
		root.add(new JLabel("Particles: "));
		JTextField part = new JTextField("0");
		root.add(part);
		root.add(new JLabel("Mole: "));
		JTextField mole = new JTextField("0");
		root.add(mole);
		root.add(new JLabel("Mole/Grams: "));
		JTextField gmol = new JTextField(mass+"");
		root.add(gmol);
		root.add(new JLabel("Grams: "));
		JTextField gram = new JTextField("0");
		root.add(gram);
		root.add(new JLabel("Atoms/Particle: "));
		JTextField aprt = new JTextField(atoms+"");
		root.add(aprt);

		atom.addActionListener(new calculateListener(atom,part,mole,gmol,gram,aprt,0));
		part.addActionListener(new calculateListener(atom,part,mole,gmol,gram,aprt,1));
		mole.addActionListener(new calculateListener(atom,part,mole,gmol,gram,aprt,2));
		gram.addActionListener(new calculateListener(atom,part,mole,gmol,gram,aprt,3));

		frame.add(root);
		
		try {
			frame.setIconImage(ImageIO.read(PeriodicTable.class.getResourceAsStream("icon.png")));
		} catch (Exception qq) {
			
		}

		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);   

		try {
			WebButton compound = new WebButton("Check Compounds Database","https://pubchem.ncbi.nlm.nih.gov/compound/");
			WebButton substance = new WebButton("Check Substances Databse","https://pubchem.ncbi.nlm.nih.gov/substance/");
			WebButton wiki = new WebButton("Check Wikipedia","https://en.wikipedia.org/wiki/");
			
			database.add(compound);
			database.add(substance);
			database.add(wiki);
			
			frame.add(database);
		} catch (Exception e) {
			
		}
		frame.setVisible(true);
	}
}
class calculateListener implements ActionListener {
	JTextField atom, part, mole, gmol, gram, aprt;
	int num;
	int atoms;
	double avgNum = 6.022 * Math.pow(10, 23);
	public calculateListener(JTextField atm, JTextField par, JTextField mol, JTextField gmo, JTextField gra, JTextField apr, int num) {
		atom = atm;
		part = par;
		mole = mol;
		gmol = gmo;
		gram = gra;
		aprt = apr;
		//function
		this.num = num;
		//total atoms
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			
			double atm = Double.parseDouble(atom.getText());
			double par = Double.parseDouble(part.getText());
			double mol = Double.parseDouble(mole.getText());
			double gmo = Double.parseDouble(gmol.getText());
			double gra = Double.parseDouble(gram.getText());
			double apr = Double.parseDouble(aprt.getText());
			switch (num) {
			case 0://given: Atoms
				par = atm / apr;
				mol = par / avgNum;
				gra = mol * gmo;
				break;
			case 1://given: Particles
				atm = par * apr;
				mol = par / avgNum;
				gra = mol * gmo;
				break;
			case 2://given: Moles
				par = avgNum * mol;
				atm = par * apr;
				gra = mol * gmo;
				break;
			case 3://given: Grams (OK)
				mol = gra / gmo;
				par = mol * avgNum;
				atm = par * apr;
				break;
			}

			atom.setText(atm+"");
			part.setText(par+"");
			mole.setText(mol+"");
			gram.setText(gra+"");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Enter a number\nMake sure no fields are blank.", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
