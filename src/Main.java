import java.util.Scanner;

void main() {
  AsciiConverter.convert("src/" + "subject.jpg", 1,50);

  Scanner scan = new Scanner(System.in);

  System.out.println("Choose from the following options:");
  System.out.println(AsciiConverter.listOptions());

  int choice = scan.nextInt();

  if (choice < 1 || choice > AsciiConverter.getOptions().size()) {
    System.out.println("Invalid option.");
    return;
  }

  System.out.print("Scale factor: ");
  int scaleFactor = scan.nextInt();

  AsciiConverter.convert(
          "src/subject.jpg",
          choice - 1,
          scaleFactor
  );
//
//  Scanner scan = new Scanner(System.in);
//  System.out.println("Choose from the following options: ");
//  System.out.println(AsciiConverter.listOptions());
//  int choice = scan.nextInt();
//  String selection;
//  switch (choice) {
//    case 1:
//      selection = "grayscale1_1";
//      break;
//
//    case 2:
//      selection = "grayscaled";
//      break;
//
//    default:
//      System.out.println("Invalid option. Using grayscaled.");
//      selection = "grayscaled";
//  }
//
//
//  AsciiConverter.convert("src/" + "subject.jpg", selection, 1);
}
