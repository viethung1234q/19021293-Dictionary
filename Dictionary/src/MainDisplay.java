import java.io.*;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.util.Map;
import java.util.Scanner;

import com.sun.speech.freetts.*;

@SuppressWarnings("serial")
public class MainDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static final String VOICENAME = "kevin16";
	private String choosenString;
	//private boolean tfDone = false;
	private boolean mouseClickedDone = false;
	
	DefaultListModel<String> listModel = new DefaultListModel<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainDisplay frame = new MainDisplay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainDisplay() throws IOException {
		super("Dictionary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Display meaning of word.
		JScrollPane scrollPaneText = new JScrollPane();
		scrollPaneText.setBounds(144, 94, 658, 394);
		contentPane.add(scrollPaneText);
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setText(Dictionary.getWords().get("abase"));
		
		DefaultCaret caret = (DefaultCaret)textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		
		scrollPaneText.setViewportView(textPane);
		
		// Display list of words.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 94, 144, 394);
		contentPane.add(scrollPane);
		
		try {
			File f = new File("EnglishTranslate.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String word = sc.next();
				String stringToCutMeaning = sc.nextLine();
				listModel.addElement(word);
			}
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JList<String> list = new JList<>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1) {
					mouseClickedDone = true;
					choosenString = list.getSelectedValue();
					String meaningString = Dictionary.getWords().get(choosenString);
					textPane.setText(meaningString);
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(new Color(204, 255, 255));
		scrollPane.setViewportView(list);
		list.setModel(listModel);
		
		// Display the word "DICTIONARY"
		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 102, 255));
		panel.setBounds(347, 34, 455, 61);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DICTIONARY");
		lblNewLabel.setBounds(0, 0, 455, 61);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(51, 102, 255));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		// Search box
		textField = new JTextField();
		textField.setBounds(0, 66, 144, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		/* if (textField.getText() != "") {
			tfDone = true;
			choosenString = textField.getText();
		} */
		
		// Button Add
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddWordDisplay myDisplay = new AddWordDisplay();
				myDisplay.setVisible(true);
			}
		});
		btnAdd.setBounds(0, 0, 63, 23);
		contentPane.add(btnAdd);
		
		// Button Delete
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteWordDisplay myDisplay = new DeleteWordDisplay();
				myDisplay.setVisible(true);
			}
		});
		btnDelete.setBounds(0, 23, 63, 23);
		contentPane.add(btnDelete);
		
		// Button Save
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dictionary.saveToFile();
				JOptionPane.showMessageDialog(
						MainDisplay.this,
						"Save words from Dictionary to file successfully !",
						"Save",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSave.setBounds(196, 0, 88, 23);
		contentPane.add(btnSave);
		
		// Button Load
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dictionary.loadFromFile();
				listModel.clear();
				try {
					File f = new File("EnglishTranslate.txt");
					Scanner sc = new Scanner(f);
					while (sc.hasNextLine()) {
						String word = sc.next();
						String stringToCutMeaning = sc.nextLine();
						listModel.addElement(word);
					}
					
					sc.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				list.setModel(listModel);
				JOptionPane.showMessageDialog(
						MainDisplay.this,
						"Load words to Dictionary from file successfully !",
						"Load",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnLoad.setBounds(196, 23, 88, 23);
		contentPane.add(btnLoad);
		
		// Button Search
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Dictionary.getWords().get(textField.getText()) == Dictionary.getWords().get("!@$#!@")) {
					JOptionPane.showMessageDialog(
							MainDisplay.this,
							"No such word in Dictionary !",
							"Error",
							JOptionPane.ERROR_MESSAGE);
					//textField.setText("");
				}
					
				else {
					textPane.setText(Dictionary.getWords().get(textField.getText()));
					//textField.setText("");
				}
			}
		});
		btnSearch.setBounds(152, 72, 80, 23);
		contentPane.add(btnSearch);
		
		// Button Speak
		JButton btnSpeak = new JButton("Speak");
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		btnSpeak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Voice voice;
				VoiceManager vManager = VoiceManager.getInstance();
				voice = vManager.getVoice(VOICENAME);
				
				voice.allocate();
				
				try {
					if (mouseClickedDone == true) {
						voice.speak(choosenString);
						mouseClickedDone = false;
					} 
					else voice.speak(textField.getText());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSpeak.setBounds(244, 72, 80, 23);
		contentPane.add(btnSpeak);
		
		/**
		 * In case you want to load words from the first/original file.
		 */
		JButton btnLoadSource = new JButton("Load Source File");
		btnLoadSource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				try {
					File file = new File("EnglishTranslateOriginal.txt");
					Scanner scanner = new Scanner(file);
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
				JOptionPane.showMessageDialog(
						MainDisplay.this,
						"Load words successfully !",
						"Load",
						JOptionPane.INFORMATION_MESSAGE);
			}	
		});
		btnLoadSource.setToolTipText("Load words from the original file");
		btnLoadSource.setBounds(292, 0, 152, 23);
		contentPane.add(btnLoadSource);
		
		// Button Reload
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setToolTipText("Update Dictionary after add or delete word");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listModel.clear();
				for (Map.Entry<String, String> word : Dictionary.getWords().entrySet()) {
					listModel.addElement(word.getKey());
				}
				list.setModel(listModel);
			}
		});
		btnRefresh.setBounds(74, 0, 96, 23);
		contentPane.add(btnRefresh);
		
	}
}
