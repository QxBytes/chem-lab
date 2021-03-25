package com.qxbytes.chemlab;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
/**
 * 
 * @author QxBytes
 *
 */
public class Webbrowser extends JScrollPane {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Webbrowser(URL url) {
			super();
			JEditorPane editorPane = new JEditorPane();
			try {
				editorPane.setPage(url);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.setViewportView(editorPane);
			this.repaint();
		}
}
