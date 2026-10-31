package Brightness;

import java.util.function.Function;

public class Brightness {
    private final String name;
    private final Function<Integer, Integer> calculator;

    public Brightness(String name, Function<Integer, Integer> calculator) {
        this.name = name;
        this.calculator = calculator;
    }

    public String getName() {
        return name;
    }

    public double calculate(int rgb) {
        return calculator.apply(rgb);
    }
}
