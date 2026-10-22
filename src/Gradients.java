import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class Gradients
{
    private static final LinkedHashMap<String, String> gradients = new LinkedHashMap<>();
    static {
        gradients.put(
                "Minimal-2",
                "# "
        );

        gradients.put(
                "Standard-5",
                "#*:. "
        );

        gradients.put(
                "Standard-10",
                "@%#*+=-:. "
        );

        gradients.put(
                "Block-5",
                "█▓▒░ "
        );

        gradients.put(
                "Dense",
                "$@B%8&WM#*oahkbdpqwmZO0QLCJYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
        );
    }

    public static Set<Map.Entry<String, String>> getGradients() {return gradients.entrySet();}

    public static int getGradientCount() {
        return gradients.size();
    }

    public static String listGradients (){
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (String name : gradients.keySet()) {
            result.append("(")
                    .append(index++)
                    .append(") ")
                    .append(name)
                    .append("\n");
        }
        return result.toString();
    }

    public static String getGradient(int index) {
        return new ArrayList<>(gradients.values()).get(index);
    }

}
