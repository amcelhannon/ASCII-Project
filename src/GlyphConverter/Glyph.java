package GlyphConverter;

public class Glyph {
    private final int codePoint;
    private final double brightness;
    private final int resolution;

    private final double[][] bitmap;
    private final double[][] edgeHistogram;
    public Glyph(int codePoint, double[][] bitmap, double[][] edgeHistogram) {
        this.codePoint = codePoint;
        this.bitmap = bitmap;
        this.resolution = bitmap[0].length;
        this.brightness = getAverageBitmapDensity();
        this.edgeHistogram = edgeHistogram;
    }

    public int getCodePoint() {
        return codePoint;
    }
    public String getSymbol() {
        return new String(Character.toChars(codePoint));
    }
    public double getBrightness() {
        return brightness;
    }
    public int getResolution() {
        return resolution;
    }

    public double[][] getBitmap() {
        return bitmap;
    }

    public static String bitMaptoString(double[][] bitmap) {
        StringBuilder sb = new StringBuilder();
        for (double[] doubles : bitmap) {
            for (int col = 0; col < bitmap.length; col++) {
                sb.append(doubles[col]);
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
        return "'" + getSymbol() + "' -> " + brightness;
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

    public double bitmapDifference(Glyph glyph1){
        return bitmapDifference(this.bitmap, glyph1.getBitmap());
    }

    public double bitmapDifference(double[][] bitmap) {
        return Glyph.bitmapDifference(this.bitmap, bitmap);
    }

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