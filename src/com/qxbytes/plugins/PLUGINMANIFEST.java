package com.qxbytes.plugins;

import javax.swing.JPanel;
/**
 * 
 * @author QxBytes
 *
 */
public class PLUGINMANIFEST extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Instantiate all plugins here
	 */
	public PLUGINMANIFEST() {
		PButton pair = new PButton("Pair First 2 Elements");
		pair.addFrame(new Pair());
		add(pair);
		
		PButton sandbox = new PButton("Create Element from Scratch");
		sandbox.addFrame(new Sandbox());
		add(sandbox);
		PButton creator = new PButton("Element, Isotope, Ion");
		creator.addFrame(new Creator());
		add(creator);
	}
}
