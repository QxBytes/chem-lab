package com.qxbytes.plugins;

import com.qxbytes.chemlab.Element;
/**
 * 
 * @author QxBytes
 *
 */
public class SElement extends Element {
	int protons;
	int neutrons;
	int electrons;
	public SElement (int pro, int neu, int ele) {
		protons = pro;
		neutrons = neu;
		electrons = ele;
		super.pre();
	}
	public SElement (Element pro, int neutrons, int electrons) {
		super(pro);
		
		this.setProtons(pro.getNum());
		this.setNeutrons(neutrons);
		this.setElectrons(electrons);
	}
	public int getElectrons() {
		return electrons;
	}
	public int getNeutrons() {
		return neutrons;
	}
	public int getNum() {
		return protons;
	}
	public int getMassnum() {
		return protons+neutrons;
	}
	/**
	 * @param protons the protons to set
	 */
	public void setProtons(int protons) {
		this.protons = protons;
	}
	/**
	 * @param neutrons the neutrons to set
	 */
	public void setNeutrons(int neutrons) {
		this.neutrons = neutrons;
	}
	/**
	 * @param electrons the electrons to set
	 */
	public void setElectrons(int electrons) {
		this.electrons = electrons;
	}
	
	public String toData() {
		String r = super.toData();
		
		r += ":" + protons;
		r += ":" + neutrons;
		r += ":" + electrons;
		
		return r;
	}
	
}
