package com.qxbytes.chemlab;

import java.awt.Color;
import java.util.List;

import javax.swing.JTextField;
/**
 * 
 * @author QxBytes
 *
 */
public class Element {
	private int num;
	private String symbol;
	private String name;
	
	private int group;
	private String groupname;
	private int period;
	private String periodname;
	private double weight;
	private int massnum;
	private int state = 0;
	private int amount = 0;
	private int abscount = 1;
	
	private boolean radioactive = false;
	private boolean artificial = false;
	private boolean metal = true;
	/*
	 * 
	 * Constructors
	 * 
	 */
	public Element() {
		this (PeriodicConstants.getElement().get(0));
	}
	/**
	 * 
	 * @param n
	 * @param sym
	 * @param name
	 * @param weight
	 * @param group
	 * @param period
	 * @param metal
	 * @param state
	 * @param massnum
	 */
	public Element(int n, String sym, String name, double weight, int group, int period, boolean metal, int state, int massnum) {
		this.num = n;
		this.symbol = sym;
		this.name = name;
		this.weight = weight;
		this.group = group;
		this.period = period;
		this.metal = metal;
		this.state = state;
		this.massnum = massnum;
		pre();
	}
	/**
	 * 
	 * @param n
	 * @param sym
	 * @param name
	 * @param weight
	 * @param group
	 * @param period
	 * @param massnum
	 */
	public Element(int n, String sym, String name, double weight, int group, int period, int massnum) {
		this.num = n;
		this.symbol = sym;
		this.name = name;
		this.weight = weight;
		this.group = group;
		this.period = period;
		this.massnum = massnum;
		pre();
	}
	public Element(Element e) {
		this.num = e.getNum();
		this.symbol = e.getSymbol();
		this.name = e.getName();
		this.weight = e.getWeight();
		this.group = e.getGroup();
		this.period = e.getPeriod();
		this.massnum = e.getMassnum();
		this.metal = e.isMetal();
		this.state = e.getState();
		
		pre();
	}
	public void pre() {
		if (this.getNum() >= 92) {
			this.setState(-1);
			this.setArtificial(true);
		}
	}
	/*
	 * Display on screen
	 */
	
