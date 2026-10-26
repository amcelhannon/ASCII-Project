package GlyphConverter;

import java.util.ArrayList;

public class GlyphList {
    private final ArrayList<Glyph> glyphs;

    public GlyphList() {
        this.glyphs = new ArrayList<>();
    }
    public GlyphList(ArrayList<Glyph> glyphs) {
        this.glyphs = glyphs;
    }
    public void addGlyph(Glyph glyph) {
        glyphs.add(glyph);
    }
    public ArrayList<Glyph> getGlyphs() {
        return glyphs;
    }
    public String getGlyphsAsString() {
        String result = "";
        for (Glyph glyph : glyphs) {
            result += glyph.getSymbol();
        }
        return result;
    }

    public Glyph getClosestBrightnessGlyph(double brightness) {
        assert (brightness >= 0 && brightness <= 1);
        assert !glyphs.isEmpty();

        Glyph bestMatch = glyphs.get(0);
        double bestDifference = Math.abs(bestMatch.getBrightness() - brightness); ; // Brightnesses should all be <=1, so this is a placeholder

        for (Glyph glyph : glyphs) {
            double currentDifference = Math.abs(glyph.getBrightness() - brightness);
            if ( currentDifference < bestDifference ) {
                bestMatch = glyph;
                bestDifference = currentDifference;
            }
        }
        return bestMatch;
    }

    public Glyph getClosestGlyph(double[][] bitmap) {
        assert (bitmap != null);
        assert !glyphs.isEmpty();

        Glyph bestMatch = glyphs.get(0);
        double bestDifference = bestMatch.bitmapDifference(bitmap);
        for (Glyph glyph : glyphs) {
            double currentDifference = glyph.bitmapDifference(bitmap);
            if ( currentDifference < bestDifference ) {
                bestMatch = glyph;
                bestDifference = currentDifference;
            }
        }
        return bestMatch;
    }

    public String toString(){
        String result = "";
        for (Glyph glyph : glyphs){
            result += glyph.toString() + "\n";
        }
        return result;
    }

}
