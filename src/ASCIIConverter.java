import Gradient.Gradient;
import GlyphConverter.Glyph;

import java.awt.image.BufferedImage;
import java.io.FileWriter;

public class ASCIIConverter implements Converter {
    @Override
    public String getName() {
        return "AsciiConverter";
    }

    @Override
    public String getDescription() {
        return "Uses a configurable scale factor and a specified gradient " +
                "to return a greyscale (monochromatic) ASCII representation of an Image";
    }

    @Override
    public void convert(BufferedImage image, FileWriter writer, Gradient gradient, int brightnessType, int scaleFactor)  {
        try {
            double [][] imagePatch;

            // To prevent OOB, currently clips
            int yCutoff = (image.getHeight() / scaleFactor) * scaleFactor ;
            int xCutoff = (image.getWidth() / scaleFactor) * scaleFactor;

            for (int y = 0; y < yCutoff; y+= scaleFactor) {
                for (int x = 0; x < xCutoff; x+= scaleFactor) {
                    imagePatch =  Converter.extractPatch(image, x, y, scaleFactor, brightnessType);
                    double brightness =  Glyph.getAverageBitmapBrightness(imagePatch);
                    char asciiChar = getAsciiChar(gradient, brightness);
                    writer.write(asciiChar);
                }
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static char getAsciiChar(Gradient gradient, double brightness) {
        // Simple ASCII mapping
        String characterBase = gradient.getCharacterBase();
        int index = (int) Math.round( brightness * (characterBase.length() - 1));
        return characterBase.charAt(index);
    }
}
