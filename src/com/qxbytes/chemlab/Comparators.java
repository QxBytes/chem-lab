package com.qxbytes.chemlab;

import java.util.Comparator;
/**
 * 
 * @author QxBytes
 *
 */
public class Comparators {

}
class electronegativityComparator implements Comparator<Element> {
	@Override
	public int compare(Element o1, Element o2) {
		//Distance from fluorine
		// sqrt((y2-y1)^2 + (x2-x1)^2)
		int d1x = Math.abs(17-o1.getGroup());
		int d1y = Math.abs(2 -o1.getPeriod());
		int distance = d1x + d1y;
		
		int d2x = Math.abs(17-o2.getGroup());
		int d2y = Math.abs(2 -o1.getPeriod());
		int distance2 = d2x + d2y;
		
		return distance2-distance;
	}
	
}
class sizeComparator implements Comparator<Element> {

	@Override
	public int compare(Element o1, Element o2) {
		if (o1.getPeriod() > o2.getPeriod()) {
			return 1;
		} else if (o1.getGroup() > o2.getGroup()){
			return -1;
		}
		return 0;
	}
	
}
class lexographicComparator implements Comparator<Element> {

	@Override
	public int compare(Element o1, Element o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}
class numberComparator implements Comparator<Element> {

	@Override
	public int compare(Element o1, Element o2) {
		
		return o1.getNum()-o2.getNum();
	}
	
}