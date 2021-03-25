package com.qxbytes.plugins;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qxbytes.chemlab.Element;
import com.qxbytes.chemlab.PicturePane;
/**
 * 
 * @author QxBytes
 *
 */
public class Pair extends PItem {
	private List<Element> pair;
	private JFrame f;
	public Pair() {
		super();
		
	}
	public void doStuff() {
		f = new JFrame(); 
		pair = Plugin.getPair();
		
		if (pair == null || pair.get(1) == null) {
			f.dispose();
			return;
		}
		
		f.setTitle(pair.get(0).getName() + "+" + pair.get(1).getName());
		f.setSize(200,300);
		f.setLocationRelativeTo(null);
		f.setLayout(new GridLayout(2,1));
		
		 JPanel top = new JPanel(new GridLayout(1,2));
		 
		 top.add(new PicturePane(pair.get(0)));
		 top.add(new PicturePane(pair.get(1)));
		 
		 f.add(top);
		 
		 JPanel bot = new JPanel(new GridLayout(0,2));
		 
		 List<JTextField> data = new ArrayList<JTextField>();
		 data.add(new JTextField("Formula:"));
		 data.add(new JTextField(pair.get(0).getBalancedEquation(pair.get(1))));
		 
		 for (int i = 0 ; i < data.size() ; i++) {
			 data.get(i).setEditable(false);
			 
			 bot.add(data.get(i));
		 }
		 
		 f.add(bot);
		 
		f.setVisible(true);
	}
}
