import java.util.Scanner;

void main() {
  Scanner scan = new Scanner(System.in);

//  System.out.println("Choose from the following options:");
//  System.out.println(AsciiConverter.listOptions());
//  System.out.print("Option Choice: ");
//  int choice = scan.nextInt();
//  if (choice < 1 || choice > AsciiConverter.getConverterOptions().size()) {
//    System.out.println("Invalid option.");
//    return;
//  }

  System.out.println("\nChoose from the following gradients:");
  System.out.println(AsciiConverter.listGradients());
  System.out.print("Gradient choice: ");
  int grade = scan.nextInt();
  if (grade < 1 || grade > AsciiConverter.getGradients().size()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.println("\nChoose from the following brightness calculators:");
  System.out.println(AsciiConverter.listBrightnessOptions());
  System.out.print("Brightness choice: ");
  int brightness = scan.nextInt();
  if (brightness < 1 || brightness > AsciiConverter.listBrightnessOptions().length()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.print("\nScale factor (Original to New): ");
  int scaleFactor = scan.nextInt();

  //AsciiConverter.convert("src/subject.jpg", choice-1 , grade-1, brightness, scaleFactor);
  AsciiConverter.convert("src/subject.jpg", 0 , grade-1, brightness-1, scaleFactor);

}
