import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BWToColorImageConverter {
    public static void main(String[] args) {
        String inputImagePath = "C:\\Users\\saura\\Desktop\\images\\1153091.jpg_key.pngdecrypted.png";
        String outputImagePath = "C:\\Users\\saura\\Desktop\\images\\imagec.png";
        
        try {
            // Read the black and white image
            BufferedImage bwImage = ImageIO.read(new File(inputImagePath));
            
            // Create a new colored image with the same dimensions as the original image
            BufferedImage coloredImage = new BufferedImage(
                    bwImage.getWidth(), bwImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            // Iterate through each pixel of the black and white image
            for (int y = 0; y < bwImage.getHeight(); y++) {
                for (int x = 0; x < bwImage.getWidth(); x++) {
                    // Get the RGB value of the pixel
                    int rgb = bwImage.getRGB(x, y);
                    
                    // Convert the black and white pixel to a color pixel
                    Color color = new Color(rgb, rgb, rgb);
                    
                    // Set the color pixel in the colored image
                    coloredImage.setRGB(x, y, color.getRGB());
                }
            }
            
            // Save the colored image to the specified output path
            ImageIO.write(coloredImage, "jpg", new File(outputImagePath));
            
            System.out.println("Colored image has been created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
