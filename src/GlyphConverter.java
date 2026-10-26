import Brightness.Brightness;
import Brightness.BrightnessCalculator;
import Gradient.Gradients;
import GlyphConverter.*;

import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

public class GlyphConverter implements Converter {
    @Override
    public String getName() {
        return "GlyphConverter";
    }

    @Override
    public String getDescription() {
        return "Uses a configurable character resolution and a specified gradient " +
                "to return an ASCII representation of an Image";
    }

    @Override
    public void convert(BufferedImage image, FileWriter writer, String gradient, int brightnessType, int scaleFactor) throws IOException {
        System.out.println("Hooray, reached!");
        convertTemp(image, writer, gradient, brightnessType, 256, scaleFactor);

    }

    public void convertTemp(BufferedImage image, FileWriter writer, String gradient, int brightnessType, int resolution, int patchSize) throws IOException {
        try {
            GlyphList glyphList = GlyphBuilder.BuildGlyphList(gradient, patchSize);

            // Note that x and y now correspond to number of times a sampling can occur
            // Ex: y=2 means second y sampling patch
            int counter = 0;
            double [][] imagePatch = null;

            for (int y = 0; y < Math.floor((double) image.getHeight() / patchSize); y++) {
                for (int x = 0; x < Math.floor((double) image.getWidth()     / patchSize); x ++) {

                    // Need to back to pixels instead of patch counts
                    int xCoord = x * patchSize;
                    int yCoord = y * patchSize;

                    imagePatch =  extractPatch(image, xCoord, yCoord, patchSize, brightnessType);
                    System.out.println("Patch is dimension " + imagePatch.length);
                    Glyph bestPatchMatch = glyphList.getClosestGlyph(imagePatch);
                    System.out.println("Best match is" + bestPatchMatch.toString());

                    System.out.println("At section " + counter + " With glyphmatch " + bestPatchMatch.getSymbol());

                    writer.write(bestPatchMatch.getSymbol());
                    counter++;
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static double[][] extractPatch(BufferedImage image, int x, int y, int patchSize, int brightnessType) {
        // Note that patchSize acts as a scaling factor
        double[][] patch = new double[patchSize][patchSize];

        //Start at offsets
        for (int row = x; row < patchSize; row++) {
            for (int col = y; col < patchSize; col++) {

                //Start reading through patch, storing 1:1 pixel to brightness value
                int rgb = image.getRGB(row, col);
                double brightness = (double) BrightnessCalculator.calculateBrightness(rgb, brightnessType)/255;
                // TODO: Assuming Same brightness calculations as Glyph- i.e. 1-
                patch[row][col] = 1.0 - brightness;
            }
        }
        return patch;
    }



}
