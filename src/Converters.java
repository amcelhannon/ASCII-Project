import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Converters {
    private static final LinkedHashMap<String, Converter> converterList = new LinkedHashMap<>();

    public static void register(
            String name,
            Converter converter) {

        converterList.put(name, converter);
    }

    static {
       register(
               "Grayscale",
                new GrayscaleConverter()
        );
    }

    public static String listConverters() {
        StringBuilder result = new StringBuilder();
        int index = 1;
        for(String name : converterList.keySet()) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(name)
                    .append("\n");
        }
        return result.toString();
    }

    public static Converter getNthConverter(int index) {
        return new ArrayList<>(converterList.values()).get(index-1);
    }

    public static Set<Map.Entry<String, Converter>> getConverterOptions() {
        return converterList.entrySet();
    }

    public static int getConverterCount() {
        return converterList.size();
    }
}
