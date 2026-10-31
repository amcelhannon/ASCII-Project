import java.util.Scanner;

import Gradient.Gradient;
import Gradient.Gradients;
import Preprocessors.*;

public static void main(String[] args) throws IOException {
  Scanner scan = new Scanner(System.in);
  //PreprocessorInterface.preprocessInterface();

//  System.out.println(characterList(0x2800, 0x28FF));
//  Gradient test =  Gradients.organizeGradient(new Gradient("Braille","⠀⠁⠂⠃⠄⠅⠆⠇⠈⠉⠊⠋⠌⠍⠎⠏⠐⠑⠒⠓⠔⠕⠖⠗⠘⠙⠚⠛⠜⠝⠞⠟⠠⠡⠢⠣⠤⠥⠦⠧⠨⠩⠪⠫⠬⠭⠮⠯⠰⠱⠲⠳⠴⠵⠶⠷⠸⠹⠺⠻⠼⠽⠾⠿⡀⡁⡂⡃⡄⡅⡆⡇⡈⡉⡊⡋⡌⡍⡎⡏⡐⡑⡒⡓⡔⡕⡖⡗⡘⡙⡚⡛⡜⡝⡞⡟⡠⡡⡢⡣⡤⡥⡦⡧⡨⡩⡪⡫⡬⡭⡮⡯⡰⡱⡲⡳⡴⡵⡶⡷⡸⡹⡺⡻⡼⡽⡾⡿⢀⢁⢂⢃⢄⢅⢆⢇⢈⢉⢊⢋⢌⢍⢎⢏⢐⢑⢒⢓⢔⢕⢖⢗⢘⢙⢚⢛⢜⢝⢞⢟⢠⢡⢢⢣⢤⢥⢦⢧⢨⢩⢪⢫⢬⢭⢮⢯⢰⢱⢲⢳⢴⢵⢶⢷⢸⢹⢺⢻⢼⢽⢾⢿⣀⣁⣂⣃⣄⣅⣆⣇⣈⣉⣊⣋⣌⣍⣎⣏⣐⣑⣒⣓⣔⣕⣖⣗⣘⣙⣚⣛⣜⣝⣞⣟⣠⣡⣢⣣⣤⣥⣦⣧⣨⣩⣪⣫⣬⣭⣮⣯⣰⣱⣲⣳⣴⣵⣶⣷⣸⣹⣺⣻⣼⣽⣾⣿"),256 );
//  System.out.println(test.getCharacterBase());

  // Preload Gradient based on Character Bases
  // Gradients.addGradient(Gradients.organizeGradient(new Gradient("Hangul",characterList(0xAC00, 0xD7AF) ), 20));
  // New Class Gradient Loader?

  // Update ConverterInterface to select images

  //Maybe build a new class to strip images out of a video, then convert

  ConverterInterface.converterInterface();
}

public static String characterList(int start, int end) {
  StringBuilder builder = new StringBuilder();
  for (int i = start; i <= end; i++) {
    builder.append(
            new String(Character.toChars(i))
    );
  }
  return builder.toString();
}


public static void checkGradients (){
  for (int i = 1; i<= Gradients.getGradientCount(); i++ ) {
    Gradient gradient = Gradients.getNthGradient(i);
    Gradient test = Gradients.organizeGradient(gradient, 10);

    if (!gradient.toString().equals(test.toString())) {
      System.out.println("Original: " + gradient.getName());
      System.out.println(test.getName() + " " + test.getCharacterBase() + "\n");
    }
  }
}