package Preprocessors;
import java.util.ArrayList;

public class Preprocessors {
    private static final ArrayList<Preprocessor> preprocessors = new ArrayList<>();
    static{
        preprocessors.add(new BoxBlurPreprocessor());
        preprocessors.add(new BrightnessPreprocessor());
        preprocessors.add(new ContrastPreprocessor());
        preprocessors.add(new EdgeenhancedPreprocessor());
        preprocessors.add(new GammaPreprocessor());
        preprocessors.add(new InvertPreprocessor());
        preprocessors.add(new NoPreprocessor());
        preprocessors.add(new SharpeningPreprocessor());
        preprocessors.add(new ThresholdPreprocessor());
    }

    public static ArrayList<Preprocessor> getPreprocessors() {return preprocessors;}

    public static int getPreprocessorCount() {
        return preprocessors.size();
    }

    public static String listPreprocessors (){
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (Preprocessor preprocessor : preprocessors) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(preprocessor.getName())
                    .append("\n");
        }
        return result.toString();
    }

    public static Preprocessor getNthPreprocessor(int index) {
        return preprocessors.get(index-1);
    }
}
