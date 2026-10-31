package Gradient;

import GlyphConverter.Glyph;
import GlyphConverter.GlyphBuilder;
import GlyphConverter.GlyphList;

import java.util.ArrayList;
import java.util.Comparator;

public final class Gradients
{
    private static final ArrayList<Gradient> gradients = new ArrayList<>();
    private static final int defaultResolution = 256;
    static {
        gradients.add(new Gradient(
                "Binary-2",
                "# "));
        gradients.add(new Gradient(
                "Binary Inverse-2",
                " #"  ));
        gradients.add(new Gradient(
                "Standard-3",
                "@*. "  ));
        gradients.add(new Gradient(
                "Standard-5",
                "#*:. "  ));
        gradients.add(new Gradient(
                "Standard-8",
                "#@=*+:. "  ));
        gradients.add(new Gradient(
                "Standard-10",
                "#@%=*+-:. "  ));
        gradients.add(new Gradient(
                "Standard-12",
                "#@%=*+-':.` "  ));
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
                "█▓▊▌▒▎░ "  ));
        gradients.add(new Gradient(
                "Geometric",
                "■□▪▫· "  ));
        gradients.add(new Gradient(
                "Rounded",
                "@Oo:. "  ));
        gradients.add(new Gradient(
                "Sketch",
                "#x=+-. "  ));
        gradients.add(new Gradient(
                "Numeric",
                "4968052173 "  ));
        gradients.add(new Gradient(
                "Dense",
                "M#@WBhkbdmZQYX$awLf%8&pqO0zunCJxt1vrI*j[]+loc/\\{}<>i|()?\"~!-^;,':`._ "  ));
        gradients.add(new Gradient(
                "Dense Clean",
                "#@%=*+-:. "  ));
        gradients.add(new Gradient(
                "Nospaces",
                "M#@WBhkbdmZQYX$awLf%8&pqO0zunCJxt1vrI*j[]+loc/\\{}<>i|()?\"~!-^;,:`_"  ));
        gradients.add(new Gradient(
                "Full ASCII",
                "HMWEN#@BAKFRmXDQbd6$GPZw%9kpq048STUVfhxa&5LOYseg2Cnuy3JzIlotv1ci7r=?+;\":[]j*<>{}()/\\|!',^.-~`_ "
        ));
        gradients.add(new Gradient(
                "Boxes",
                "┼├┤┴┬│└┘┌┐─"));
        gradients.add(new Gradient(
                "Braille",
                "⣿⠿⠟⠏⠇⠃⠁"));
        gradients.add(new Gradient(
                "Braille Full",
                "⣿⢿⡿⠿⣷⣻⣟⣯⣽⣾⢷⡷⢻⡻⢟⡟⢯⢽⡯⡽⢾⡾⠷⠻⠟⠯⠽⠾⣳⣗⣧⣵⣛⣫⣶⣹⣏⣝⣭⣺⣞⣮⣼⢳⡳⢗⡗⢧⢵⡧⢛⡛⢫⢶⢹⡫⢏⡶⡹⢝⡏⡝⢭⢺⡭⡵⡺⢞⡞⢮⢼⡮⡼⠳⠗⠧⠵⠛⠫⠶⠹⠏⠝⠭⠺⠞⠮⠼⣓⣣⣱⣇⣕⣥⣲⣋⣖⣙⣦⣩⣴⣍⣚⣪⣸⣎⣜⣬⢓⡓⢣⢱⡣⢇⡱⢕⡇⡕⢥⢲⡥⢋⡲⢖⢙⡋⡖⡙⢦⢩⢴⡦⡩⢍⡴⢚⡍⡚⢪⡬⢸⡪⢎⡸⢜⡎⡜⢬⠓⠣⠱⠇⠕⠥⠲⠋⠖⠙⠦⠩⠴⠍⠚⠪⠸⠎⠜⠬⣃⣑⣡⣅⣒⣢⣰⣆⣉⣔⣤⣊⣘⣨⣌⢃⢑⡃⡑⢡⡡⢅⢒⡅⡒⢢⢰⡢⢆⢉⡰⢔⡆⡉⡔⢤⡤⢊⢘⡊⡘⢨⡨⢌⡌⠃⠑⠡⠅⠒⠢⠰⠆⠉⠔⠤⠊⠘⠨⠌⣁⣂⣐⣠⣄⣈⡁⢂⢐⡂⢁⡐⢠⡠⢄⡄⢈⡈⠁⠂⠐⠠⠄⠈⣀⢀⡀⠀"
        ));

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

    public static Gradient getNthGradient(int index) {
        return gradients.get(index-1);
    }

    public static Gradient organizeGradient(Gradient gradient, int resolution) {
        // Organizes a gradient, based on brightness, and returns a new Gradient Object
        GlyphList glyphList = GlyphBuilder.BuildGlyphList(gradient, resolution);
        glyphList.sort(Comparator.comparing(Glyph::getBrightness).reversed());
        return new Gradient("Organized " + gradient.getName(), glyphList.getGlyphsAsString());
    }

    public static Gradient MakeGradient(String name, String characterBase, int resolution){
        Gradient gradient = new Gradient(name, characterBase);
        return organizeGradient(gradient, resolution);
    }

    public static void addGradient(Gradient gradient) {
        gradients.add(gradient);
    }

    public static void addGradient(String name, String characterBase) {
        gradients.add(MakeGradient(name, characterBase, defaultResolution) );
    }
}
