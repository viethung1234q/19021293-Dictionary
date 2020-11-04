import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Map;
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
					DeleteWordDisplay myDisplay = new DeleteWordDisplay();
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
	public DeleteWordDisplay() {
		super("Delete Word");
		setBounds(100, 100, 414, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Word:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 11, 96, 23);
		contentPane.add(lblNewLabel);
		
		tfWord = new JTextField();
		tfWord.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		tfWord.setBounds(130, 11, 240, 26);
		contentPane.add(tfWord);
		tfWord.setColumns(10);
		
		JButton btnDelete = new JButton();
		Action buttonAction = new AbstractAction("Delete") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tfWord.getText().length() > 0) {
					boolean done = Dictionary.deleteWord(tfWord.getText());
					if (!done) {
						JOptionPane.showMessageDialog(
							DeleteWordDisplay.this,
							" Word not found in Dictionary !",
							"Delete Word",
							JOptionPane.INFORMATION_MESSAGE);
						tfWord.requestFocus();
					}
					else {
						MainDisplay.listModel.clear();
						for (Map.Entry<String, String> word : Dictionary.getWords().entrySet()) {
							MainDisplay.listModel.addElement(word.getKey());
						}
						MainDisplay.list.setModel(MainDisplay.listModel);
						
						tfWord.setText("");
						tfWord.requestFocus();
						
						JOptionPane.showMessageDialog(
							DeleteWordDisplay.this,
							" Delete Word Successfully !",
							"Delete Word",
							JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else JOptionPane.showMessageDialog(
						DeleteWordDisplay.this,
						"   Please Enter Word",
						"Delete Word",
						JOptionPane.ERROR_MESSAGE);
			}
		};
		btnDelete.setAction(buttonAction);
		btnDelete.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "");
		btnDelete.getActionMap().put("", buttonAction);
		
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDelete.setToolTipText("Click to Delete");
		btnDelete.setBounds(130, 50, 96, 23);
		contentPane.add(btnDelete);
		
		
	}
}
