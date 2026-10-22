import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AsciiConverter {
    private static final LinkedHashMap<String, String> converterOptions = new LinkedHashMap<>();
    static {
        converterOptions.put(
                "grayscaled",
                "Uses a configurable scale factor"

        );

    }

    private static final LinkedHashMap<String, String> gradients = new LinkedHashMap<>();
    static {
        gradients.put(
                "Minimal-2",
                "# "
        );

        gradients.put(
                "Standard-5",
                "#*:. "
        );

        gradients.put(
                "Standard-10",
                "@%#*+=-:. "
        );

        gradients.put(
                "Block-5",
                "█▓▒░ "
        );

        gradients.put(
                "Dense",
                "$@B%8&WM#*oahkbdpqwmZO0QLCJYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
        );
    }



    public static Set<Map.Entry<String, String>> getConverterOptions() {return converterOptions.entrySet();}

    public static String listOptions (){
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (String name : converterOptions.keySet()) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(name)
                    .append("\n");
        }
        return result.toString();
    }
    public static Set<Map.Entry<String, String>> getGradients() {return gradients.entrySet();}

    public static int getGradientCount() {
        return gradients.size();
    }

    public static String listGradients (){
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (String name : gradients.keySet()) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(name)
                    .append("\n");
        }
        return result.toString();
    }

    public static String getGradient(int index) {
        return new ArrayList<>(gradients.values()).get(index);
    }

    public static void convert (String filename, int convertType, int gradientType, int brightnessType, int scaleFactor) {
        try {
            BufferedImage image = ImageIO.read(new File(filename));
            FileWriter writer = new FileWriter("output.txt");

            if  (convertType < 0 || convertType >= converterOptions.size()){
                System.out.println("Invalid type choice");
            }
            else if  (gradientType < 0 || gradientType >= gradients.size()){
                System.out.println("Invalid gradient choice");
            }
            else if (brightnessType < 0 || brightnessType >= BrightnessCalculator.brightnessOptionsSize()){
                System.out.println("Invalid brightness choice");
            }
            else {
                String gradient = getGradient(gradientType);

                switch (convertType){
                    case 0:
                        grayscaled(image, writer, gradient, brightnessType, scaleFactor);
                        break;

                    default:
                        System.out.println("Invalid option. Using grayscaled.");
                        grayscaled(image, writer, gradient, brightnessType, scaleFactor);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    public static void grayscaled (BufferedImage image,  FileWriter writer, String gradient, int brightnessType, int scaleFactor) throws IOException {
        try{
            for (int y = 0; y < image.getHeight(); y+= scaleFactor) {
                for (int x = 0; x < image.getWidth(); x+= scaleFactor) {

                    int rgb = image.getRGB(x, y);

                    char asciiChar = getAsciiChar(gradient, brightnessType, rgb);

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

    private static char getAsciiChar(String gradient, int brightnessType, int rgb) {
        int brightness = BrightnessCalculator.calculateBrightness(rgb, brightnessType);

        // Simple ASCII mapping
        int index = brightness * (gradient.length() - 1) / 255;
        char asciiChar = gradient.charAt(index);
        return asciiChar;
    }

}
