package justin_and_joel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;

public class DecryptPage extends JFrame{
    private static final long serialVersionUID = 1L;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptPage frame = new DecryptPage();
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
	public DecryptPage() {
		
		// Create the decrypt page JFrame
		
		
		JLabel contentPane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(69, 12, 1920, 1080);
		ImageIcon img = new ImageIcon("C:\\Users\\saura\\Desktop\\college\\java\\backimg1.png");
		contentPane=new JLabel(img, JLabel.CENTER);
		contentPane.setBounds(0, 0, 1920, 1080);;
		add(contentPane);
		
		// Create decrypt page title
		JLabel Title = new JLabel("Decryption");
		Title.setLayout(null);	
		Title.setBounds(630, 170, 1378, 128);
		Title.setFont(new Font("Times New Roman", Font.BOLD, 60));
		Title.setForeground(Color.WHITE);
			
		contentPane.add(Title);
		
		// Allows user to select first image to decrypt
		JButton btnImage1 = new JButton("Image 1");
		btnImage1.setBounds(650, 340, 250, 35);
		btnImage1.setFont(new Font("SanSerif Bold", Font.BOLD, 13));
		
			
		contentPane.add(btnImage1);
		btnImage1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Allow user to choose path
				Main.image1_path = ImageFunctions.GetPathName();
				
				// Attempt to open file as image
				try{
					Main.image1_file = new File(Main.image1_path);
				} catch (NullPointerException e) {
					System.out.println("An invalid file path was returned");
					return;
				}
				
				// Display image 1
				Main.image1 = ImageFunctions.Display(Main.image1_file, "Image 1");

			}
		});
		
		// Allow user to select second image to decrypt
		JButton btnImage2 = new JButton("Image 2");
		btnImage2.setBounds(650, 410, 250, 35);
		btnImage2.setFont(new Font("SanSerif Bold", Font.BOLD, 13));
		
			
		contentPane.add(btnImage2);
	
		btnImage2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Allow user to choose second file
				Main.image2_path = ImageFunctions.GetPathName();
				
				// Attempt to open file as image
				try{
					Main.image2_file = new File(Main.image2_path);
				} catch (NullPointerException e) {
					System.out.println("An invalid file path was returned");
					return;
				}
				
				// Display image 2
				Main.image2 = ImageFunctions.Display(Main.image2_file, "Image 2");
			}
		});
		
		// Choose a path for output to be saved
		JButton btnSaveImage = new JButton("Key Image");
		btnSaveImage.setBounds(650, 480, 250, 35);
		btnSaveImage.setFont(new Font("SanSerif Bold", Font.BOLD, 13));
	
			
		contentPane.add(btnSaveImage);
		btnSaveImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Allow user to choose path
				Main.image_decrypt_path = ImageFunctions.GetPathName();
				Main.normal_size_decrypted_path = Main.image_decrypt_path;
				
				// Format save string name
				Main.image_decrypt_path += ".png";
				Main.normal_size_decrypted_path += "decrypted.png";
				
				// Attempt to create an output file
				try{
					Main.image_decrypt_file = new File(Main.image_decrypt_path);
					Main.normal_size_decrypted_file = new File(Main.normal_size_decrypted_path);
				} catch (NullPointerException e) {
					System.out.println("An invalid file path was returned");
					return;
				}
				
			}
		});
		
		// Convert encrypted images into decrypted output
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Decrypt and display the encoded images
				Main.decrypt_image = ImageFunctions.Decrypt(Main.image1, Main.image2);
				ImageFunctions.Display_Image(Main.decrypt_image, "Decrypted Image");
				
				

				// Create, display, and save scaled image (same size as original image
				Main.normal_size_decrypted_image = ImageFunctions.Shrink(Main.decrypt_image);
				ImageFunctions.Display_Image(Main.normal_size_decrypted_image, "Regular Sized Decrypted Image");				
				ImageFunctions.Save(Main.normal_size_decrypted_image, Main.normal_size_decrypted_file);
			}
		});
		btnDecrypt.setBounds(650, 550, 250, 35);
		btnDecrypt.setFont(new Font("SanSerif Bold", Font.BOLD, 13));
		btnDecrypt.setBackground(Color.red);
		btnDecrypt.setForeground(Color.WHITE);
			
		contentPane.add(btnDecrypt);
	}
}