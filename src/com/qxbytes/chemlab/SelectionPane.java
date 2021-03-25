package com.qxbytes.chemlab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
/**
 * 
 * @author QxBytes
 *
 */
public class SelectionPane {
	private static JFrame frame;

	private static JPanel root;
	private static JScrollPane sroot;

	private static JMenuBar jmb;

	private static JMenu menu;
	private static JMenuItem in;
	private static JMenuItem out;
	private static JMenuItem export;

	private static JMenu select;
	private static JMenuItem all;

	private static JTextField parser;

	private static JMenuItem removeAll;

	private static List<Element> inpane = new ArrayList<Element>();
	private static List<DetailedItem> di = new ArrayList<DetailedItem>();
	public static void showDetailedPane(Element e) {
		if (frame == null || frame.isVisible() == false) {
			System.out.println("Initialized");
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.setSize(DC.SW,DC.SH);
			frame.setLocationRelativeTo(null);
			frame.setLocation(DC.SX,DC.SY);
			frame.setResizable(true);

			inpane.clear();
			di.clear();

			inpane.removeAll(inpane);
			di.removeAll(di);

			inpane = new ArrayList<Element>();
			di = new ArrayList<DetailedItem>();

			jmb = new JMenuBar();
			menu = new JMenu("File");
			in = new JMenuItem("Open");
			out = new JMenuItem("Save");
			export = new JMenuItem("Export to Spreadsheet");
			select = new JMenu("Select");
			all = new JMenuItem("All Elements");

			parser = new JTextField();

			removeAll = new JMenuItem("Delete Selection");
			removeAll.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int times = di.size();
					for (int i = 0 ; i < times ; i++) {
						di.get(0).delete();
					}
					frame.dispose();
				}

			});

			JFileChooser jfc = new JFileChooser();

			in.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					int x = jfc.showOpenDialog(frame);
					if (x == JFileChooser.APPROVE_OPTION) {
						List<Element> from = Utils.fromSaveData(jfc.getSelectedFile());
						removeAll.doClick();
						for (int i = 0 ; i < from.size() ; i++) {
							SelectionPane.showDetailedPane(from.get(i));
						}
					}
				}

			});
			out.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int x = jfc.showSaveDialog(frame);
					if (x == JFileChooser.APPROVE_OPTION) {
						Utils.toSaveData(inpane, jfc.getSelectedFile());
						System.out.println("Successfully Exported");
					}
				}

			});
			export.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						ExcelHandler.exportToExcel(inpane);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
			all.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					for (int i = 1 ; i < PeriodicConstants.getElement().size() ; i++) {
						SelectionPane.showDetailedPane(new Element(PeriodicConstants.getElement().get(i)));
					}
				}

			});
			parser.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						int x = Integer.parseInt(parser.getText().substring(0, 1));
						String symbol = parser.getText().substring(1);
						Element add = PeriodicConstants.getElement(symbol);
						if (add == null) {
							for (int i = 0 ; i < inpane.size() ; i++) {
								if (inpane.get(i).getSymbol().equals(symbol)) {
									add = inpane.get(i);
								}
							}
						}
						for (int i = 0 ; i < x ; i++) {
							SelectionPane.showDetailedPane(add);
						}
						parser.setText("");
					} catch (Exception e) {
						parser.setText("Syntax Error(Max 9)");
					}
				}

			});
			if (DC.EXTDMODE) {
				root = new JPanel(new GridLayout(0,1));
			} else {
				root = new JPanel(new GridLayout(1,0));
			}
			
			sroot = new JScrollPane();

			frame.add(sroot,BorderLayout.CENTER);

			//frame.add(root, BorderLayout.CENTER);

			frame.add(parser, BorderLayout.SOUTH);

			frame.add(removeAll, BorderLayout.NORTH);

			menu.add(out);
			menu.add(in);
			menu.add(export);

			select.add(all);
			select.add(removeAll);

			jmb.add(menu);
			jmb.add(select);

			frame.setJMenuBar(jmb);
			
			try {
				frame.setIconImage(ImageIO.read(PeriodicTable.class.getResourceAsStream("icon.png")));
			} catch (Exception qq) {
				
			}
			
			frame.repaint();

			root.setBackground(Color.WHITE);

			sroot.setViewportView(root);
		}



		if (!SortElement.addElement(inpane, e)) {
			//did not find duplicate
			di.add(new DetailedItem(root,e,inpane,di));
			root.add(di.get(di.size()-1));
			frame.setVisible(true);
			if (!DC.EXTDMODE) {
				sroot.getHorizontalScrollBar().setValue(di.size()*200);
			} else {
				sroot.getVerticalScrollBar().setValue(di.size()*200);
			}
			root.revalidate();
			root.repaint();
		} else {
			//found duplicate=OK
			for (int i = 0 ; i < di.size() ; i++) {
				//loop=OK
				if (di.get(i).toString().equals(new DetailedItem(root,e,inpane,di).toString())) {
					di.get(i).setCount(di.get(i).getCount()+1);
					if (!DC.EXTDMODE) {
						sroot.getHorizontalScrollBar().setValue(200*i);
					} else {
						sroot.getVerticalScrollBar().setValue(200*i);
					}
				}
			}
			root.revalidate();
			root.repaint();
		}

		frame.setTitle("Selected: " + SortElement.writeFormula(inpane));
		frame.setVisible(true);
	}
	public static void replaceContents(List<Element> e	) {
		removeAll.doClick();
		List<Element> x = Utils.compress(e);
		for (int i = 0 ; i < x.size() ; i++) {
			SelectionPane.showDetailedPane(x.get(i));
		}
	}

	/**
	 * @return the inpane
	 */
	public static List<Element> getInpane() {
		return inpane;
	}

	/**
	 * @param inpane the inpane to set
	 */
	public static void setInpane(List<Element> inpane) {
		SelectionPane.inpane = inpane;
	}
	/**
	 * @return the frame
	 */
	public static JFrame getFrame() {
		return frame;
	}
	

}
