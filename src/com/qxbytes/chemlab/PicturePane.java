package com.qxbytes.chemlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 
 * @author QxBytes
 *
 */
public class PicturePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Element e;
	public PicturePane(Element e) {
		this.e = e;
	}
	public void paintComponent(Graphics g) {
		
		g.setColor(new Color(175,250,250));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		
		g.drawOval(0, 0, this.getWidth(), this.getWidth());//2
		
		g.drawOval(10, 10, this.getWidth()-20, this.getWidth()-20);//8
		
		g.drawOval(20, 20, this.getWidth()-40, this.getWidth()-40);//18
		
		g.drawOval(30, 30, this.getWidth()-60, this.getWidth()-60);//32
		
		//radii
		int s4 = (this.getWidth())/2;
		int s3 = (this.getWidth()-20)/2;
		int s2 = (this.getWidth()-40)/2;
		int s1 = (this.getWidth()-60)/2;
		
		int m = this.getWidth()/2;
		
		double co = Math.PI / 180;
		
		double a1 = 360 / 2 * co;
		double a2 = 360 / 8 * co;
		double a3 = 360 / 18 * co;
		double a4 = 360 / 32 * co;
		
		g.setColor(Color.RED);
		
		Color v = new Color(180,0,210);
		
		for (int i = 0 ; i < e.getS1() ; i++) {
			if (e.getS1() < 2) {
				g.setColor(v);
			}
			float x = (float) (s1*Math.cos(a1*i) + m)-3;
			float y = (float) (s1*Math.sin(a1*i) + m)-3;
			g.fillOval((int)x, (int)y, 5, 5);
		}
		
		for (int i = 0 ; i < e.getS2() ; i++) {
			if (e.getS2() < 8) {
				g.setColor(v);
			}
			float x = (float) (s2*Math.cos(a2*i) + m)-3;
			float y = (float) (s2*Math.sin(a2*i) + m)-3;
			g.fillOval((int)x, (int)y, 5, 5);
		}
		
		for (int i = 0 ; i < e.getS3() ; i++) {
			if (e.getS3() < 18) {
				g.setColor(v);
			}
			float x = (float) (s3*Math.cos(a3*i) + m)-3;
			float y = (float) (s3*Math.sin(a3*i) + m)-3;
			g.fillOval((int)x, (int)y, 5, 5);
		}
		
		for (int i = 0 ; i < e.getS4() ; i++) {
			if (e.getS4() < 32) {
				g.setColor(v);
			}
			float x = (float) (s4*Math.cos(a4*i) + m)-3;
			float y = (float) (s4*Math.sin(a4*i) + m)-3;
			g.fillOval((int)x, (int)y, 5, 5);
		}
		
		int mn = m-e.getNeutrons()/4;
		int nr = e.getNeutrons()/2;
		
		int mp = m-e.getNum()/4;
		int pr = e.getNum()/2;
		g.setColor(Color.RED);
		
		g.drawOval(mn,mn,nr,nr);
		
		g.setColor(Color.ORANGE);
		
		g.drawOval(mp,mp,pr,pr);
		
		g.setColor(e.getStateColor());
		g.setFont(new Font("Sans Serif",Font.PLAIN,32));
		g.drawString(e.getSymbol(), 20, this.getWidth()+32);
		
		g.setFont(new Font("Sans Serif",Font.PLAIN,12));
		g.drawString(e.getCharge(), 50, this.getWidth()+20);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Sans Serif",Font.PLAIN,16));
		g.drawString(e.getNum() + "P/" + e.getNeutrons() + "N", m-32, m);
		
		g.setColor(e.getStateColor());
		g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
	}
}
