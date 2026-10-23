package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BoxBlurPreprocessor implements Preprocessor {
    private static final ArrayList<String> parameterNames = new ArrayList<>();
    private final double [] [] kernel = {
            {1,1,1},
            {1,1,1},
            {1,1,1},
    };
    private final double scalar = .25;

    @Override
    public String getName() {
        return "Box Blur Preprocessor" ;
    }

    @Override
    public BufferedImage process(BufferedImage image, Double parameter) {
        return process(image);
    }
    public BufferedImage process(BufferedImage image) {
        return Preprocessor.applyKernel(image, kernel, scalar) ;
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
