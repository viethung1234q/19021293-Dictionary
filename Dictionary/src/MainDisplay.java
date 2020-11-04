import java.io.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.util.Map;
import java.util.Scanner;

import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class MainDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String chosenString;
	private boolean mouseClickedDone = false;
	
	static DefaultListModel<String> listModel = new DefaultListModel<>();
	static JList<String> list = new JList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay frame = new MainDisplay();
					frame.setLocationRelativeTo(null);
					frame.addWindowFocusListener(new WindowAdapter() {
					    public void windowGainedFocus(WindowEvent e) {
					        frame.textField.requestFocusInWindow();
					    }
					});
					UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.BOLD, 13));
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainDisplay() {
		super("Dictionary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 580);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(23, 35, 51));
		panel.setBounds(0, 0, 35, 540);
		contentPane.add(panel);
		panel.setLayout(null);
		
		/**
		 *  Display meaning of words.
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(221, 60, 582, 480);
		contentPane.add(scrollPane_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setForeground(new Color(0, 0, 0));
		textPane.setBackground(new Color(255, 255, 255));
		textPane.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		textPane.setBorder(null);
		textPane.setContentType("text/html");
		textPane.setText(Dictionary.getWords().get("abase"));
		
		DefaultCaret caret = (DefaultCaret)textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		scrollPane_1.setViewportView(textPane);
		
		/**
		 *  Display list of words.
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(35, 60, 185, 480);
		contentPane.add(scrollPane);
		
		try {
			File f = new File("EnglishTranslateOriginal.txt");
			Scanner sc = new Scanner(new BufferedReader(new FileReader(f)));
			String word = "";
			while (sc.hasNextLine()) {
				word = sc.next();
				listModel.addElement(word);
				word = sc.nextLine();
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(84, 127, 206));
		scrollPane.setViewportView(panel_2);
		panel_2.setLayout(null);
		
		list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		list.setForeground(new Color(255, 255, 255));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1) {
					mouseClickedDone = true;
					textField.requestFocus();
					chosenString = list.getSelectedValue();
					String meaningString = Dictionary.getWords().get(chosenString);
					textPane.setText(meaningString);
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(new Color(84, 127, 206));
		list.setBounds(0, 0, 184, 450);
		scrollPane.setViewportView(list);
		list.setModel(listModel);
		
		/**
		 *  Button Save.
		 */
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dictionary.saveToFile();
				JOptionPane.showMessageDialog(
						MainDisplay.this,
						"Save Dictionary successfully !",
						"Save",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSave.setToolTipText("Save Dictionary");
		btnSave.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_download_32px.png")));
		btnSave.setBorder(null);
		btnSave.setBackground(new Color(23, 35, 51));
		btnSave.setBounds(0, 21, 32, 32);
		panel.add(btnSave);
		
		/**
		 *  Button Load.
		 */
		JButton btnLoad = new JButton("");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dictionary.loadFromFile();
				listModel.clear();
				try {
					File f = new File("EnglishTranslate.txt");
					Scanner sc = new Scanner(new BufferedReader(new FileReader(f)));
					String word = "";
					while (sc.hasNextLine()) {
						word = sc.next();
						listModel.addElement(word);
						word = sc.nextLine();
					}
					
					sc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.setModel(listModel);
				JOptionPane.showMessageDialog(
						MainDisplay.this,
						"Upload Dictionary from last save successfully !",
						"Load",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnLoad.setToolTipText("Upload Dictionary from last save");
		btnLoad.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_upload_32px.png")));
		btnLoad.setBorder(null);
		btnLoad.setBackground(new Color(23, 35, 51));
		btnLoad.setBounds(0, 86, 32, 32);
		panel.add(btnLoad);
		
		/**
		 * In case you want to load words from start/original file.
		 * LoadSourceFile button.
		 */
		JButton btnLoadSource = new JButton("");
		btnLoadSource.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_upload_document_32px.png")));
		btnLoadSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				Dictionary.getWords().clear();
				try {
					File f = new File("EnglishTranslateOriginal.txt");
					Scanner scanner = new Scanner(new BufferedReader(new FileReader(f)));
					while (scanner.hasNextLine()) {
						String englishWord = scanner.next();
						String meaning = scanner.nextLine().substring(1);
						
						listModel.addElement(englishWord);
						Dictionary.getWords().put(englishWord, meaning);
					}
					scanner.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				list.setModel(listModel);
				textPane.setText(Dictionary.getWords().get("abase"));
				JOptionPane.showMessageDialog(
						MainDisplay.this,
						"Load words successfully !",
						"Load",
						JOptionPane.INFORMATION_MESSAGE);
			}	
		});
		btnLoadSource.setToolTipText("Upload words from the Original Dictionary");
		btnLoadSource.setBorder(null);
		btnLoadSource.setBackground(new Color(23, 35, 51));
		btnLoadSource.setBounds(0, 151, 32, 32);
		panel.add(btnLoadSource);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(71, 120, 197));
		panel_1.setBounds(35, 0, 952, 60);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		/**
		 *  Search box.
		 */
		textField = new JTextField();
		textField.setBorder(new EmptyBorder(1, 1, 1, 1));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				listModel.clear();
				for (Map.Entry<String, String> entry : Dictionary.getWords().entrySet()) {
					if (entry.getKey().startsWith(textField.getText())) {
						listModel.addElement(entry.getKey());
					}
				}
				list.setModel(listModel);
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Dictionary.getWords().get(textField.getText()) == Dictionary.getWords().get("!@$#!dqd@")) {
					JOptionPane.showMessageDialog(
							MainDisplay.this,
							" Word not found in Dictionary !",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					textField.requestFocus();
				}
					
				else {
					textPane.setText(Dictionary.getWords().get(textField.getText()));
					textField.requestFocus();
				}
			}
		});
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setForeground(Color.BLACK);
		textField.setBackground(new Color(82, 194, 255));
		textField.setBounds(335, 20, 300, 25);
		panel_1.add(textField);
		textField.setColumns(10);
		
		
		/**
		 *  Button Add.
		 */
		JButton btnAdd = new JButton("Add");
		btnAdd.setToolTipText("To add phrasal verb, please use a hyphen (-) between 2 words. ");
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_plus_32px_1.png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddWordDisplay myDisplay = new AddWordDisplay();
				myDisplay.setLocationRelativeTo(null);
				myDisplay.setVisible(true);
			}
		});
		btnAdd.setBackground(new Color(71, 120, 197));
		btnAdd.setBorder(null);
		btnAdd.setBounds(8, 16, 62, 32);
		panel_1.add(btnAdd);

		/**
		 *  Button Delete.
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteWordDisplay myDisplay = new DeleteWordDisplay();
				myDisplay.setLocationRelativeTo(null);
				myDisplay.setVisible(true);
			}
		});
		btnDelete.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_trash_32px_1.png")));
		btnDelete.setBorder(null);
		btnDelete.setBackground(new Color(71, 120, 197));
		btnDelete.setBounds(80, 16, 84, 32);
		panel_1.add(btnDelete);

		/**
		 *  Button Change.
		 */
		JButton btnChange = new JButton("Change");
		btnChange.setForeground(new Color(255, 255, 255));
		btnChange.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangeWordDisplay myDisplay = new ChangeWordDisplay();
				myDisplay.setLocationRelativeTo(null);
				myDisplay.setVisible(true);
			}
		});
		btnChange.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_change_32px_2.png")));
		btnChange.setBorder(null);
		btnChange.setBackground(new Color(71, 120, 197));
		btnChange.setBounds(172, 16, 84, 32);
		panel_1.add(btnChange);

		/**
		 *  Button Search.
		 */
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Dictionary.getWords().get(textField.getText()) == Dictionary.getWords().get("!@$#!dqd@")) {
					JOptionPane.showMessageDialog(
							MainDisplay.this,
							" Word not found in Dictionary !",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					textField.requestFocus();
				}
					
				else {
					textPane.setText(Dictionary.getWords().get(textField.getText()));
					textField.requestFocus();
				}
			}
		});
		btnSearch.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_search_32px.png")));
		btnSearch.setBorder(null);
		btnSearch.setBackground(new Color(71, 120, 197));
		btnSearch.setBounds(655, 16, 32, 32);
		panel_1.add(btnSearch);

		/**
		 *  Button Speak.
		 */
		JButton btnSpeak = new JButton("");
		btnSpeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (mouseClickedDone == true) {
						new Trying_Different_Languages(chosenString);
						mouseClickedDone = false;
					} 
					else if (textField.getText() != "") {
						new Trying_Different_Languages(textField.getText());
					}
					textField.requestFocus();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSpeak.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_voice_32px_1.png")));
		btnSpeak.setBorder(null);
		btnSpeak.setBackground(new Color(71, 120, 197));
		btnSpeak.setBounds(705, 16, 32, 32);
		panel_1.add(btnSpeak);
		
		JButton btnTranslate = new JButton("Translate");
		btnTranslate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TranslateDisplay myTranslateDisplay = new TranslateDisplay();
				myTranslateDisplay.setLocationRelativeTo(null);
				myTranslateDisplay.setVisible(true);
			}
		});
		btnTranslate.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/icons8_vietnam_38px.png")));
		btnTranslate.setForeground(Color.WHITE);
		btnTranslate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnTranslate.setBorder(null);
		btnTranslate.setBackground(new Color(71, 120, 197));
		btnTranslate.setBounds(795, 16, 124, 32);
		panel_1.add(btnTranslate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(805, 60, 179, 480);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainDisplay.class.getResource("/images/Book1.png")));
		lblNewLabel.setBounds(1, 130, 170, 308);
		panel_3.add(lblNewLabel);
		
		JLabel lblDictionary = new JLabel("DICTIONARY");
		lblDictionary.setFont(new Font("Segoe Print", Font.PLAIN, 26));
		lblDictionary.setHorizontalAlignment(SwingConstants.CENTER);
		lblDictionary.setBounds(1, 55, 170, 56);
		panel_3.add(lblDictionary);
		
		JLabel lblSince = new JLabel("since 2020");
		lblSince.setHorizontalAlignment(SwingConstants.CENTER);
		lblSince.setFont(new Font("Segoe Script", Font.PLAIN, 11));
		lblSince.setBounds(2, 450, 179, 20);
		panel_3.add(lblSince);
	}
}
