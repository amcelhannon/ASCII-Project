import java.awt.image.BufferedImage;

public class InvertPreprocessor implements Preprocessor {

    @Override
    public String getName() {
        return "Invert";
    }

    @Override
    public BufferedImage process(BufferedImage image) {

        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);

                int red = 255 - ((rgb >> 16) & 0xFF);
                int green = 255 - ((rgb >> 8) & 0xFF);
                int blue = 255 - (rgb & 0xFF);

                int invertedRgb = (red << 16) | (green << 8) | blue;

                result.setRGB(x, y, invertedRgb);
            }
        }

        return result;
    }
}