package com.qxbytes.chemlab;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import com.qxbytes.plugins.SElement;
/**
 * 
 * @author QxBytes
 *
 */
public class Utils {
	public static List<JTextField> eString(Element e) {
		List<JTextField> lbs = new ArrayList<JTextField>();

		lbs.add(new JTextField("Atom Num: " + e.getNum()+""));
		lbs.add(new JTextField("Symbol: " + e.getSymbol()));
		lbs.add(new JTextField("Name: " + e.getName()));
		lbs.add(new JTextField("Charge: " + e.getCharge()));
		lbs.add(new JTextField("Atom Wght: "+e.getWeight()+""));
		lbs.add(new JTextField("Mass Num: " + e.getMassnum()+""));

		lbs.add(new JTextField("P/N/E :" + e.getNum() + ":"+(e.getMassnum()-e.getNum()) + ":"+e.getNum()));

		lbs.add(new JTextField("State: " + e.getState()+""));

		lbs.add(new JTextField("Metal: "+e.isMetal()+""));
		lbs.add(new JTextField("Art: " +e.isArtificial()+""));
		lbs.add(new JTextField("Rad: " +e.isRadioactive()+""));

		lbs.add(new JTextField("Row: "+e.getPeriod()+""));
		lbs.add(new JTextField("Column: "+e.getGroup()+""));

		lbs.add(new JTextField(":E/1:" + e.getS1()+"/2:" + e.getS2()+"/3:" + e.getS3()+"/4:" + e.getS4()));

		return lbs;
	}
	public static List<String> esString(Element e) {
		List<String> x = new ArrayList<String>();
		List<JTextField> xx = eString(e);
		for (int i = 0 ; i < xx.size() ; i++) {
			x.add(xx.get(i).getText().substring(xx.get(i).getText().indexOf(":")+1));
		}
		return x;
	}
	/**
	 * 
	 * @param e
	 * @return Decompressed List (Does not change original)
	 */
	public static List<Element> decompress(List<Element> e) {
		List<Element> returned = new ArrayList<Element>();
		for (int i = 0 ; i < e.size() ; i++) {
			for (int x = 0 ; x < e.get(i).getAbscount() ; x++) {
				List<String> t = parseValues(e.get(i).toData(), ':');

				int n = Integer.parseInt(t.get(0));
				String sym = t.get(1);
				String name = t.get(2);
				double weight = Double.parseDouble(t.get(3));
				int group = Integer.parseInt(t.get(4));
				int period = Integer.parseInt(t.get(5));
				boolean metal = Boolean.parseBoolean(t.get(6));
				int state = Integer.parseInt(t.get(7));
				int massnum = Integer.parseInt(t.get(8));

				if (t.size() > 9) {
					int pro = Integer.parseInt(t.get(9));
					int neu = Integer.parseInt(t.get(10));
					int ele = Integer.parseInt(t.get(11));

					Element xx = new Element(pro,sym,name,weight,group,period,metal,state,massnum);
					SElement xxx = new SElement(xx,neu,ele);

					returned.add(xxx);
				} else {
					Element xx = new Element(n,sym,name,weight,group,period,metal,state,massnum);
					returned.add(xx);
				}

				//returned.add(new Element(n,sym,name,weight,group,period,metal,state,massnum));
			}
		}
		return returned;
	}
	/**
	 * 
	 * @param e
	 * @return Compressed List (Does not change original)
	 */
	public static List<Element> compress(List<Element> e) {
		List<Element> inpane = new ArrayList<Element>();
		for (int i = 0 ; i < e.size() ; i++) {
			SortElement.addElement(inpane, e.get(i));
		}
		return inpane;
	}
	/**
	 * Precondition: Decompressed(Automatic)
	 * Postcondition: Compressed
	 * @param e
	 * @return
	 */
	public static void toSaveData(List<Element> e, File f) {
		try {
			List<Element> d = Utils.decompress(e);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0 ; i < d.size() ; i++) {
				bw.write(d.get(i).toData() + "\n");
				System.out.println(">" + d.get(i).getAbscount());
			}
			bw.close();
		} catch (Exception ee) {
		}
	}
	/**
	 * Precondition: Decompressed(Automatic)
	 * Postcondition: Compressed
	 * @param f
	 * @return
	 */
	public static List<Element> fromSaveData(File f) {
		List<Element> returned = new ArrayList<Element>();
		try {

			List<String> data = Files.readAllLines(f.toPath());

			for (int i = 0 ; i < data.size() ; i++) {
				List<String> t = parseValues(data.get(i), ':');
				/*
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
				int n = Integer.parseInt(t.get(0));
				String sym = t.get(1);
				String name = t.get(2);
				double weight = Double.parseDouble(t.get(3));
				int group = Integer.parseInt(t.get(4));
				int period = Integer.parseInt(t.get(5));
				boolean metal = Boolean.parseBoolean(t.get(6));
				int state = Integer.parseInt(t.get(7));
				int massnum = Integer.parseInt(t.get(8));

				if (t.size() > 9) {
					int pro = Integer.parseInt(t.get(9));
					int neu = Integer.parseInt(t.get(10));
					int ele = Integer.parseInt(t.get(11));

					Element xx = new Element(pro,sym,name,weight,group,period,metal,state,massnum);
					SElement xxx = new SElement(xx,neu,ele);

					SortElement.addElement(returned, xxx);
				} else {
					Element xx = new Element(n,sym,name,weight,group,period,metal,state,massnum);
					SortElement.addElement(returned, xx);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returned;
	}
	public static List<String> getParamNames() {
		String d =  "param n@param sym@param name@param weight@param group@param period@param metal@param state@param massnum";
		List<String> e = Utils.parseValues(d, '@');

		return e;
	}
	/**
	 * Reverse Operation of toFormattedString
	 * @param data
	 * @return
	 */
	public static Element fromFormattedString(String data) {
		List<String> t = parseValues(data, ':');
		try {
			int n = Integer.parseInt(t.get(0));
			String sym = t.get(1);
			String name = t.get(2);
			double weight = Double.parseDouble(t.get(3));
			int group = Integer.parseInt(t.get(4));
			int period = Integer.parseInt(t.get(5));
			boolean metal = Boolean.parseBoolean(t.get(6));
			int state = Integer.parseInt(t.get(7));
			int massnum = Integer.parseInt(t.get(8));

			if (t.size() > 9) {
				int pro = Integer.parseInt(t.get(9));
				int neu = Integer.parseInt(t.get(10));
				int ele = Integer.parseInt(t.get(11));

				Element xx = new Element(pro,sym,name,weight,group,period,metal,state,massnum);
				SElement xxx = new SElement(xx,neu,ele);

				return xxx;
			} else {
				Element xx = new Element(n,sym,name,weight,group,period,metal,state,massnum);
				return xx;
			}

			//xx = new Element(n,sym,name,weight,group,period,metal,state,massnum);
		} catch (Exception e) {
			return PeriodicConstants.getElement().get(0);
		}
		//return xx;
	}
	/**
	 * Reverse operation of fromFormattedString
	 * @param e
	 */
	public static String toFormattedString(Element e) {
		return e.toData();
	}
	public static List<JTextField> toDisplayableData(Element e) {
		List<String> x =  parseValues(e.toData(),':');
		List<JTextField> xx = new ArrayList<JTextField>();
		for (int i = 0 ; i < x.size() ; i++) {
			xx.add(new JTextField(x.get(i)));
		}
		return xx;
	}
	public static List<String> parseValues(String x, char separator) {
		List<String> values = new ArrayList<String>();
		String addto = "";
		for (int i = 0 ; i < x.length() ; i++) {
			if (x.charAt(i) == separator) {
				values.add(addto);
				addto = "";
			} else {
				addto += x.charAt(i);
			}
		}
		if (!addto.equals("")) {
			values.add(addto);
		}
		return values;
	}
	public static String getElementTooltip(Element x) {
		try { 
			URL url = null;
			try {
				url = new URL("https://periodictable.com/GridImages/big/"+x.getNum()+".JPG");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return "Error: Server unresponsive";
			}
			//String more = "\"https://en.wikipedia.org/wiki/" + x.getName();
			String tt = "<html><body><img src=\"" + url + "\"></body></html>";
			System.out.println(tt);
			return tt;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "<html><body><p style=\"color:red\">Error: No internet</p></body></html>";
	}
}
