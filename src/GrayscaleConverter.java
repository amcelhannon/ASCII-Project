import Brightness.BrightnessCalculator;
import Gradient.Gradient;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

public class GrayscaleConverter implements Converter {
    @Override
    public String getName() {
        return "Grayscaled";
    }

    @Override
    public String getDescription() {
        return "Uses a configurable scale factor and a specified gradient " +
                "to return a greyscale (monochromatic) ASCII representation of an Image";
    }

    @Override
    public void convert(BufferedImage image, FileWriter writer, Gradient gradient, int brightnessType, int scaleFactor) throws IOException {
        try {
            String gradientBase = gradient.toString();
            for (int y = 0; y < image.getHeight(); y += scaleFactor) {
                for (int x = 0; x < image.getWidth(); x += scaleFactor) {

                    int rgb = image.getRGB(x, y);

                    char asciiChar = getAsciiChar(gradientBase, brightnessType, rgb);

                    writer.write(asciiChar);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static char getAsciiChar(String gradient, int brightnessType, int rgb) {
        int brightness = BrightnessCalculator.calculateBrightness(rgb, brightnessType);

        // Simple ASCII mapping
        int index = brightness * (gradient.length() - 1) / 255;
        char asciiChar = gradient.charAt(index);
        return asciiChar;
    }
}
