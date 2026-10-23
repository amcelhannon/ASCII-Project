package Preprocessors;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class PreprocessorInterface {
    // Command Line Interface to apply Image Preprocessing
    // Displays selection
    // Interprets User Choice
    // If applicable, asks for necessary follow-up information
    // Transforms image
    // Saves image as image_name_preprocessed.

    private static final Scanner scan = new Scanner(System.in);

    public static void preprocessInterface() throws IOException {
        System.out.println("Hello");

        int processorChoice = 0;
        while (processorChoice != -1) {
            System.out.println("Choose from the following Preprocessors," +
                    " or enter -1 if no preprocessing is desired:");
            System.out.println(Preprocessors.listPreprocessors());
            System.out.print("Option Choice: ");

            processorChoice = scan.nextInt();
            scan.nextLine();
            if (processorChoice == -1 ){
                continue;
            }
            else if (processorChoice < 1 || processorChoice > Preprocessors.getPreprocessorCount()) {
                System.out.println("Invalid option.");

            }
            else {


                System.out.print("\nEnter the image name to preprocess: ");
                String fileName = scan.nextLine();
                if (fileName.equals("")) {
                    fileName = "subject.jpg"; //default
                }
                String pathInResources = "/" + fileName;
                URL imageUrl = PreprocessorInterface.class.getResource(pathInResources);
                assert imageUrl != null;
                BufferedImage originalImage = ImageIO.read(imageUrl);

                String[] parts = fileName.split("\\.");

                String baseName = String.join(".", Arrays.copyOf(parts, parts.length - 1));
                String extension = parts[parts.length - 1];

                BufferedImage result = handle(originalImage, processorChoice);
                String outputFile = baseName + "_preprocessed." + extension;

                // Save preprocessed image
                ImageIO.write(result, extension, new File(outputFile));

                System.out.println("Saved preprocessed image as: " + outputFile + "\n\n");
            }
        }
    }

    private static BufferedImage handle(BufferedImage image, int processorChoice) throws IOException {
        Preprocessor chosenPreprocessor = Preprocessors.getNthPreprocessor(processorChoice);

        // if parameters are needed, get them
        if (chosenPreprocessor.requiresParameters()) {
            ArrayList<String> parameters = chosenPreprocessor.getParameters();
            System.out.println("Enter parameter value for " + parameters.get(0));
            double value = scan.nextDouble();
            scan.nextLine();

            return Preprocessors.getNthPreprocessor(processorChoice)
                    .process(image, value);
        }
        else  {
            return Preprocessors.getNthPreprocessor(processorChoice)
                    .process(image, null);
        }
    }
}