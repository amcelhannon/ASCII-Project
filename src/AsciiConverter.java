import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AsciiConverter {

    public static void convert (String filename, int convertType, int gradientType, int brightnessType, int scaleFactor) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            FileWriter writer = new FileWriter("output.txt");

            if  (convertType < 0 || convertType >= Converters.getConverterCount()){
                System.out.println("Invalid type choice");
            }
            else if  (gradientType < 0 || gradientType >= Gradients.getGradientCount()){
                System.out.println("Invalid gradient choice");
            }
            else if (brightnessType < 0 || brightnessType >= BrightnessCalculator.brightnessOptionsSize()){
                System.out.println("Invalid brightness choice");
            }
            else {
                String gradient = Gradients.getGradient(gradientType);
                Converter converter = Converters.getConverter(convertType);

                converter.convert(image, writer, gradient, brightnessType, scaleFactor);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }





}
