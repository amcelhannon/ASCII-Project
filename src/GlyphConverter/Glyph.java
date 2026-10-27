package GlyphConverter;

public class Glyph {
    private char symbol;
    private double brightness;

    private double[][] bitmap;
    private double[][] edgeHistogram;
    public Glyph(char symbol, double[][] bitmap, double[][] edgeHistogram) {
        this.symbol = symbol;
        this.bitmap = bitmap;
        this.brightness = getAverageBitmapDensity();
        this.edgeHistogram = edgeHistogram;
    }

    public char getSymbol() {
        return symbol;
    }
    public double getBrightness() {
        return brightness;
    }

    public String bitMaptoString(){
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < bitmap.length; row++) {
            for (int col = 0; col < bitmap.length; col++) {
                sb.append(bitmap[row][col]);
            }
        }
        return sb.toString();
    }

    public static String bitMaptoString(double[][] bitmap) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < bitmap.length; row++) {
            for (int col = 0; col < bitmap.length; col++) {
                sb.append(bitmap[row][col]);
            }
        }
        return sb.toString();
    }

    public double getAverageBitmapDensity(){
        double sum = 0;
        // Assumes 1 is white, 0 is dark
        for (int row = 0; row < bitmap.length; row++) {
            for (int col = 0; col < bitmap.length; col++) {
                sum += bitmap[row][col];
            }
        }
        return sum/(bitmap.length * bitmap.length);
    }

    @Override
    public String toString() {
        return "'" + String.valueOf(symbol) + "' -> " + String.valueOf(brightness) ;
    }

    public static double bitmapDifference(double[][] bitmap1, double[][] bitmap2) {
        if (bitmap1 == null || bitmap2 == null) {
            throw new IllegalArgumentException("bitmap1 or bitmap2 is null");
        }
        else if (bitmap1.length != bitmap2.length || bitmap1[0].length != bitmap2[0].length) {
            throw new IllegalArgumentException("Bitmap dimensions must match.");
        }

        double difference = 0;

        for (int row = 0; row < bitmap1.length; row++) {
            for (int col = 0; col < bitmap1[row].length; col++) {
                difference += Math.abs( bitmap1[row][col] - bitmap2[row][col]);
            }
        }

        return difference/( bitmap1.length * bitmap1[0].length);
    }

    public double bitmapDifference(double[][] bitmap) {
        return Glyph.bitmapDifference(this.bitmap, bitmap);
    }

    //TODO: delete
    public static double getAverageBitmapBrightness(double[][] bitmap) {

            double sum = 0;
            for (int row = 0; row < bitmap.length; row++) {
                for (int col = 0; col < bitmap.length; col++) {
                    sum += bitmap[row][col];
                }
            }
            return sum/(bitmap.length * bitmap.length);
    }


}
