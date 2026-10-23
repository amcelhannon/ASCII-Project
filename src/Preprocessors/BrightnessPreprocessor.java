package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;

public class BrightnessPreprocessor implements Preprocessor {
    private static final ArrayList<String> parameterNames = new ArrayList<>() ;
    static {
        parameterNames.add("Brightness, as Double [0-255]");
    }

    @Override
    public String getName() {
        return "Brightness Preprocessor" ;
    }

    @Override
    public BufferedImage process(BufferedImage image, Double parameter) {
        int inputBrightness = (int) (double) parameter;
        assert inputBrightness >= 0 && inputBrightness <= 255;
        return process(image, inputBrightness);
    }

    public BufferedImage process(BufferedImage image, int brightness) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                red =  Preprocessor.clampRGB(red + brightness);
                green = Preprocessor.clampRGB(green + brightness);
                blue = Preprocessor.clampRGB(blue+brightness);

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