package com.qxbytes.chemlab;

import java.util.ArrayList;
import java.util.List;

import com.qxbytes.plugins.SElement;
/**
 * 
 * @author QxBytes
 *
 */
public class PeriodicConstants {
	static List<Element> e = new ArrayList<Element>();
	public static void loadElements() {
		e.removeAll(e);
		
		e.add(new Element(0,"?","Unstable",0.00,0,0,false,0,0));
		
		e.add(new Element(1,"H","Hydrogen",1.0078,17,1,false,2,1));
		e.add(new Element(2,"He","Helium",4.0026,18,1,false,2,4));
		
		e.add(new Element(3,"Li","Lithium",6.938,1,2,7));
		e.add(new Element(4,"Be","Beryllium",9.0122,2,2,9));
		e.add(new Element(5,"B","Boron",10.806,13,2,11));
		e.add(new Element(6,"C","Carbon",12.009,14,2,false,0,12));
		e.add(new Element(7,"N","Nitrogen",14.006,15,2,false,2,14));
		e.add(new Element(8,"O","Oxygen",15.999,16,2,false,2,16));
		e.add(new Element(9,"F","Fluorine",18.998,17,2,false,2,19));
		e.add(new Element(10,"Ne","Neon",20.180,18,2,false,2,20));
		
		e.add(new Element(11,"Na","Sodium",22.99,1,3,23));
		e.add(new Element(12,"Mg","Magnesium",24.305,2,3,24));
		e.add(new Element(13,"Al","Aluminum",26.982,13,3,27));
		e.add(new Element(14,"Si","Silicon",28.084,14,3,28));
		e.add(new Element(15,"P","Phosphorus",30.974,15,3,false,0,31));
		e.add(new Element(16,"S","Sulfur",32.059,16,3,false,0,32));
		e.add(new Element(17,"Cl","Chlorine",35.446,17,3,false,2,35));
		e.add(new Element(18,"Ar","Argon",39.948,18,3,false,2,40));
		
		e.add(new Element(19,"K","Potassium",39.098,1,4,39));
		e.add(new Element(20,"Ca","Calcium",40.078,2,4,40));
		e.add(new Element(21,"Sc","Scandium",44.956,3,4,45));
		e.add(new Element(22,"Ti","Titanium",47.867,4,4,48));
		e.add(new Element(23,"V","Vanadium",50.942,5,4,51));
		e.add(new Element(24,"Cr","Chromium",51.996,6,4,52));
		e.add(new Element(25,"Mn","Maganese",54.938,7,4,55));
		e.add(new Element(26,"Fe","Iron",55.845,8,4,56));
		e.add(new Element(27,"Co","Cobalt",58.933,9,4,59));
		e.add(new Element(28,"Ni","Nickel",58.693,10,4,59));
		e.add(new Element(29,"Cu","Copper",63.546,11,4,64));
		e.add(new Element(30,"Zn","Zinc",65.38,12,4,65));
		e.add(new Element(31,"Ga","Gallium",69.723,13,4,70));
		e.add(new Element(32,"Ge","Germanium",72.63,14,4,73));
		e.add(new Element(33,"As","Arsenic",74.922,15,4,75));
		e.add(new Element(34,"Se","Selenium",78.96,16,4,false,0,79));
		e.add(new Element(35,"Br","Bromine",79.904,17,4,false,1,80));
		e.add(new Element(36,"Kr","Krypton",83.798,18,4,false,2,84));
		
		e.add(new Element(37,"Rb","Rubidium",85.468,1,5,85));
		e.add(new Element(38,"Sr","Strontium",87.62,2,5,88));
		e.add(new Element(39,"Y","Yttrium",88.906,3,5,89));
		e.add(new Element(40,"Zr","Zirconium",91.224,4,5,91));
		e.add(new Element(41,"Nb","Niobium",92.906,5,5,93));
		e.add(new Element(42,"Mo","Molybdenum",95.96,6,5,96));
		e.add(new Element(43,"Tc","Technetium",98.9062,7,5,98));
		e.add(new Element(44,"Ru","Ruthenium",101.07,8,5,101));
		e.add(new Element(45,"Rh","Rhodium",102.91,9,5,103));
		e.add(new Element(46,"Pd","Palladium",106.42,10,5,106));
		e.add(new Element(47,"Ag","Silver",101.87,11,5,108));
		e.add(new Element(48,"Cd","Cadmium",112.41,12,5,112));
		e.add(new Element(49,"In","Indium",113.82,13,5,115));
		e.add(new Element(50,"Sn","Tin",118.71,14,5,119));
		e.add(new Element(51,"Sb","Antimony",121.76,15,5,122));
		e.add(new Element(52,"Te","Tellurium",127.6,16,5,128));
		e.add(new Element(53,"I","Iodine",126.90,17,5,127));
		e.add(new Element(54,"Xe","Xenon",131.29,18,5,false,2,131));
	}
	public static List<Element> getElement() {
		return e;
	}
	/**
	 * Not implemented
	 * @param e
	 * @return
	 */
	public static String getElectronConfig(Element e) {
		
		
		return "";
	}
	public static Element getElement(String x) {
		for (int i = 0 ; i < e.size() ; i++) {
			if (getElement().get(i).getSymbol().equals(x) || getElement().get(i).getName().equalsIgnoreCase(x)) {
				return getElement().get(i);
			}
		}
		return null;
	}
	public static Element getElementFromIndex(int num) {
		if (num > getElement().size()) return getElement().get(0);
		return getElement().get(num+1);
	}
	/**
	 * Obtain a sandbox element. It may, or may not, exist.
	 * @param pro
	 * @param neu
	 * @param elec
	 * @return Sandbox Element
	 */
	public static SElement getElement(int pro, int neu, int elec) {
		PeriodicConstants.loadElements();
		
		if (pro > 0 && pro < getElement().size()) {
			return new SElement(PeriodicConstants.getElement().get(pro+1),neu,elec);
		} else {
			return new SElement(pro,neu,elec);
		}
	}
}
