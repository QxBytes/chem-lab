package com.qxbytes.chemlab;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * @author QxBytes
 *
 */
public class AnalyzePane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AnalyzePane() {
		super();
		setLayout(new GridLayout(1,0));
		add(new JLabel("Sort & Analyze"));
		JButton eng = new JButton("Electronegativity");
		eng.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionPane.getInpane().sort(new electronegativityComparator());
				new SortPane(SelectionPane.getInpane(),"Electronegativity- Least to Greatest");
			}

		});
		add(eng);
		JButton size = new JButton("Atomic Radius");
		size.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectionPane.getInpane().sort(new sizeComparator());
				new SortPane(SelectionPane.getInpane(),"Size- Smallest to Largest");
			}

		});
		add(size);
		JButton alpha = new JButton("Alphabetize");
		alpha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionPane.getInpane().sort(new lexographicComparator());
				new SortPane(SelectionPane.getInpane(),"Alphabetize- a-z");
			}

		});
		add(alpha);
		JButton sort = new JButton("Num. Sort");
		sort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectionPane.getInpane().sort(new numberComparator());
				new SortPane(SelectionPane.getInpane(),"Atom. Num- Low-High");
			}

		});
		add(sort);
		JButton anlz = new JButton("Analyze");
		anlz.setBackground(Color.GREEN);
		anlz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double totalmass = 0;
				int totalatoms = 0;
				for (int i = 0 ; i < SelectionPane.getInpane().size() ;i++) {
					for (int x = 0 ; x < SelectionPane.getInpane().get(i).getAbscount() ; x++) {
						totalmass += SelectionPane.getInpane().get(i).getWeight();
						totalatoms+=1;
					}
				}
				new ConversionPane(totalatoms, totalmass, "Convert");

			}

		});
		add(anlz);
	}
}
