import Brightness.BrightnessCalculator;
import Gradient.Gradient;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

public interface Converter {

    String getName();

    String getDescription();

    void convert(
            BufferedImage image,
            FileWriter writer,
            Gradient gradient,
            int brightnessType,
            int scaleFactor
    ) throws IOException;

    static double[][] extractPatch(BufferedImage image, int x, int y, int patchSize, int brightnessType) {
        // Note that patchSize acts as a scaling factor
        double[][] patch = new double[patchSize][patchSize];

        for (int row = 0; row < patchSize; row++) {
            for (int col = 0; col < patchSize; col++) {

                //Start reading through patch, storing 1:1 pixel to brightness value
                int rgb = image.getRGB(row + x, col + y);
                double brightness = (double) BrightnessCalculator.calculateBrightness(rgb, brightnessType)/255;
                // Assuming Same brightness calculations as Glyph- i.e. 1-
                patch[row][col] = 1.0 - brightness;
            }
        }
        return patch;
    }

}