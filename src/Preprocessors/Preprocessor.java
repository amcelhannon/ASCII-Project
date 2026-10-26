package Preprocessors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Math.clamp;

public interface Preprocessor {
    //ArrayList<> parameters();

    BufferedImage process(BufferedImage image, Double parameter);

    String getName();

    boolean requiresParameters();
    ArrayList<String> getParameters();

    static int clampRGB(int channel){
        return clamp(channel, 0, 255);
    }

    static BufferedImage applyKernel (BufferedImage image, double[][] kernel, double scalar){
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int width = image.getWidth();
        int height = image.getHeight();
        int kernelSize = kernel.length;
        int offset = kernelSize / 2;

        for (int y = offset; y < height - offset; y++) {
            for (int x = offset; x < width - offset; x++) {
                double redSum = 0;
                double greenSum = 0;
                double blueSum = 0;

                for (int ky = 0; ky < kernelSize; ky++) {
                    for (int kx = 0; kx < kernelSize; kx++) {
                        int px = x + kx - offset;
                        int py = y + ky - offset;

                        Color c = new Color(image.getRGB(px, py));
                        double weight = kernel[ky][kx];
                        redSum += c.getRed() * weight;
                        greenSum += c.getGreen() * weight;
                        blueSum += c.getBlue() * weight;
                    }
                }

                redSum *= scalar;
                greenSum *= scalar;
                blueSum *= scalar;

                result.setRGB(x, y, new Color(
                        clampRGB((int) redSum),
                        clampRGB((int) greenSum),
                        clampRGB((int) blueSum)).getRGB());
            }
        }

        return result;
    }
}