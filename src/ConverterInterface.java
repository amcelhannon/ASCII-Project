import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConverterInterface {
    private static final Scanner scan = new Scanner(System.in);

    public static void converterInterface() throws IOException {
        // Command Line Interface to access Image Inverters
        // Displays selection
        // Interprets User Choice
        // Asks for necessary follow-up information
        // Transforms image
        // Currently saves as output.txt.

        int converterChoice = 0;
        while (converterChoice != -1) {

            System.out.println("Choose from the following converters:");
            System.out.println(Converters.listConverters());
            System.out.print("Option Choice: ");
            converterChoice = scan.nextInt();
            scan.nextLine();

            if (converterChoice == -1) {
                continue;
            }
            else if (converterChoice < 1 || converterChoice > Converters.getConverterCount()) {
                System.out.println("Invalid option.");
            }
            else{
                System.out.println("\nChoose from the following gradients:");
                System.out.println(Gradients.listGradients());
                System.out.print("Gradient choice: ");
                int gradientChoice = scan.nextInt();
                scan.nextLine();
                if (gradientChoice < 1 || gradientChoice > Gradients.getGradients().size()) {
                    System.out.println("Invalid option.");
                    return;
                }

                System.out.println("\nChoose from the following brightness calculators:");
                System.out.println(BrightnessCalculator.listBrightnessOptions());
                System.out.print("Brightness choice: ");
                int brightness = scan.nextInt();
                scan.nextLine();
                if (brightness < 1 || brightness > BrightnessCalculator.brightnessOptionsSize()) {
                    System.out.println("Invalid option.");
                    return;
                }

                System.out.print("\nScale factor (Original to New): ");
                int scaleFactor = scan.nextInt();
                scan.nextLine();

                String fileName = "src/subject.jpg";
                String outputName = "output.txt";

                BufferedImage OriginalImage = ImageIO.read(new File(fileName));
                FileWriter writer = new FileWriter(outputName);
                String gradient = Gradients.getNthGradient(gradientChoice);
                Converter converter = Converters.getNthConverter(converterChoice);

                converter.convert(OriginalImage, writer, gradient, brightness, scaleFactor);
                System.out.println("Art saved to " + outputName + "\n\n");
            }
        }
    }
}