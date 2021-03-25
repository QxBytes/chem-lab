package com.qxbytes.chemlab;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
/**
 * 
 * @author QxBytes
 *
 */
public class WebButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WebButton(String name, String prefix) {
		super(name);
		
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(prefix + SelectionPane.getFrame().getTitle().substring(10).replaceAll("1", "")));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
