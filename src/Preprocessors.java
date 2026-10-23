import java.util.ArrayList;

public class Preprocessors {
    private static final ArrayList<Preprocessor> preprocessors = new ArrayList<>();
    static{
        preprocessors.add(new NoPreprocessor());
        preprocessors.add(new InvertPreprocessor());
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

    public static Preprocessor getPreprocessor(int index) {
        return preprocessors.get(index);
    }
}
