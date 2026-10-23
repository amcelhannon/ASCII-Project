import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

public interface Preprocessor {

    BufferedImage process(
            BufferedImage image
    );

    String getName();
}