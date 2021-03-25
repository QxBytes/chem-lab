package com.qxbytes.chemlab;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author QxBytes
 *
 */
public class SortElement {
	@Deprecated
	public static List<Element> sort(List<Element> elements) {
		PeriodicConstants.loadElements();
		List<Element> allelements = PeriodicConstants.getElement();
		List<Element> data = new ArrayList<Element>(elements);
		/*
		 * Every slot of data/One piece of data
		 */
		for (int x = 0 ; x < data.size() ; x++) {
			/*
			 * To every element
			 */
			for (int i = 0 ; i < allelements.size() ; i++) {
				if (data.get(x).toData().equals(allelements.get(i).toData())) {
					allelements.get(i).setAmount(allelements.get(i).getAmount()+1);
					//System.out.println(allelements.get(i).getAmount());
					break;
				}
			}
		}
		return allelements;
	}
	/**
	 * Adds an element; assumes compressed; if duplicate found, will increase count by one.
	 * @param elements
	 * @param e
	 * @return If succeeded in finding a duplicate.
	 */
	public static boolean addElement(List<Element> elements, Element e) {
		//Utils.compress(elements);
		for (int i = 0 ; i < elements.size() ; i++) {
			if (elements.get(i).equals(e)) {
				elements.get(i).setAbscount(elements.get(i).getAbscount()+1);
				return true;
			}
		}
		elements.add(e);
		return false;
	}
	private static List<Element> altSort(List<Element> elements) {
		PeriodicConstants.loadElements();
		List<Element> allelements = new ArrayList<Element>();
		List<Element> data = new ArrayList<Element>(elements);
		for (int i = 0 ; i < data.size() ; i++) {
			data.get(i).setAmount(0);
		}
		
		/*
		 * 
		 */
		allelements.add(data.get(0));
		/*
		 * Every slot of data/One piece of data
		 */
		for (int x = 0 ; x < data.size() ; x++) {
			/*
			 * To every element
			 */
			for (int i = 0 ; i < allelements.size() ; i++) {
				if (data.get(x).toData().equals(allelements.get(i).toData())) {
					allelements.get(i).setAmount(allelements.get(i).getAmount()+1);
					//System.out.println(allelements.get(i).getAmount());
					break;
				}
				if (i == allelements.size()-1) {
					allelements.add(data.get(x));
				}
			}
		}
		return allelements;
	}
	public static String writeFormula(List<Element> elements) {
		List<Element> x = altSort(Utils.decompress(elements));

		String display = "";
		for (int i = 0 ; i < x.size() ; i++	) {
			display += x.get(i).toString();
		}
		return display;
	}
	public static void swap(List<Element> e,int in1, int in2) {
		Element temp = e.get(in1);
		e.set(in1, e.get(in2));
		e.set(in2, temp);
	}
}
