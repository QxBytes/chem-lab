package com.qxbytes.chemlab;

import java.awt.Toolkit;
/**
 * Display Constants
 * @author QxBytes
 * 
 */
public class DC {
	public static int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public static boolean EXTDMODE = true;
	
	public static int NORMWIDTHPT = DC.WIDTH;
	public static int NORMHEIGHTPT = (int)(DC.HEIGHT*.7);
	public static int NORMWIDTHSL = DC.WIDTH;
	public static int NORMHEIGHTSL = 200;
	public static int NORMYSL = (int)(DC.HEIGHT*.7);
	
	public static int EXTDWIDTHPT = (int)(DC.WIDTH)-250;
	public static int EXTDHEIGHTPT = DC.HEIGHT-100;
	public static int EXTDWIDTHSL = 250;
	public static int EXTDHEIGHTSL = DC.HEIGHT-100;
	public static int EXTDXSL = (int)(DC.WIDTH-250);
	
	public static int PW = EXTDWIDTHPT;
	public static int PH = EXTDHEIGHTPT;
	public static int SW = EXTDWIDTHSL;
	public static int SH = EXTDHEIGHTSL;
	public static int SY = 0;
	public static int SX = EXTDXSL;
	
//	public static int PW = NORMWIDTHPT;
//	public static int PH = NORMHEIGHTPT;
//	public static int SW = NORMWIDTHSL;
//	public static int SH = NORMHEIGHTSL;
//	public static int SY = NORMYSL;
//	public static int SX = 0;
	
}
