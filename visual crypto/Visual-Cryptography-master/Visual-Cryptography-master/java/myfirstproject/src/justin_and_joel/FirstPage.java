package justin_and_joel;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*; 
import javax.swing.*;

public class FirstPage extends JFrame {

	/**
	 *        
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application .
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage frame = new FirstPage();
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
		public FirstPage() {
			
			// Create main Jpanel
			JPanel heading;
			heading=new JPanel();
			heading.setLayout(null);
			heading.setBackground(Color.BLACK);
			heading.setBounds(450, 180, 600, 500);
			

			JLabel background;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 1920, 1080);
			ImageIcon img = new ImageIcon("C:\\Users\\saura\\Desktop\\college\\java\\backimg.png");
			background=new JLabel(img, JLabel.CENTER);
			background.add(heading);

			background.setBounds(0, 0, 1920, 1080);;
			add(background);
	

			// Create label
			JLabel Title = new JLabel("VISUAL CRYPTOGRAPHY");
			Title.setLayout(null);
			
			Title.setBounds(64, 40, 1378, 128);
			Title.setFont(new Font("Times New Roman", Font.BOLD, 37));
			Title.setForeground(Color.WHITE);
			                          
			heading.add(Title);

			
			
			// Button to access encryption page
			JButton btnEncrypt = new JButton("Encrypt");
			btnEncrypt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					EncryptPage.main(null);
				}
			});
			btnEncrypt.setBounds(180, 250, 250, 35);
			btnEncrypt.setFont(new Font("SanSerif Bold", Font.BOLD, 13));
			btnEncrypt.setBackground(Color.blue );
			btnEncrypt.setForeground(Color.WHITE);

			
			heading.add(btnEncrypt);
			
			// Button to access decryption page
			JButton btnDecrypt = new JButton("Decrypt");
			btnDecrypt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DecryptPage.main(null);
				}
			});
			btnDecrypt.setBounds(180, 350, 250, 35);
			btnDecrypt.setFont(new Font("SanSerif Bold", Font.BOLD, 13));
			btnDecrypt.setBackground(Color.RED);
			btnDecrypt.setForeground(Color.WHITE);


			heading.add(btnDecrypt);
		}
}