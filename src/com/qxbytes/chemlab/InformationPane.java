package com.qxbytes.chemlab;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;
/**
 * 
 * @author QxBytes
 *
 */
public class InformationPane {
	public InformationPane(List<String> data, String name) {
		JFrame frame = new JFrame(name);
		frame.setLayout(new GridLayout(data.size(),1));
		for (int i = 0 ; i < data.size() ; i++) {
			JTextField x = new JTextField(data.get(i));
			
			x.setEditable(false);
			frame.add(x);
		}
		
		frame.setVisible(true);
	}
}
