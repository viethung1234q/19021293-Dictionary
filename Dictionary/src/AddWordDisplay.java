import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class AddWordDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField tfWord;
	private JTextArea taMeaning = new JTextArea();
	private JLabel lblNewLabel_1;
	
	public static void main(String[] args) {
        AddWordDisplay myDisplay = new AddWordDisplay();
        myDisplay.setVisible(true);
    }

	/**
	 * Create the frame.
	 */
	public AddWordDisplay() {
		super("Add Word");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Word:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 89, 31);
		contentPane.add(lblNewLabel);
		
		tfWord = new JTextField();
		tfWord.setBounds(130, 14, 257, 26);
		contentPane.add(tfWord);
		tfWord.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Enter Meaning:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 53, 102, 31);
		contentPane.add(lblNewLabel_1);
		
		
		
		JButton btnNewButton = new JButton("Add Word");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfWord.getText().charAt(0) == ' ' || taMeaning.getText().charAt(0) == ' ') {
					JOptionPane.showMessageDialog(
							AddWordDisplay.this,
							"Please do not start with blank space!",
							"Add Word",
							JOptionPane.ERROR_MESSAGE);
				}
				else if (tfWord.getText().length() > 0 && taMeaning.getText().length() > 0) {
					String meaning = "<html><i>" + tfWord.getText() + "</i><br/><ul><li><font color='#cc0000'><b>" +
					taMeaning.getText() +"</b></font></li></ul></html>";
					
					Dictionary.addWord(tfWord.getText(), meaning);
					
					tfWord.setText("");
					taMeaning.setText("");
					
					JOptionPane.showMessageDialog(
							AddWordDisplay.this,
							"Add Word successfully", 
							"Add Word",
							JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(
							AddWordDisplay.this,
							"Please Enter Word and Meaning",
							"Add Word",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setToolTipText("Click to Add");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(130, 178, 118, 26);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 56, 257, 103);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(taMeaning);
		
	}

}
