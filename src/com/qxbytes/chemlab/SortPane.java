package com.qxbytes.chemlab;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * 
 * @author QxBytes
 *
 */
public class SortPane {
	private List<ElementDisplay> e = new ArrayList<ElementDisplay>();
	private List<Element> selected = new ArrayList<Element>();
	private JMenuItem toSelection = new JMenuItem("Export to Selection");
	private JMenuItem export = new JMenuItem("Export to Spreadsheet");
	private JMenu jm = new JMenu("Options");
	private JMenuBar jmb = new JMenuBar();
	private List<Element> param;
	public SortPane(List<Element> e, String title) {
		e = Utils.decompress(e);
		param = e;

		JFrame f = new JFrame(title);
		f.setSize(700, 300);
		f.setLayout(new GridLayout(1,1));
		f.setLocation(0, 0);

		JPanel panel = new JPanel(new GridLayout(1,e.size()));
		panel.setPreferredSize(new Dimension(100*e.size(),300));
		for (int i = 0 ; i < e.size() ; i++) {
			ElementDisplay ed = new ElementDisplay(e.get(i),selected);
			ed.setPreferredSize(new Dimension(100,300));
			this.e.add(ed);
			panel.add(new ElementDisplay(e.get(i),selected));
		}

		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(panel);

		f.add(jsp);

		toSelection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectionPane.replaceContents(param);
			}

		});
		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ExcelHandler.exportToExcel(param);
				} catch (Exception x) {

				}
			}

		});

		jm.add(toSelection);
		jm.add(export);

		jmb.add(jm);

		f.setJMenuBar(jmb);
		
		try {
			f.setIconImage(ImageIO.read(PeriodicTable.class.getResourceAsStream("icon.png")));
		} catch (Exception qq) {
			
		}

		f.setVisible(true);
	}
}
