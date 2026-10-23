import java.util.Scanner;

import Preprocessors.*;

void main() throws IOException {
  Scanner scan = new Scanner(System.in);

  PreprocessorInterface.preprocessInterface();

//  System.out.println("Choose from the following converters:");
//  System.out.println(Converters.listConverters());
//  System.out.print("Option Choice: ");
//  int choice = scan.nextInt();
//  if (choice < 1 || choice > Converters.getConverterCount()) {
//    System.out.println("Invalid option.");
//    return;
//  }
//
//  System.out.println("\nChoose from the following gradients:");
//  System.out.println(Gradients.listGradients());
//  System.out.print("Gradient choice: ");
//  int grade = scan.nextInt();
//  if (grade < 1 || grade > Gradients.getGradients().size()) {
//    System.out.println("Invalid option.");
//    return;
//  }
//
//  System.out.println("\nChoose from the following brightness calculators:");
//  System.out.println(BrightnessCalculator.listBrightnessOptions());
//  System.out.print("Brightness choice: ");
//  int brightness = scan.nextInt();
//  if (brightness < 1 || brightness > BrightnessCalculator.brightnessOptionsSize()) {
//    System.out.println("Invalid option.");
//    return;
//  }
//
//  System.out.print("\nScale factor (Original to New): ");
//  int scaleFactor = scan.nextInt();
//
//  AsciiConverter.convert("src/subject.jpg",choice, grade, brightness, scaleFactor);

}
