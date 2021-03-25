package com.qxbytes.plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
/**
 * 
 * @author QxBytes
 *
 */
public class PButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PItem> windows = new ArrayList<PItem>();
	
	public PButton(String name) {
		this.setName(name);
		this.setText(name);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0 ; i < windows.size() ; i++) {
					windows.get(i).doStuff();
				}
			}
			
		});
	}
	public void addFrame(PItem p) {
		windows.add(p);
	}
}
