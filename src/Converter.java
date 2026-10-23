import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

public interface Converter {

    String getName();

    String getDescription();

    void convert(
            BufferedImage image,
            FileWriter writer,
            String gradient,
            int brightnessType,
            int scaleFactor
    ) throws IOException;
}