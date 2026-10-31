package Gradient;

public class Gradient {
    private final String name;
    private final String characterBase;
    private final int length;

    public Gradient(String name, String characterBase) {
        this.name = name;
        this.characterBase = characterBase;
        this.length = characterBase.length();
    }

    public String getName() {
        return name;
    }
    public String getCharacterBase() {
        return characterBase;
    }
    public int getLength() {return length;}
}
