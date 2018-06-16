package aec;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class AEC {

	private JFrame frmAec;
	private JTextField textCode;
	private JTextField textResult;
	private JTextField textKey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AEC window = new AEC();
					window.frmAec.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AEC() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAec = new JFrame();
		frmAec.setIconImage(Toolkit.getDefaultToolkit().getImage(AEC.class.getResource("/aec/logo.jpg")));
		frmAec.setTitle("AEC");
		frmAec.setBounds(100, 100, 450, 300);
		frmAec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAec.getContentPane().setLayout(null);
		
		JLabel lblCode = new JLabel("String/Code");
		lblCode.setBounds(15, 60, 98, 20);
		lblCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmAec.getContentPane().add(lblCode);
		
		textCode = new JTextField();
		textCode.setBounds(117, 57, 248, 26);
		frmAec.getContentPane().add(textCode);
		textCode.setColumns(10);
		
		JComboBox type = new JComboBox();
		type.setBounds(117, 15, 248, 26);
		type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					textResult.setBackground(Color.GRAY);
					textResult.setEnabled(false);
					textResult.setText("");
					textCode.setText("");
					textKey.setText("");
				}
			}
		});
		type.setModel(new DefaultComboBoxModel(new String[] {"Encrypt", "Decrypt"}));
		type.setEditable(true);
		frmAec.getContentPane().add(type);
		
		JLabel lblKey = new JLabel("Key");
		lblKey.setBounds(15, 96, 98, 20);
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmAec.getContentPane().add(lblKey);
		
		textKey = new JTextField();
		textKey.setBounds(117, 98, 248, 22);
		textKey.setBackground(Color.WHITE);
		frmAec.getContentPane().add(textKey);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setBounds(15, 132, 98, 20);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frmAec.getContentPane().add(lblResult);
		
		textResult = new JTextField();
		textResult.setBounds(117, 131, 248, 26);
		textResult.setBackground(Color.GRAY);
		textResult.setEnabled(false);
		frmAec.getContentPane().add(textResult);
		textResult.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(163, 174, 115, 29);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					String code = textCode.getText().toString();
					
					if(!textCode.getText().equals("") && !textKey.getText().equals("") ) {
						
					try {
					int key = Integer.parseInt(textKey.getText());
					if(type.getSelectedItem().toString()=="Encrypt") {
						textResult.setText(encrypt(code, key));
					}else {
						textResult.setText(decrypt(code, key));
					}
					textResult.setEnabled(true);
					textResult.setBackground(Color.WHITE);
					}catch(Exception z) {
						Toolkit.getDefaultToolkit().beep();
						JOptionPane.showMessageDialog(null, "Key must be number only", "Invalid key", 0);
					}
					
				}else {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "String/Code and Key must not be empty", "Empty", 2);
				}
					
				
			}
		});
		frmAec.getContentPane().add(btnSubmit);
		
		JLabel lblAltechticSolutions = new JLabel("Altechtic Solutions");
		lblAltechticSolutions.setForeground(SystemColor.textHighlight);
		lblAltechticSolutions.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAltechticSolutions.setBounds(257, 219, 156, 20);
		frmAec.getContentPane().add(lblAltechticSolutions);
	}
	
	public static String encrypt(String string, int key) {
		int encrypted;
		String coded ="";
		
		for(int i = 0; i < string.length(); i++) {
			
			final int ascii = (int) string.charAt(i);
			
			if( ascii + key > 126) {
				
				 encrypted = 33 + ((ascii + key ) - 127 );
				
			}else {
				 encrypted = ascii + key;
			}
			
			String letter = Character.toString((char) encrypted);
			
			coded += letter;
			
		}
		
		return (coded);
		
	}
	
public static String decrypt(String code, int key) {
		
		int decrypted;
		String string = "";
		
		for(int i = 0; i < code.length(); i++) {
			
		final int ascii = (int) code.charAt(i);
		
		if (ascii + 126 -33 - key < 127) {
			decrypted = ascii + 127 -33 - key;
		}else {
			decrypted = (ascii - key);
		}
		
		String letter = Character.toString((char) decrypted);
		
		string += letter;
		
		}
		
		string = string.replace("~", " ");
		
		return (string);
	}
}
