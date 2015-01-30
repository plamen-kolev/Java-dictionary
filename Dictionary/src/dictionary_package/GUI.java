package dictionary_package;

//Generated by GuiGenie - Copyright (c) 2004 Mario Awad.
//Home Page http://guigenie.cjb.net - Check often for new versions!

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JPanel {
	private JButton search;
	private JList list_item;
	private JTextField jcomp3;
	private JTextArea description_box;
	private JMenuBar jcomp5;
	private JScrollPane hbar;
	DefaultListModel model;
	private boolean init = false;

	public void changeWordsXls(Dictionary dict, String path){
		model.clear();
		dict.readXls(path);
		for (String string : dict.getKeys()) {
			model.addElement(string);
		}

		if(this.init==false){

			list_item = new JList (model);
			this.init= true;
		} else {
			
		}
	}
	
	public void changeWordsOnlineCsv(Dictionary dict, String url){

		System.out.println("Change words called");
		dict.readCsv(url);

		for (String string : dict.getKeys()) {
			model.addElement(string);
		}

		if(this.init==false){

			list_item = new JList (model);
			this.init= true;
		} else {
			
		}

	}

	public GUI() {

		final Dictionary dict = new Dictionary();
		model = new DefaultListModel();

		this.changeWordsOnlineCsv(dict, System.getProperty("user.dir")+ "/data2.csv");

		JMenu fileMenu = new JMenu ("Файл");
		JMenuItem loadCsvOnline = new JMenuItem ("Зареди CSV файл онлайн");
		JMenuItem loadLocalXsl = new JMenuItem ("Зареди от локален Ексел файл");
		fileMenu.add(loadLocalXsl);
		fileMenu.add (loadCsvOnline);
		JMenuItem exitItem = new JMenuItem ("Изход");
		fileMenu.add (exitItem);
		JMenu helpMenu = new JMenu ("Помощ");
		JMenuItem contentsItem = new JMenuItem ("Съдържание");
		helpMenu.add (contentsItem);
		JMenuItem aboutItem = new JMenuItem ("Инфо");
		helpMenu.add (aboutItem);

		//construct components
		search = new JButton ("Търсене");
		final JPanel listPanel = new JPanel();


		jcomp3 = new JTextField (5);
		description_box = new JTextArea (5, 5);
		description_box.setEditable(false);
		jcomp5 = new JMenuBar();
		jcomp5.add (fileMenu);
		jcomp5.add (helpMenu);

		hbar = new JScrollPane(list_item);
		hbar.setBounds(10,45,140,320);
		//set components properties
		search.setToolTipText ("Напишете думата тук.");
		list_item.setToolTipText ("Кликнете по дума за нейното значение.");

		//adjust size and set layout
		setPreferredSize (new Dimension (667, 373));
		setLayout (null);

		//add components
		add (search);
		// add (list_item);
		add (jcomp3);
		add (description_box);
		add (jcomp5);
		add(hbar);       

		//set component bounds (only needed by Absolute Positioning)
		search.setBounds (570, 10, 85, 25);
		list_item.setBounds (10, 45, 140, 315);
		jcomp3.setBounds (320, 10, 240, 25);
		description_box.setBounds (155, 45, 500, 315);
		jcomp5.setBounds (10, 10, 200, 25);

		// listeners

		list_item.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()){
					JList source = (JList)event.getSource();
					String selected = source.getSelectedValue().toString();
					String definition = dict.getDefinition(selected);
					System.out.println(selected);
					description_box.setText(definition);
				}
			}
		});

		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String searchTerm = jcomp3.getText();

				String definition = dict.getDefinition(searchTerm);
				if(definition == null){ description_box.setText("Думата не бе намерена.");
				} else { 	description_box.setText(definition); 	}
			}
		});
		
		loadCsvOnline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String input = JOptionPane.showInputDialog(this  ,"Enter in some text:");
				changeWordsOnlineCsv(dict, input);
			}
		});

		loadLocalXsl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String input = JOptionPane.showInputDialog(this  ,"Enter in some text:");
				if (input == null){
					return;
				}
				changeWordsXls(dict, input);
			}
		});

	}

	public static void main (String[] args) {
		JFrame frame = new JFrame ("Речник");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add (new GUI());
		frame.pack();
		frame.setVisible (true);

	}
}
