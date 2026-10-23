package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EdgeenhancedPreprocessor implements Preprocessor {
    private final ArrayList<String> parameterNames = new ArrayList<>() ;
    private final double scalar = 1;
    private final double [] [] kernel = {
            {-1,-1,-1},
            {-1,8,-1},
            {-1,-1,-1},
    };

    @Override
    public BufferedImage process(BufferedImage image, Double parameter) {
        return process(image);
    }

    public BufferedImage process(BufferedImage image) {
        return Preprocessor.applyKernel(image, kernel, scalar) ;
    }

    @Override
    public String getName() {
        return "Edge-enhanced Preprocessor" ;
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
