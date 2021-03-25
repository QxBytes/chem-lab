package com.qxbytes.chemlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
/**
 * 
 * @author QxBytes
 *
 */
public class ElementDisplay extends JButton {
	private Element e;
	private boolean hover = false;
	private List<Element> selected = new ArrayList<Element>();
	public ElementDisplay(Element e, List<Element> selected) {
		super();
		this.e = e;
		this.selected = selected;
		this.setToolTipText(Utils.getElementTooltip(e));
		this.setBorder(BorderFactory.createLineBorder(e.getMetalColor()));
		this.addMouseListener(new ElementItemListener(this));
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				selected.add(e);
				
				SelectionPane.showDetailedPane(e);
				
				//double atom = 0;
				for (int i = 0 ; i < selected.size() ; i++) {
					//atom += selected.get(i).getWeight() ;
				}
				//System.out.println("Total: " + atom);
			}
			
		});
	}
	public ElementDisplay(Element e) {
		super();
		this.e = e;
		this.setBorder(BorderFactory.createLineBorder(e.getMetalColor()));
		this.addMouseListener(new ElementItemListener(this));
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectionPane.showDetailedPane(e);
			}
			
		});
	}
	
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		g.setColor(e.getStateColor());
		
		g.setFont(new Font("Sans Serif",Font.PLAIN,12));
		g.drawString(e.getNum()+"",0,12);
		g.drawString(e.getCharge()+"", 30, 18);
		g.setFont(new Font("Sans Serif",Font.PLAIN,32));
		g.drawString(e.getSymbol(), 2, 40);
		g.setFont(new Font("Sans Serif",Font.PLAIN,10));
		g.drawString(e.getName(), 0, 60);
		g.setFont(new Font("Sans Serif",Font.PLAIN,12));
		g.drawString(e.getMassnum()+"", 0, 80);
		g.drawString(e.getWeight()+"", 0, 92);
		
		if (hover) {
			g.setColor(new Color(100,200,200,100));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.GREEN);
			g.drawString("+Add", this.getWidth()/2-17, this.getHeight()/2+5);
		}
	}
	public List<Element> getSelected() {
		return selected;
	}
	public void setHover(boolean h) {
		hover = h;
	}
	
}
