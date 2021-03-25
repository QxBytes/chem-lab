package com.qxbytes.chemlab;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * 
 * @author QxBytes
 *
 */
public class ElementItemListener implements MouseListener {
	ElementDisplay d;
	public ElementItemListener(ElementDisplay d) {
		this.d = d;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		 
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		d.setHover(true);
		d.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		d.setHover(false);
		d.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		 
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		 
		
	}

}
