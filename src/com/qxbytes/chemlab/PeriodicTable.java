package com.qxbytes.chemlab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.qxbytes.plugins.PLUGINMANIFEST;
/**
 * 
 * @author QxBytes
 *
 */
public class PeriodicTable {
	private List<ElementDisplay> buttons = new ArrayList<ElementDisplay>();
	private List<Element> ELEMENTS = new ArrayList<Element>();
	private List<Element> selected = new ArrayList<Element>();
	public PeriodicTable() {
		PeriodicConstants.loadElements();
		ELEMENTS = PeriodicConstants.getElement();

		JFrame frame = new JFrame("QChem- Periodic Table");
		frame.setLayout(new BorderLayout());
		frame.setSize(DC.PW,DC.PH);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			frame.setIconImage(ImageIO.read(PeriodicTable.class.getResourceAsStream("icon.png")));
		} catch (Exception e) {
			
		}

		JPanel table = new JPanel(new GridLayout(5,18));

		int counter = 1;
		for (int y = 0 ; y < 5 ; y++) {
			for (int x = 0 ; x < 18 ; x++) {
				if (counter < ELEMENTS.size() 
						&& ELEMENTS.get(counter).getPeriod()-1 == y && ELEMENTS.get(counter).getGroup()-1 == x) {
					ElementDisplay button = new ElementDisplay(ELEMENTS.get(counter),selected);
					buttons.add(button);
					table.add(button);
					
					counter++;
				} else {
					/*
					 * Filler
					 */
					JPanel temp = new JPanel();
					temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					table.add(temp);
				}
			}
		}

		frame.add(table,BorderLayout.CENTER);
		
		AnalyzePane analysis = new AnalyzePane();
		frame.add(analysis,BorderLayout.NORTH);
		
		PLUGINMANIFEST pm = new PLUGINMANIFEST();
		frame.add(pm,BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
}
