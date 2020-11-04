import java.io.IOException;

import com.darkprograms.speech.translator.GoogleTranslate;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/*******************************************************************************************************
 * An API for a Google Translation service in Java. 
 * Please Note: This API is unofficial and is not supported by Google. Subject to breakage at any time.
 * The translator allows for language detection and translation. 
 * Recommended for translation of user interfaces or speech commands.
 * All translation services provided via Google Translate
 * @author GOXR3PLUS
 *******************************************************************************************************/
@SuppressWarnings("serial")
public class TranslateDisplay extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea = new JTextArea();
	private JTextArea textArea_1 = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TranslateDisplay frame = new TranslateDisplay();
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
	public TranslateDisplay() {
		super("Translate Paragraph");
		setBounds(100, 100, 431, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Text area - English paragraph.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 392, 97);
		contentPane.add(scrollPane);
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textArea.getText().equals("")) textArea_1.setText("");
			}
		});
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		
		// Text area - meaning.
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 163, 392, 97);
		contentPane.add(scrollPane_1);
		
		scrollPane_1.setViewportView(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		//scrollPane.setPreferredSize(new Dimension(414, 500));
		textArea_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		
		// Button Translate.
		JButton btnTranslate = new JButton("Translate");
		btnTranslate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					//English to VietNamese
					String translateString = textArea.getText().replace(". ", ".").replace("\n", "");
					textArea_1.setText(GoogleTranslate.translate("vi", translateString));
					textArea.requestFocus();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnTranslate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnTranslate.setBounds(153, 125, 106, 23);
		contentPane.add(btnTranslate);
	}
}
