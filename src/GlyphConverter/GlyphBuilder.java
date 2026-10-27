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
    public static Glyph BuildGlyph (char symbol, int resolution) {
        double[][] bitmap = renderBitmap(symbol, resolution);
        double [][] edgeHistogram = renderEdgeHistogram(symbol);
        return new Glyph(symbol, bitmap, edgeHistogram);

    }

    public static GlyphList BuildGlyphList(Gradient gradient, int resolution) {
        return BuildGlyphList(gradient.getCharacterBase(), resolution);
    }

    public static GlyphList BuildGlyphList(String gradient, int resolution) {
        GlyphList glyphList = new GlyphList();
        for (char c :  gradient.toCharArray()) {
            Glyph glyph = BuildGlyph(c, resolution);
            glyphList.addGlyph(glyph);
        }
        return glyphList;
    }

    public static double[][] renderBitmap (char symbol, int resolution) {
        //Generate a canvas
        BufferedImage image = new BufferedImage(resolution, resolution, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, resolution, resolution);

        // Prepare what we want to draw
        g.setColor(Color.BLACK);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, resolution);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();

        //Calculate offsets to center character
        int xOffset = (resolution - metrics.charWidth(symbol)) / 2;
        int yOffset = ((resolution - metrics.getHeight()) / 2) + metrics.getAscent();

        //Draw
        g.drawString(String.valueOf(symbol), xOffset, yOffset);
        g.dispose(); // get rid of unnecessary resources


        // to ensure
        saveBitmap(image);

        // Pull out what we want bitmap
        double[][] bitmap = new double[resolution][resolution];

        for (int row = 0; row < resolution; row++) {
            for (int col = 0; col < resolution; col++) {
                //TODO: Assuming black and white for now
                int gray = image.getRGB(col, row) & 0xFF; // is 255 if white
                bitmap[row][col] = 1.0 - (gray / 255.0);
                // 1 means white, 0 means black
                }
            }
        return bitmap;
    }

    public static double[][] renderEdgeHistogram (char symbol) {
        return null;
    }

    public static void saveBitmap (BufferedImage bitmap) {
        //For me to doublecheck things exist
        try {
            File outputFile = new File("character_bitmap.bmp");
            boolean success = ImageIO.write(bitmap, "bmp", outputFile);
            if (success) {
                //System.out.println("Successfully saved character bitmap to: " + outputFile.getAbsolutePath());
            } else {
                System.err.println("Failed to save the image: No appropriate writer found.");
            }
        } catch (IOException e) {
            System.err.println("Error saving the bitmap file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
