import java.awt.image.BufferedImage;

public class NoPreprocessor implements Preprocessor {

    @Override
    public String getName() {
        return "No processing";
    }

    @Override
    public BufferedImage process(BufferedImage image) {
        return image;
    }
}