package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

public class GammaPreprocessor implements Preprocessor {
    private static final ArrayList<String> parameterNames = new ArrayList<>() ;
    static {
        parameterNames.add("gamma, as Double [0-255] ([.5-2] is preferred)");
    }

    @Override
    public String getName() {
        return "Gamma Preprocessor" ;
    }

    @Override
    public BufferedImage process(BufferedImage image, Double parameter) {
        assert parameter >= 0 && parameter <= 255;
        return process(image, (double) parameter);
    }

    public BufferedImage process(BufferedImage image, double gamma) {
        assert gamma >= 0 && gamma <= 255;
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                Color color = new Color(image.getRGB(x, y));
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                result.setRGB(x, y, new Color(
                                        applyGamma(red, gamma),
                                        applyGamma(green, gamma),
                                        applyGamma(blue, gamma)).getRGB());
            }
        }

        return result;
    }

    private int applyGamma(int value, double gamma) {
        double normalized = value / 255.0;
        double corrected = Math.pow(normalized, gamma);
        int result = (int)(corrected * 255);
        return Preprocessor.clampRGB(result);
    }

    @Override
    public boolean requiresParameters(){
        return true;
    }

    @Override
    public ArrayList<String> getParameters(){
        return parameterNames;
    }
}