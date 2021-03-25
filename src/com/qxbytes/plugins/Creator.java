package com.qxbytes.plugins;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import com.qxbytes.chemlab.Element;
import com.qxbytes.chemlab.ElementDisplay;
import com.qxbytes.chemlab.PeriodicConstants;
import com.qxbytes.chemlab.PicturePane;
/**
 * 
 * @author QxBytes
 *
 */
public class Creator extends PItem {
	JFrame f;
	JSpinner protons;
	JSpinner neutrons;
	JSpinner electrons;
	JButton button;
	PicturePane p;
	ElementDisplay ed;
	IonIsotopePane iip;
	Element e;
	@Override
	public void doStuff() {
		e = PeriodicConstants.getElement().get(0);
		ed = new ElementDisplay(e);
		p = new PicturePane(e);
		iip = new IonIsotopePane(e);
		
		f = new JFrame();
		f.setSize(700, 350);
		f.setLocationRelativeTo(null);
		f.setLayout(new BorderLayout());
		
		protons = new JSpinner();
		neutrons = new JSpinner();
		electrons = new JSpinner();
		button = new JButton("Update");
		
		JPanel top = new JPanel();
		JPanel center = new JPanel(new GridLayout(1,0));
		
		top.add(new JLabel("Protons:"));
		top.add(protons);
		top.add(new JLabel("Neutrons:"));
		top.add(neutrons);
		top.add(new JLabel("Electrons:"));
		top.add(electrons);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = (Integer)protons.getValue();
				int ii = (Integer)neutrons.getValue();
				int iii = (Integer)electrons.getValue();
				
				SElement base;
				if (i < PeriodicConstants.getElement().size()) {
					base = new SElement(PeriodicConstants.getElement().get(i),ii,iii);
					base.setProtons(i);
					
					f.setTitle("(partial extrapolation)");
				} else {
					base = new SElement(i,ii,iii);
					
					f.setTitle("(extrapolated)");
				}
				
				p = new PicturePane(base);
				
				ed = new ElementDisplay(base);
				
				iip = new IonIsotopePane(base);
				
				center.removeAll();
				center.add(p);
				center.add(ed);
				center.add(iip);
				
				f.add(center,BorderLayout.CENTER);
				
				f.revalidate();
			}
			
		});
		
		center.add(p);
		center.add(ed);
		center.add(iip);
		
		f.add(center, BorderLayout.CENTER);
		
		f.add(button,BorderLayout.SOUTH);
		
		f.add(top,BorderLayout.NORTH);
		
		f.setVisible(true);
	}

}
