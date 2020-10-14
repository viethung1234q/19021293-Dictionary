import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DeleteWordDisplay extends JFrame {

	private JPanel contentPane;
	private JTextField tfWord;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteWordDisplay frame = new DeleteWordDisplay();
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
	public DeleteWordDisplay() {
		super("Delete Word");
		setBounds(100, 100, 414, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Word:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(20, 11, 79, 23);
		contentPane.add(lblNewLabel);
		
		tfWord = new JTextField();
		tfWord.setBounds(120, 11, 253, 23);
		contentPane.add(tfWord);
		tfWord.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfWord.getText().length() > 0) {
					boolean done = Dictionary.deleteWord(tfWord.getText());
					if (!done) {
						JOptionPane.showMessageDialog(
								DeleteWordDisplay.this,
								"Word Not Found. Please try again!",
								"Delete Word",
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(
							DeleteWordDisplay.this,
							"Delete Word Successfully!",
							"Delete Word",
							JOptionPane.INFORMATION_MESSAGE);
						tfWord.setText("");
					}
				}
				else JOptionPane.showMessageDialog(
						DeleteWordDisplay.this,
						"Please Enter Word",
						"Delete Word",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDelete.setToolTipText("Click to Delete");
		btnDelete.setBounds(120, 45, 96, 23);
		contentPane.add(btnDelete);
		
		
	}
}
