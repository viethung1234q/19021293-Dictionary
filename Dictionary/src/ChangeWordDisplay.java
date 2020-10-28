import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

@SuppressWarnings("serial")
public class ChangeWordDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField tfWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeWordDisplay myDisplay = new ChangeWordDisplay();
					myDisplay.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeWordDisplay() {
		super("Change word");
		setBounds(100, 100, 413, 255);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWord = new JLabel("Word:");
		lblWord.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblWord.setBounds(10, 11, 97, 31);
		contentPane.add(lblWord);
		
		tfWord = new JTextField();
		tfWord.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tfWord.setBounds(130, 15, 250, 26);
		contentPane.add(tfWord);
		tfWord.setColumns(10);
		
		JLabel lblNewMeaning = new JLabel("New meaning:");
		lblNewMeaning.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewMeaning.setBounds(10, 50, 97, 31);
		contentPane.add(lblNewMeaning);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 54, 250, 111);
		contentPane.add(scrollPane);
		
		JTextArea taMeaning = new JTextArea();
		taMeaning.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scrollPane.setViewportView(taMeaning);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfWord.getText().length() > 0 && taMeaning.getText().length() > 0) {
					if (tfWord.getText().charAt(0) == ' ') {
				        JOptionPane.showMessageDialog(
				        ChangeWordDisplay.this,
				        "Word does not start with blank space !",
				        "Change Word",
				        JOptionPane.ERROR_MESSAGE);
				        tfWord.requestFocus();
				    }
					else {
						String meaning = "<html><i>" + tfWord.getText() + "</i><br/><ul><li><font color='#cc0000'><b>"
				        + taMeaning.getText() +"</b></font></li></ul></html>";

				        if (Dictionary.changeWord(tfWord.getText(), meaning) != null) {
				        	MainDisplay.listModel.clear();
							for (Map.Entry<String, String> word : Dictionary.getWords().entrySet()) {
								MainDisplay.listModel.addElement(word.getKey());
							}
							MainDisplay.list.setModel(MainDisplay.listModel);
				        	
				        	tfWord.setText("");
					        taMeaning.setText("");
					        tfWord.requestFocus();
					        
					        JOptionPane.showMessageDialog(
						        ChangeWordDisplay.this,
						        "Change Word successfully !",
						        "Change Word",
						        JOptionPane.INFORMATION_MESSAGE);
					        
				        }
				        else {
				            JOptionPane.showMessageDialog(
					            ChangeWordDisplay.this,
					            " Word not found in Dictionary !",
					            "Change Word",
					            JOptionPane.ERROR_MESSAGE);
				            tfWord.requestFocus();
				        }
					}
				}
				else {
					JOptionPane.showMessageDialog(
						ChangeWordDisplay.this,
						"Please enter in both Word and Meaning !",
						"Change Word",
						JOptionPane.ERROR_MESSAGE);
					tfWord.requestFocus();
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton.setBounds(130, 177, 110, 28);
		contentPane.add(btnNewButton);
	}
}