	public Color getStateColor() {
		if (this.getState() == -1 )	 {
			return Color.GREEN;
		} else if (this.getState() == 0) {
			return Color.DARK_GRAY;
		} else if (this.getState() == 1) {
			return Color.CYAN;
		}
		return Color.ORANGE;
		
	}
	public Color getMetalColor() {
		if (this.isMetal()) {
			return Color.BLUE;
		} else {
			return Color.RED;
		}
	}
	/*
	 * Algorithms
	 */
	public String getCharge() {
		if ((this.getGroup() < 13 && this.getGroup() > 2 ) || this.getGroup() == 18) {
			return 0+"";
		}
		if (this.getGroup() < 3) {
			return "+"+this.getGroup();
		} else if (this.getGroup() == 13) {
			return "+3";
		} else if (this.getGroup() == 14) {
			return "±4";
		} else {
			return ""+(-(18 - this.getGroup()));
		}
	}
	public double getAbsCharge() {
		if ((this.getGroup() < 13 && this.getGroup() > 2 ) || this.getGroup() == 18) {
			return 0;
		}
		if (this.getGroup() < 3) {
			return this.getGroup();
		} else if (this.getGroup() == 13) {
			return 3;
		} else if (this.getGroup() == 14) {
			return 4;
		} else {
			return (-(18 - this.getGroup()));
		}
	}
	public int getS1() {
		if (this.getElectrons() > 2) {
			return 2;
		} else {
			return this.getElectrons();
		}
	}
	public int getS2() {
		if (this.getElectrons() > 10) {
			return 8;
		} else if (this.getElectrons() > 2){
			return this.getElectrons()-2;
		}
		return 0;
	}
	public int getS3() {
		if (this.getElectrons() > 28) {
			return 18;
		} else if (this.getElectrons() > 10) {
			return this.getElectrons()-10;
		}
		return 0;
	}
	public int getS4() {
		if (this.getElectrons() > 60) {
			return 32;
		} else if (this.getElectrons() > 28) {
			return this.getElectrons()-28;
		}
		return 0;
	}
	public int getNeutrons() {
		return this.massnum - this.getNum();
	}
	public boolean isTransition() {
		if (this.getCharge().equals("0") && group != 18) {
			return true;
		}
		return false;
	}
	public boolean isNoble() {
		if (this.getGroup() == 18) return true;
		return false;
	}
	public String getBalancedEquation(Element e1) {
		if (isTransition() && e1.isTransition()) {
			return "Double Transition";
		}
		if (isNoble() || e1.isNoble()) {
			return "Too Noble";
		}
		
		if (e1.getAbsCharge() == getAbsCharge()) {
			return "Two metals/Nonmetals";
		}
		
		if (isTransition() || e1.isTransition()) {
			return 1 + e1.getSymbol() + " " + 1 + getSymbol();

		}
		//Proper Practice to put last first
		if (getAbsCharge() > 0) {
			return Math.abs(getAbsCharge()) + e1.getSymbol() + " " + Math.abs(e1.getAbsCharge()) + getSymbol();
		}
		//Normal SOP
		return Math.abs(e1.getAbsCharge()) + getSymbol() + " " + Math.abs(getAbsCharge()) + e1.getSymbol();
		
	}
	public int getElectrons() {
		return getNum();
	}
	/*
	 * 
	 * Where Getters and Setters Start
	 * 
	 */
	
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the group
	 */
	public int getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(int group) {
		this.group = group;
	}
	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}
	/**
	 * @param groupname the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	/**
	 * @return the period
	 */
	public int getPeriod() {
		return period;
	}
	/**
	 * @param period the period to set
	 */
	public void setPeriod(int period) {
		this.period = period;
	}
	/**
	 * @return the periodname
	 */
	public String getPeriodname() {
		return periodname;
	}
	/**
	 * @param periodname the periodname to set
	 */
	public void setPeriodname(String periodname) {
		this.periodname = periodname;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the radioactive
	 */
	public boolean isRadioactive() {
		return radioactive;
	}
	/**
	 * @param radioactive the radioactive to set
	 */
	public void setRadioactive(boolean radioactive) {
		this.radioactive = radioactive;
	}
	/**
	 * @return the artificial
	 */
	public boolean isArtificial() {
		return artificial;
	}
	/**
	 * @param artificial the artificial to set
	 */
	public void setArtificial(boolean artificial) {
		this.artificial = artificial;
	}
	/**
	 * @return the metal
	 */
	public boolean isMetal() {
		return metal;
	}
	/**
	 * @param metal the metal to set
	 */
	public void setMetal(boolean metal) {
		this.metal = metal;
	}
	/**
	 * @return the massnum
	 */
	public int getMassnum() {
		return massnum;
	}
	/**
	 * @param massnum the massnum to set
	 */
	public void setMassnum(int massnum) {
		this.massnum = massnum;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return the abscount
	 */
	public int getAbscount() {
		return abscount;
	}
	/**
	 * @param abscount the abscount to set
	 */
	public void setAbscount(int abscount) {
		this.abscount = abscount;
	}
	/**
	 * ToString
	 */
	@Override
	public String toString() {
		if (amount == 0) {
			return "";
		}
		return this.getSymbol()+this.getAmount();
	}
	/**
	 * 
	 * @param n
	 * @param sym
	 * @param name
	 * @param weight
	 * @param group
	 * @param period
	 * @param metal
	 * @param state
	 * @param massnum
	 */
	public String toData() {
		String returned = "";
		
		returned += ("" + num);
		returned += (":" + symbol);
		returned += (":" + name);
		returned += (":" + weight);
		returned += (":" + group);
		returned += (":" + period);
		returned += (":" + metal);
		returned += (":" + state);
		returned += (":" + massnum);
		
		return returned;
	}
	/**
	 * Returns absolute data including all functions -amount.
	 */
	public String toAbsoluteData() {
		List<JTextField> lbs = Utils.eString(this);
		
		String x = "";
		for (JTextField j : lbs) {
			x += j.getText() + "\n";
		}
		return x;
	}
	/**
	 * Compares absolute data. Does not include amounts.
	 */
	public boolean equals(Element e) {
		return this.toAbsoluteData().equals(e.toAbsoluteData());
	}
}
