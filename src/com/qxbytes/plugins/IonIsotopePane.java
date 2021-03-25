package com.qxbytes.plugins;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qxbytes.chemlab.Element;
import com.qxbytes.chemlab.PeriodicConstants;
/**
 * 
 * @author QxBytes
 *
 */
public class IonIsotopePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SElement se;
	List<JTextField> data = new ArrayList<JTextField>();
	public IonIsotopePane(Element e) {
		
	}
	public IonIsotopePane(SElement se) {
		this.se = se;
		this.setLayout(new GridLayout(0,2));
		/*
		 * Assume that this element needs to be completely extrapolated
		 */
		//charge 
		int ion = se.getNum() - se.getElectrons();
		
		//isotope ratio
		int iso =  se.getNeutrons();
		int predictedstable = (int) (se.getNum() * 1.6);
		
		//name
		String element = se.getName()+"-" + (se.getNeutrons()+se.getNum());
		
		//massn
		int massn = iso + se.getNum();
		
		double weight = massn;
		
		/*
		 * Partial extrapolation
		 */
		if (PeriodicConstants.getElementFromIndex(se.getNum()-1).getNum() == se.getNum()) {
			int difference = PeriodicConstants.getElementFromIndex(se.getNum()-1).getNeutrons()-se.getNeutrons();
			weight = PeriodicConstants.getElementFromIndex(se.getNum()-1).getWeight()-difference;
			predictedstable = PeriodicConstants.getElementFromIndex(se.getNum()-1).getNeutrons();
		}
		
		
		data.add(new JTextField("Isotope: "));
		data.add(new JTextField(""+iso));
		data.add(new JTextField("Ion: "));
		data.add(new JTextField(""+ion));
		data.add(new JTextField("Element: "));
		data.add(new JTextField(element));
		data.add(new JTextField("Massnum(ex): "));
		data.add(new JTextField(massn+""));
		data.add(new JTextField("Weight(ex): "));
		data.add(new JTextField(weight+""));
		data.add(new JTextField("Stable Isotope: "));
		data.add(new JTextField(predictedstable+""));
		
		for (JTextField x : data) {
			x.setEditable(false);
			add(x);
		}

	}


}
