package Brightness;

import java.util.ArrayList;
public final class BrightnessCalculator {
    private static final ArrayList<Brightness> brightnessOptions = new ArrayList<>();

    static {
        brightnessOptions.add(new Brightness("Average",
                        rgb -> {
                            int r = (rgb >> 16) & 0xFF;
                            int g = (rgb >> 8) & 0xFF;
                            int b = rgb & 0xFF;

                            return (r + g + b) / 3;
                        }
                )
        );
        brightnessOptions.add(new Brightness("Perceptual",
                        rgb -> {
                            int red = (rgb >> 16) & 0xFF;
                            int green = (rgb >> 8) & 0xFF;
                            int blue = rgb & 0xFF;
                            return (int) (red * 0.2126 + green * 0.7152 + blue * 0.0722);
                        }
                )
        );
        brightnessOptions.add(new Brightness("Red Channel",
                        rgb -> (rgb >> 16) & 0xFF
                )
        );
        brightnessOptions.add(new Brightness("Green Channel",
                        rgb -> {
                            return (rgb >> 8) & 0xFF;
                        }
                )
        );
        brightnessOptions.add(new Brightness("Blue Channel",
                        rgb -> {
                            return rgb & 0xFF;
                        }
                )
        );


    }

    public static double calculateBrightness( int rgb, int brightnessOption) {
        return brightnessOptions.get(brightnessOption).calculate(rgb);
    }

    public static String listBrightnessOptions() {
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (Brightness brightness : brightnessOptions) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(brightness.getName())
                    .append("\n");
        }
        return result.toString();}

    public static int brightnessOptionsSize() {
        return brightnessOptions.size();
    }
}