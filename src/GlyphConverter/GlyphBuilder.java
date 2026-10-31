package GlyphConverter;

import Gradient.Gradient;
import Brightness.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class GlyphBuilder {

    public static Glyph BuildGlyph (int codePoint, int resolution) {
        double[][] bitmap = renderBitmap(codePoint, resolution);
        if (bitmap == null) {
            return null;
        }
        double [][] edgeHistogram = renderEdgeHistogram(codePoint);
        return new Glyph(codePoint, bitmap, edgeHistogram);
    }

    public static GlyphList BuildGlyphList(Gradient gradient, int resolution) {
        GlyphList glyphList = new GlyphList();
        Glyph glyph;
        for (int codePoint : gradient.getCharacterBase().codePoints().toArray()){
            glyph = BuildGlyph(codePoint, resolution);
            if (glyph != null)
                glyphList.addGlyph(GlyphBuilder.BuildGlyph(codePoint, resolution));
            else
                System.out.println("Unable to display codePoint: " + codePoint);
        }
        return glyphList;
    }

    public static double[][] renderBitmap (int codePoint, int resolution) {
        //Generate a canvas
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, resolution);
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, resolution, resolution);

        //
        if (!font.canDisplay(codePoint)){
            return null;
        }

        // Prepare what we want to draw
        g.setColor(Color.BLACK);

        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();

        //Calculate offsets to center character
        String symbol = new String(Character.toChars(codePoint));

        if (!font.canDisplay(codePoint)) {
            System.out.println("HEY: " + symbol);
        }

        int xOffset = (resolution - metrics.stringWidth(symbol)) / 2;
        int yOffset = ((resolution - metrics.getHeight()) / 2) + metrics.getAscent();

        //Draw
        g.drawString(symbol, xOffset, yOffset);
        g.dispose(); // get rid of unnecessary resources


        // to ensure
        saveBitmap(image);

        // Pull out what we want bitmap
        double[][] bitmap = new double[resolution][resolution];

        for (int row = 0; row < resolution; row++) {
            for (int col = 0; col < resolution; col++) {
                //Assuming black and white for now
                int gray = image.getRGB(col, row) & 0xFF; // is 255 if white
                bitmap[row][col] = 1.0 - (gray / 255.0);
                // 1 means white, 0 means black
                }
            }
        return bitmap;
    }

    public static double[][] renderEdgeHistogram (int codePoint) {
        return null;
    }

    public static void saveBitmap (BufferedImage bitmap) {
        //To doublecheck output
        try {
            File outputFile = new File("character_bitmap.bmp");
            boolean success = ImageIO.write(bitmap, "bmp", outputFile);
            if (!success) {
                System.err.println("Failed to save the image: No appropriate writer found.");
            }
        } catch (IOException e) {
            System.err.println("Error saving the bitmap file: " + e.getMessage());
        }
    }
}
