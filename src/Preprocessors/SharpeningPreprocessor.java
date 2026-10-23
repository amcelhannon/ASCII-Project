package Preprocessors;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SharpeningPreprocessor implements Preprocessor {
    private final ArrayList<String> parameterNames = new ArrayList<>() ;
    private final double scalar = 1;
    private final double [] [] kernel = {
            {0,-1,0},
            {-1,5,-1},
            {0,-1,0},
    };

    @Override
    public String getName() {
        return "Edge-enhanced Preprocessor" ;
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
