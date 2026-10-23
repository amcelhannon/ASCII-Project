package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

public class ThresholdPreprocessor implements Preprocessor {
    private static final ArrayList<String> parameterNames = new ArrayList<>() ;
    static {
        parameterNames.add("Luminance Threshold, as Integer [0-255]");
    }

    @Override
    public String getName() {
        return "Threshold Preprocessor" ;
    }

    @Override
    public BufferedImage process(BufferedImage image, Double parameter) {
        assert parameter >= 0 && parameter <= 255;
        return process(image, (int) (double) parameter );
    }

    public BufferedImage process(BufferedImage image, int threshold) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                Color color = new Color(image.getRGB(x, y));
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                int luminance =  (int) (red * 0.2126 + green * 0.7152 + blue * 0.0722);

                if ( luminance < threshold ) {
                    result.setRGB(x, y, Color.black.getRGB());

                }
                else {
                    result.setRGB(x, y, Color.white.getRGB());
                }
            }
        }

        return result;
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