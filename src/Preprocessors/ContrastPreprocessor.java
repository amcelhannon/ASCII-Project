package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

public class ContrastPreprocessor implements Preprocessor {
    private static final ArrayList<String> parameterNames = new ArrayList<>() ;
    static {
        parameterNames.add("Contrast factor, as Double [0-255] ([.5-2] is preferred)");
    }

    @Override
    public String getName() {
        return "Contrast Preprocessor" ;
    }

    @Override
    public BufferedImage process(BufferedImage image, Double contrastFactor) {
        assert contrastFactor >= 0 && contrastFactor <= 255;
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                red = Preprocessor.clampRGB((int) (red * contrastFactor));
                green = Preprocessor.clampRGB((int) (green * contrastFactor));
                blue = Preprocessor.clampRGB((int) (blue * contrastFactor));

                result.setRGB(x, y, new Color(red, green, blue).getRGB());
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