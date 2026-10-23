package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class NoPreprocessor implements Preprocessor {
    private final ArrayList<String> parameterNames = new ArrayList<>() ;

    @Override
    public String getName() {
        return "No processing";
    }

    @Override
    public BufferedImage process(BufferedImage image, Double parameter) {
        return process(image) ;
    }

    public BufferedImage process(BufferedImage image) {
        return image;
    }

    @Override
    public boolean requiresParameters(){
        return false;
    }

    @Override
    public ArrayList<String> getParameters(){
        return parameterNames;
    }
}