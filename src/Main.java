import java.awt.image.BufferedImage;
import java.util.Scanner;

void main() {
  Scanner scan = new Scanner(System.in);

  System.out.println("Choose from the following Preprocessors:");
  System.out.println(Preprocessors.listPreprocessors());
  System.out.print("Option Choice: ");
  int processorChoice = scan.nextInt();
  if (processorChoice < 1 || processorChoice > Preprocessors.getPreprocessorCount()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.println("Choose from the following converters:");
  System.out.println(Converters.listConverters());
  System.out.print("Option Choice: ");
  int choice = scan.nextInt();
  if (choice < 1 || choice > Converters.getConverterCount()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.println("\nChoose from the following gradients:");
  System.out.println(Gradients.listGradients());
  System.out.print("Gradient choice: ");
  int grade = scan.nextInt();
  if (grade < 1 || grade > Gradients.getGradients().size()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.println("\nChoose from the following brightness calculators:");
  System.out.println(BrightnessCalculator.listBrightnessOptions());
  System.out.print("Brightness choice: ");
  int brightness = scan.nextInt();
  if (brightness < 1 || brightness > BrightnessCalculator.brightnessOptionsSize()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.print("\nScale factor (Original to New): ");
  int scaleFactor = scan.nextInt();

  AsciiConverter.convert("src/subject.jpg", processorChoice-1, choice-1 , grade-1, brightness-1, scaleFactor);

}
