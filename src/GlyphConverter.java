import Brightness.BrightnessCalculator;
import GlyphConverter.*;

import java.awt.image.BufferedImage;
import java.io.FileWriter;

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
    public void convert(BufferedImage image, FileWriter writer, String gradient, int brightnessType, int patchSize)  {
        try {
            GlyphList glyphList = GlyphBuilder.BuildGlyphList(gradient, patchSize);
            System.out.println(glyphList);
            int counter = 0;
            double [][] imagePatch;
            Glyph bestPatchMatch;

            // To prevent OOB, currently clips
            int yCutoff = (image.getHeight() / patchSize) * patchSize ;
            int xCutoff = (image.getWidth() / patchSize) * patchSize;

            for (int y = 0; y < yCutoff; y+= patchSize) {
                for (int x = 0; x < xCutoff; x+= patchSize) {
                    imagePatch =  Converter.extractPatch(image, x, y, patchSize, brightnessType);
                    bestPatchMatch = glyphList.getClosestGlyph(imagePatch);
                    writer.write(bestPatchMatch.getSymbol());
                    counter++;
                }
                writer.write("\n");
            }
            System.out.println("Patch count: " + counter);
            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
