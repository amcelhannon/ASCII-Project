import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class AsciiConverter {
    private static final ArrayList<String> options = new ArrayList<>();
    static {
        options.add("grayscale1_1");
        options.add("grayscaled");
    }

    public static ArrayList<String> getOptions() {return options;}

    public static String listOptions (){
        String result = "";
        for (int i = 0; i < options.size(); i++){
            result += "\t("+ i + ")" +  options.get(i) + "\n";
        }
        return result;
    }

    public static void convert (String filename, int convertType, int scaleFactor) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            FileWriter writer = new FileWriter("output.txt");

            if  (convertType < 0 || convertType >= options.size()){
                System.out.println("Invalid choice");
            }
            else {
                switch (convertType){
                    case 0:
                        grayscale1_1(image, writer);
                        break;

                    case 1:
                        grayscaled(image, writer, scaleFactor);
                        break;

                    default:
                        System.out.println("Invalid option. Using grayscaled.");
                        grayscaled(image, writer, scaleFactor);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void grayscaled (BufferedImage image,  FileWriter writer, int scaleFactor ) throws IOException {
        try{
            for (int y = 0; y < image.getHeight(); y+= scaleFactor) {
                for (int x = 0; x < image.getWidth(); x+= scaleFactor) {

                    int rgb = image.getRGB(x, y);

                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Convert to grayscale brightness
                    int brightness = (red + green + blue) / 3;

                    // Simple ASCII mapping
                    char asciiChar = brightness < 128 ? '#' : ' ';

                    writer.write(asciiChar);
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("ASCII art saved to output.txt");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void grayscale1_1 (BufferedImage image,  FileWriter writer ) throws IOException {
        grayscaled(image, writer, 1);
    }

}
