package Gradient;

import java.util.ArrayList;

public final class Gradients
{
    private static final ArrayList<Gradient> gradients = new ArrayList<>();
    static {
        gradients.add(new Gradient(
                "Binary-2",
                "# "));
        gradients.add(new Gradient(
                "Binary Inverse-2",
                " $ #"  ));
        gradients.add(new Gradient(
                "Standard-3",
                "@*. "  ));
        gradients.add(new Gradient(
                "Standard-5",
                "#*:. "  ));
        gradients.add(new Gradient(
                "Standard-7",
                "@#*+=:. "  ));
        gradients.add(new Gradient(
                "Standard-10",
                "@%#*+=-:. "  ));
        gradients.add(new Gradient(
                "Standard-15",
                "@%#*+=-:.`' "  ));
        gradients.add(new Gradient(
                "Block-5",
                "█▓▒░ " ));
        gradients.add(new Gradient(
                "Block-9",
                "█▉▊▋▌▍▎▏░ " ));
        gradients.add(new Gradient(
                "Block Dense",
                "█▉▊▋▌▍▎▏ "   ));
        gradients.add(new Gradient(
                "Block-Mixed",
                "█▓▊▒▌░▎ "  ));
        gradients.add(new Gradient(
                "Geometric",
                "■□▪▫· "  ));
        gradients.add(new Gradient(
                "Rounded",
                "@Oo:. "  ));
        gradients.add(new Gradient(
                "Sketch",
                "#x+=-. "  ));
        gradients.add(new Gradient(
                "Numeric",
                "9876543210 "  ));
        gradients.add(new Gradient(
                "Dense",
                "$@B%8&WM#*oahkbdpqwmZO0QLCJYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "  ));
        gradients.add(new Gradient(
                "Dense Clean",
                "@%#*+=-:. "  ));
        gradients.add(new Gradient(
                "Nospaces",
                "$@B%8&WM#*oahkbdpqwmZO0QLCJYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`"  ));
    }

    public static ArrayList<Gradient> getGradients() {return gradients;}

    public static int getGradientCount() {
        return gradients.size();
    }

    public static String listGradients (){
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (Gradient gradient : gradients) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(gradient.getName())
                    .append("\n");
        }
        return result.toString();
    }

    public static String getNthGradient(int index) {
        return gradients.get(index-1).getCharacterBase();
    }

    public static void addGradient(String name, String characterBase) {
        gradients.add(new Gradient(name, characterBase));
    }

}
