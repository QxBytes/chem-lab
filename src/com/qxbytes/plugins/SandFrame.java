package com.qxbytes.plugins;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qxbytes.chemlab.Element;
import com.qxbytes.chemlab.ElementDisplay;
import com.qxbytes.chemlab.PeriodicConstants;
import com.qxbytes.chemlab.PicturePane;
import com.qxbytes.chemlab.Utils;
/**
 * 
 * @author QxBytes
 *
 */
public class SandFrame {
	JFrame f;
	Element e;
	ElementDisplay ed;
	PicturePane pp;
	List<JTextField> labels = new ArrayList<JTextField>();
	List<JTextField> data;
	public SandFrame() {
		f = new JFrame();
		e = PeriodicConstants.getElement().get(0);
		ed = new ElementDisplay(e);
		pp = new PicturePane(e);
		
		f.setLayout(new GridLayout(1,0));
		f.setSize(600, 300);
		f.setLocationRelativeTo(null);
		
		JPanel info = new JPanel(new GridLayout(0,2));
		
		List<String> temp = Utils.getParamNames();
		for (int i = 0 ; i < temp.size() ; i++) {
			labels.add(new JTextField(temp.get(i)));
			labels.get(labels.size()-1).setEditable(false);
		}
		
		data = Utils.toDisplayableData(e);
		
		int ln = 0;
		int dn = 0;
		for (int i = 0 ; i < labels.size() + data.size() ; i++) {
			if (i % 2 == 0) {
				//label
				info.add(labels.get(ln));
				ln++;
			} else {
				//data
				data.get(dn).addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent ex) {
						String x = "";
						
						for (int i = 0 ; i < data.size() ; i++) {
							x+= data.get(i).getText()+":";
						}
						
						e = Utils.fromFormattedString(x);
						
						f.remove(pp);
						f.remove(ed);
						
						pp = new PicturePane(e);
						ed = new ElementDisplay(e);
						
						f.add(pp);
						f.add(ed);
						
						f.revalidate();
						f.repaint();
						
					}
					
				});
				info.add(data.get(dn));
				dn++;
			}
		}
		
		f.add(info);
		f.add(pp);
		f.add(ed);
		
		f.setVisible(true);
	}
}
