import java.util.Scanner;

import GlyphConverter.Glyph;
import GlyphConverter.GlyphBuilder;
import GlyphConverter.GlyphList;
import Gradient.Gradient;
import Gradient.Gradients;
import Preprocessors.*;

void main() throws IOException {
  Scanner scan = new Scanner(System.in);

  //GlyphBuilder.renderBitmap('%',25);
  //GlyphList glyphList = GlyphBuilder.BuildGlyphList(Gradients.getNthGradient(7), 256);
  //System.out.println(glyphList.getGlyphsAsString());
  //Gradients.addGradient("Bitmapped", glyphList.getGlyphsAsString());


  //PreprocessorInterface.preprocessInterface();
  ConverterInterface.converterInterface();

  //GlyphList glyphList = GlyphBuilder.BuildGlyphList("@%#", 10);
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