import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ascii_Converter {


    public static void convert (String filename, String convertType) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            FileWriter writer = new FileWriter("output.txt");

            if (convertType.equals("grayscale1_1")){
                grayscale1_1(image, writer);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void grayscale1_1 (BufferedImage image,  FileWriter writer ) throws IOException {
        try{
            // Loop through pixels
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {

                    int rgb = image.getRGB(x, y);

                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Convert to grayscale brightness
                    int brightness = (red + green + blue) / 3;

                    // Simple ASCII mapping
                    char asciiChar = brightness < 128 ? '#' : ' ';

                    writer.write(asciiChar);
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("ASCII art saved to output.txt");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }






}
