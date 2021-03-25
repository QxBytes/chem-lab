package com.qxbytes.chemlab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * 
 * @author QxBytes
 *
 */
public class DetailedItem extends JPanel implements IO{
	/**
	 * 
	 */
	private JPanel toolbar = new JPanel(new GridLayout(1,0));
	//removal
	private JPanel parent;
	private List<Element> inpane;
	private List<DetailedItem> di;

	private Element e;
	private JPanel thiscomponent = this;

	private static final long serialVersionUID = 1L;
	private JTextField l;
	private JPanel root;
	private PicturePane pic;
	private JPanel data;

	private JButton delete1;
	private JButton delete;
	private JButton add;
	private JButton export;

	private int count;
	public DetailedItem(JPanel parent, Element e, List<Element> inpane, List<DetailedItem> di) {
		setBackground(Color.WHITE);

		this.setLayout(new BorderLayout());

		count = e.getAbscount();

		root = new JPanel(new GridLayout(1,2));
		pic = new PicturePane(e);
		data = new JPanel(new GridLayout(14,1));
		delete = new JButton("X");
		export = new JButton("▲");
		delete1 = new JButton("-1");
		add = new JButton("+1");

		this.e = e;
		this.parent = parent;
		this.inpane = inpane;
		this.di = di;

		this.setPreferredSize(new Dimension(200,200));

		List<JTextField> lbs = Utils.eString(e);

		for (int i = 0 ; i < lbs.size() ; i++) {
			lbs.get(i).setEditable(false);
			lbs.get(i).setBackground(new Color(255,255,0));
			lbs.get(i).setBorder(BorderFactory.createLineBorder(e.getStateColor()));
			data.add(lbs.get(i));
		}

		root.add(pic);
		root.add(data);

		this.add(root, BorderLayout.CENTER);
		
		delete.setForeground(Color.RED);
		delete.setBackground(Color.RED);
		
		export.setForeground(Color.CYAN);
		export.setBackground(Color.GREEN);
		
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}

		});

		delete1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete1();
			}

		});
		
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				add();
			}
			
		});
		
		export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent q) {
				List<Element> t = new ArrayList<Element>();
				t.add(e);
				Export(t);
			}

		});

		toolbar.add(delete);
		toolbar.add(delete1);
		toolbar.add(add);
		toolbar.add(export);

		this.add(toolbar, BorderLayout.SOUTH);

		l = new JTextField(e.getName() + " x " + count);
		l.setEditable(false);
		this.add(l,BorderLayout.NORTH);
	}
	public void delete() {
		for (int i = 0 ; i < inpane.size() ; i++) {
			if (inpane.get(i).equals(e)) {
				inpane.get(i).setAbscount(1);
			}
		}
		
		inpane.remove(e);
		parent.remove(thiscomponent);
		parent.revalidate();
		parent.repaint();
		di.remove(this);
	}
	public void delete1() {
		if (count == 1) {
			delete();
			return;
		}

		this.setCount(this.getCount()-1);
		for (int i = 0 ; i < inpane.size() ; i++) {
			if (inpane.get(i).equals(e)) {
				inpane.get(i).setAbscount(inpane.get(i).getAbscount()-1);
			}
		}
	}
	public void add() {
		SelectionPane.showDetailedPane(e);
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
		this.l.setText(e.getName() + " x " + count);
	}
	/**
	 * @return the inpane
	 */
	public List<Element> getInpane() {
		return inpane;
	}


	/**
	 * @param inpane the inpane to set
	 */
	public void setInpane(List<Element> inpane) {
		this.inpane = inpane;
	}

	public boolean equals(DetailedItem di) {
		return di.equals(this.toString());
	}
	@Override
	public String toString() {
		return e.toAbsoluteData();
	}
	@Override
	public void Export(List<Element> e) {
		JFrame f = new JFrame("" + e.get(0).getName());
		f.setSize(200, 300);

		JTextArea jta = new JTextArea();
		jta.setText(e.get(0).toAbsoluteData());
		f.add(jta);

		f.setVisible(true);
	}
	@Override
	public List<Element> Import() {
		return null;

	}
}
