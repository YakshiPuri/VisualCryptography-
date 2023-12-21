import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class btocl {
  
  public static void main(String[] args) throws IOException {
    String inputImagePath = "C:\\Users\\saura\\Desktop\\images\\spiderman.jpg_key.pngdecrypted.png";
    String outputImagePath = "C:\\Users\\saura\\Desktop\\images";
    BufferedImage inputImage = ImageIO.read(new File(inputImagePath));
    BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    
    // Iterate through each pixel of the input image and set the corresponding pixel in the output image with a random color
    for (int y = 0; y < inputImage.getHeight(); y++) {
      for (int x = 0; x < inputImage.getWidth(); x++) {
        int rgb = inputImage.getRGB(x, y);
        int gray = (int) (0.299 * ((rgb >> 16) & 0xFF) + 0.587 * ((rgb >> 8) & 0xFF) + 0.114 * (rgb & 0xFF));
        Color randomColor = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
        outputImage.setRGB(x, y, gray == 0 ? randomColor.getRGB() : rgb);
      }
    }
    
    // Write the output image to a file
    ImageIO.write(outputImage, "jpg", new File(outputImagePath));
  }
  
}
