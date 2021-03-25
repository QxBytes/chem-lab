package com.qxbytes.plugins;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.qxbytes.chemlab.SelectionPane;
import com.qxbytes.chemlab.Element;
import com.qxbytes.chemlab.Utils;
/**
 * 
 * @author QxBytes
 *
 */
public class Plugin {
	private JButton activator;
	private JFrame frame;

	public Plugin(String name, int sx, int sy) {
		frame = new JFrame(name);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		
		activator = new JButton(name);
	}
	public Plugin(String name) {
		frame = new JFrame(name);
		frame.setLocationRelativeTo(null);
		
		activator = new JButton(name);
	}
	public Plugin() {
		frame = new JFrame("Plug-in");
		frame.setLocationRelativeTo(null);
		
		activator = new JButton("Plug-in");
	}
	
	/**
	 * Gets the current selection
	 * @return Compressed selection
	 */
	public static List<Element> getSelected() {
		return SelectionPane.getInpane();
	}
	/**
	 * Gets the first two elements (doubles included) to analyze
	 * @return A pair of elements in a List
	 */
	public static List<Element> getPair() {
		if (Utils.decompress(getSelected()).isEmpty() || Utils.decompress(getSelected()).size() < 2) {
			System.out.println("!");
			return null;
		}
		List<Element> returned = Utils.decompress(getSelected()).subList(0, 2);
		return returned;
	}
	/**
	 * Gets the standard button to trigger the opening of the plugin
	 * @return The button
	 */
	public JButton b() {
		return activator;
	}
	/**
	 * Gets the standard frame for the plugin to run in
	 * @return The frame
	 */
	public JFrame f() {
		return frame;
	}
}
