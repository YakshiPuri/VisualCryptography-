package justin_and_joel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;  
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class EncryptPage extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int BLACK = -13882323;  // Constant to represent the RGB binary value of black. In binary - 1111111 00000000 00000000 00000000
	static final int WHITE = -6908265;  // Constant to represent the RGB binary value of white. In binary - 1111111 1111111 1111111 1111111
	private boolean imageFlag;  // Flag used to track state of image radio button
	private boolean textFlag;  // Flag used to track state of text radio button
	
	// GUI control declarations
	JRadioButton rdbtnImage;
	JRadioButton rdbtnText;
	JButton btnOriginal;
	JButton btnModified;
	JTextArea textArea;
	JButton btnEncrypt;
	JButton btnClear;

	/**
	 * Launch the application .
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptPage frame = new EncryptPage();
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
	public EncryptPage() {
		
		imageFlag = false;
		textFlag = false;
		
		// Create encryption page Jpanel  
		JLabel contentPane;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(69, 12, 1920, 1080);
		ImageIcon img = new ImageIcon("C:\\Users\\saura\\Desktop\\college\\java\\backimg1.png");
		contentPane=new JLabel(img, JLabel.CENTER);
		contentPane.setBounds(0, 0, 1920, 1080);;
		add(contentPane);


		
		// Title of encryption page
		JLabel lblTitle = new JLabel("Create Encrypted Images");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 37));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(557, 112, 418, 78);
		contentPane.add(lblTitle);
		
		// Label next to Original Image button that displays selected image path
		final JLabel Original_Path_Name = new JLabel("No Path Selected");
		Original_Path_Name.setBounds(747, 295, 291, 30);
		Original_Path_Name.setForeground(Color.WHITE);
		contentPane.add(Original_Path_Name);
		
		// Label next to Save Image button that displays selected save path
		final JLabel Save_Path_Name = new JLabel("No Save Path Selected");
		Save_Path_Name.setBounds(747, 365, 291, 30);
		Save_Path_Name.setForeground(Color.WHITE);
		contentPane.add(Save_Path_Name);
		
		
		// Radio button that indicates input from an image file
		rdbtnImage = new JRadioButton("Image");
		rdbtnImage.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				imageFlag = true;
				textFlag = false;
				
				//System.out.println("imageFlag is: "+ imageFlag);   // Print debug statement
				rdbtnText.setSelected(false);
			}
		});
		rdbtnImage.setBounds(650, 235, 85, 23);
		rdbtnImage.setBackground(Color.BLACK);
		rdbtnImage.setForeground(Color.WHITE);
		contentPane.add(rdbtnImage);
		
		// Radio button that indicates input from an image file
		rdbtnText = new JRadioButton("Text");	
		rdbtnText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				imageFlag = false;
				textFlag = true;
				//System.out.println("imageFlag is: " + imageFlag);  // Print debug statement
				rdbtnImage.setSelected(false);
			}
		});
		rdbtnText.setBounds(791, 235, 79, 23);
		rdbtnText.setBackground(Color.BLACK);
		rdbtnText.setForeground(Color.WHITE);
		contentPane.add(rdbtnText);
		
		// Button to select file to import as the original image
		btnOriginal = new JButton("Original");
		btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(imageFlag == false && textFlag == false){
					System.out.println("Select image radio button");
					return;
				}
				
				else{
					
					// Allows user to choose file name and path for original image
					Main.path = ImageFunctions.GetPathName();
					
					// Handles errors during file selection
					try{
						Main.file = new File(Main.path);
						Main.originalImage = ImageFunctions.Display(Main.file, "Original");
					} catch (NullPointerException e) {
						Original_Path_Name.setText("Error opening image file");
						return;
					}
		
					//Update label to display selected path
					Original_Path_Name.setText(Main.path);
				}
			}
		});
		btnOriginal.setBounds(612, 300, 117, 25);
		contentPane.add(btnOriginal);
		
		// Button to choose save destination for output
		btnModified = new JButton("Modified");
		btnModified.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(imageFlag == false && Main.path == null && rdbtnText.isSelected() == false){
					System.out.println("Select image radio button/select an original image");
					return;
				}
				
				else{
					
					// Allow user to chose a save location and name
					Main.save_path = ImageFunctions.GetPathName();
					
					// Catch errors during path selection
					if (Main.save_path == null) {
						Save_Path_Name.setText("Error selecting save destination");
						return;
					}
					
					// Create a save file for key
					Main.save_key_path = Main.save_path + "_key.png";
					Main.key_file = new File(Main.save_key_path);
					System.out.println("Save key: " + Main.save_key_path);
										
					// Create a save file for cipher
					Main.save_cipher_path = Main.save_path + "_cipher.png";
					Main.cipher_file = new File(Main.save_cipher_path);
					System.out.println("Save cipher: " + Main.save_cipher_path);
					
					//Update save label to display selected path
					Save_Path_Name.setText(Main.save_path + ".png");
									
					
				}
			}
		});
		btnModified.setBounds(612, 370, 117, 25);
		contentPane.add(btnModified);
		
		// Label for text entry
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(612, 445, 70, 15);
		lblMessage.setForeground(Color.WHITE);
		contentPane.add(lblMessage);
		
		// Create text input box
		textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 24));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setLineWrap(true);
		textArea.setBounds(610, 475, 320, 79);
		contentPane.add(textArea);
		
		// Button to process selected images and output encrypted images
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Read in user text input
				String text = textArea.getText();
				
				// If user has not entered text, prompt and return
				if(imageFlag == false && text.equals("")){
					System.out.println("Enter some text");
					return;
				}
				
				// If user has entered text, obtain image of textbox and store as original image
				if(imageFlag == false && textFlag == true){
					
					//System.out.println("converting textbox to image"); // Print debugging statement
					
					// Convert text input box into an image
					BufferedImage text_image = new BufferedImage(textArea.getWidth(), textArea.getHeight(), BufferedImage.TYPE_BYTE_BINARY );
					Graphics2D graphic = text_image.createGraphics();
					textArea.printAll(graphic);
					graphic.dispose();
					
					//ImageFunctions.Display_Image(text_image, "Text converted to image"); // Print debugging statement
					
					Main.originalImage = text_image;
				}
				
				//File names and paths for the magnified images
				Main.save_key_magnified_path = Main.save_path + "share1.png";
				Main.save_cipher_magnified_path = Main.save_path + "share2.png";
				Main.key_magnified_file = new File(Main.save_key_magnified_path);
				Main.cipher_magnified_file = new File(Main.save_cipher_magnified_path);
				
				// Create Black and White image from original image
				BufferedImage black_white = new BufferedImage(
				        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
				        BufferedImage.TYPE_BYTE_BINARY);
				Graphics2D graphics = black_white.createGraphics();
				graphics.drawImage(Main.originalImage, 0, 0, null);

				// Save and display black and white image file
				Main.bw_file = new File(Main.save_path + ".png");
				// ImageFunctions.Save(black_white, Main.bw_file);
				ImageFunctions.Display(Main.bw_file, "Original B/W");
				
				// Create image key
				BufferedImage key_image = new BufferedImage(
				        Main.originalImage.getWidth(), Main.originalImage.getHeight(),
				        BufferedImage.TYPE_BYTE_BINARY);
				
				// Generate a random key
				Random rand = new Random();
				try {
					SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
					
					for(int i = 0; i < key_image.getHeight(); i++){
						for(int j = 0; j < key_image.getWidth(); j++){
							
							int result = secureRandomGenerator.nextInt(100);
							if(result < 70){
								key_image.setRGB(j, i, WHITE);
							}
							else{
								key_image.setRGB(j, i, BLACK);
							}
						}
					}
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				// Save and display key image file					
				ImageFunctions.Display_Image(key_image, "Key");
				ImageFunctions.Save(key_image, Main.key_file);
				
				// Save and display magnified key image file
				BufferedImage magnified_key_image = ImageFunctions.Magnify(key_image);
				ImageFunctions.Save(magnified_key_image, Main.key_magnified_file);
				ImageFunctions.Display_Image(magnified_key_image, "Magnified key");
				
				// Save and display magnified cipher image file
				Main.cipher_image = ImageFunctions.Create_Cipher(black_white, key_image);
				BufferedImage magnified_cipher_image = ImageFunctions.Magnify(Main.cipher_image);
				ImageFunctions.Save(magnified_cipher_image, Main.cipher_magnified_file);
				ImageFunctions.Display_Image(magnified_cipher_image, "Magnified Cipher");
				
			
			}
		});
		btnEncrypt.setBounds(590, 640, 150, 25);
		btnEncrypt.setBackground(Color.BLUE);
		btnEncrypt.setForeground(Color.WHITE);
		contentPane.add(btnEncrypt);
		
		// Reset user input text if clicked
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(805, 640, 150, 25);
		btnClear.setBackground(Color.RED);
		btnClear.setForeground(Color.WHITE);
		contentPane.add(btnClear);
		
	}
}