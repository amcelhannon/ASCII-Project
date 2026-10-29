package GlyphConverter;

import java.util.ArrayList;
import java.util.Comparator;

public class GlyphList {
    private final ArrayList<Glyph> glyphs;
    private final double PATCH_WEIGHT = .95;
    private final double BRIGHTNESS_WEIGHT = 1-PATCH_WEIGHT;

    public GlyphList() {
        this.glyphs = new ArrayList<>();
    }
    public GlyphList(ArrayList<Glyph> glyphs) {this.glyphs = glyphs;}

    public void sort(Comparator<Glyph>  comparator) {glyphs.sort(comparator);}

    public void addGlyph(Glyph glyph) {glyphs.add(glyph);}

    public ArrayList<Glyph> getGlyphs() {return glyphs;}

    public String getGlyphsAsString() {
        StringBuilder result = new StringBuilder();
        for (Glyph glyph : glyphs) {
            result.append(glyph.getSymbol());
        }
        return result.toString();
    }

    public Glyph getClosestBrightnessGlyph(double brightness) {
        assert (brightness >= 0 && brightness <= 1);
        assert !glyphs.isEmpty();

        Glyph bestMatch = glyphs.getFirst();
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

    public Glyph getClosestGlyph(Glyph glyphToMatch) {
        assert (glyphToMatch != null);
        assert !glyphs.isEmpty();

        Glyph bestMatch = glyphs.getFirst();
        double bestScore = getSimilarityScore(glyphToMatch, bestMatch);
        double currentScore;
        for (Glyph glyph : glyphs) {
            currentScore = getSimilarityScore(glyphToMatch, glyph);
            if (currentScore < bestScore) {
                bestMatch = glyph;
                bestScore = currentScore;
            }
        }
        return bestMatch;
    }

    public double getSimilarityScore(Glyph glyph1, Glyph glyph2) {
        assert (glyph1 != null && glyph2 != null);
        assert (glyph1.getResolution() == glyph2.getResolution());

        double brightnessDifference = Math.abs(glyph1.getBrightness()- glyph2.getBrightness());
        double patchDifference = Math.abs(glyph1.bitmapDifference(glyph2));

        return (brightnessDifference * BRIGHTNESS_WEIGHT) + (patchDifference * PATCH_WEIGHT);
    }

    public String toString(){
        String result = "";
        for (Glyph glyph : glyphs){
            result += glyph.toString() + "\n";
        }
        return result;
    }
}
