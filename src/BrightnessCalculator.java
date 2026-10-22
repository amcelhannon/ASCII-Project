import java.util.ArrayList;
public final class BrightnessCalculator {
    private static final ArrayList<String> brightnessOptions = new ArrayList<>();

    static {
        brightnessOptions.add("Average");
        brightnessOptions.add("Perceptual");
        brightnessOptions.add("Red Channel");
        brightnessOptions.add("Green Channel");
        brightnessOptions.add("Blue Channel");
    }

    public static int calculateBrightness( int rgb, int brightnessOption) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;

        //      float[] hsbvals = new float[3];
        //      java.awt.Color.RGBtoHSB(red, green, blue, hsbvals);

        return switch (brightnessOption) {
            case 0 -> // RGB average
                    (red + green + blue) / 3;
            case 1 -> // Perceptual
                    (int) (0.299 * red + 0.587 * green + 0.114 * blue);
            case 2 -> // R
                    red;
            case 3 -> // G
                    green;
            case 4 -> // B
                    blue;
            default -> (red + green + blue) / 3;
        };
    }

    public static String listBrightnessOptions() {
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (String name : brightnessOptions) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(name)
                    .append("\n");
        }
        return result.toString();}

    public static int brightnessOptionsSize() {
        return brightnessOptions.size();
    }
}